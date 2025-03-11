package practica1_UT4_FTPComplicado;



// Importación de librerías necesarias para el funcionamiento
import practica1_UT4_FTPComplicado.commands.CommandExecutor;

// Importación de librerías necesarias para el funcionamiento
import practica1_UT4_FTPComplicado.commands.CommandParser;

// Importación de librerías necesarias para el funcionamiento
import practica1_UT4_FTPComplicado.commands.FTPCommand;



// Importación de librerías necesarias para el funcionamiento
import java.io.BufferedReader;

// Importación de librerías necesarias para el funcionamiento
import java.io.IOException;

// Importación de librerías necesarias para el funcionamiento
import java.io.InputStreamReader;

// Importación de librerías necesarias para el funcionamiento
import java.io.PrintWriter;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;



// Clase principal que define la funcionalidad del programa
public class ClientThread extends Thread {

// Manejo de sockets para la comunicación en red
    private Socket socket;



// Manejo de sockets para la comunicación en red
    public ClientThread(Socket socket) {

        this.socket = socket;

    }



    @Override

    public void run() {

        try (

// Permite leer datos de entrada de manera eficiente
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

// Permite escribir datos de salida en una conexión
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)

        ) {

            // Saludo inicial

            salida.println("Te has conectado correctamente. Ingresa el comando:");



            // Crear el CommandParser y CommandExecutor

            CommandParser parser = new CommandParser();

            CommandExecutor executor = new CommandExecutor();



            // Ejecutar comandos

            String comando;

// Bucle que se ejecuta mientras la condición sea verdadera
            while ((comando = entrada.readLine()) != null) {

// Condición que evalúa una expresión booleana
                if (comando.equalsIgnoreCase("QUIT")) {

                    salida.println("Conexión cerrada.");

                    break;

                }



                // Divide el comando en el nombre y el argumento

                String[] partes = comando.split(" ", 2);

                String commandName = partes[0];

                String argument = partes.length > 1 ? partes[1] : "";  // Obtener el argumento si existe



// Manejo de sockets para la comunicación en red
                // Usar CommandParser para analizar el comando, pasamos el Socket y BufferedReader

                FTPCommand command = parser.getCommand(commandName, argument, socket, entrada);



// Condición que evalúa una expresión booleana
                if (command != null) {

                    // Para el comando USER, solo se pasa el nombre de usuario

// Condición que evalúa una expresión booleana
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
