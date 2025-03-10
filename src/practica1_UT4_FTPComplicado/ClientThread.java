package practica1_UT4_FTPComplicado;

import practica1_UT4_FTPComplicado.commands.CommandExecutor;
import practica1_UT4_FTPComplicado.commands.CommandParser;
import practica1_UT4_FTPComplicado.commands.FTPCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Saludo inicial
            salida.println("Te has conectado correctamente. Ingresa el comando:");

            // Crear el CommandParser y CommandExecutor
            CommandParser parser = new CommandParser();
            CommandExecutor executor = new CommandExecutor();

            // Ejecutar comandos
            String comando;
            while ((comando = entrada.readLine()) != null) {
                if (comando.equalsIgnoreCase("QUIT")) {
                    salida.println("Conexión cerrada.");
                    break;
                }

                // Divide el comando en el nombre y el argumento
                String[] partes = comando.split(" ", 2);
                String commandName = partes[0];
                String argument = partes.length > 1 ? partes[1] : "";  // Obtener el argumento si existe

                // Usar CommandParser para analizar el comando, pasamos el Socket y BufferedReader
                FTPCommand command = parser.getCommand(commandName, argument, socket, entrada);

                if (command != null) {
                    // Para el comando USER, solo se pasa el nombre de usuario
                    if (commandName.equalsIgnoreCase("USER")) {
                        // Establecer el nombre de usuario en el CommandParser
                        parser.setUsername(argument);
                    }

                    // Crear el arreglo de argumentos que pasarás a executeCommand
                    String[] args = {commandName, argument};  // Se pasan los args al método
                    executor.executeCommand(command, args, salida);  // Llamar al método con los args
                } else {
                    salida.println("Comando no reconocido.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        }
    }
}
