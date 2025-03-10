package practica1_UT4_FTPComplicado.commands;

import java.io.*;
import java.net.Socket;
import java.util.Base64;

public class PutCommand implements FTPCommand {
    private Socket clientSocket;
    private BufferedReader input;

    public PutCommand(Socket clientSocket, BufferedReader input) {
        this.clientSocket = clientSocket;
        this.input = input;
    }

    @Override
    public void execute(String[] args, PrintWriter salida) {
        if (args.length < 2) {
            salida.println("ERROR: Debes proporcionar un nombre de archivo.");
            return;
        }

        String nombreArchivo = args[1];
        File archivo = new File(nombreArchivo);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            salida.println("OK: Esperando contenido del archivo...");

            String linea;
            while ((linea = input.readLine()) != null) {
                if ("EOF".equals(linea)) {
                    break;
                }

                // Decodificar la línea de Base64
                byte[] decodedBytes = Base64.getDecoder().decode(linea);
                String decodedString = new String(decodedBytes);

                // Escribir la línea decodificada en el archivo
                escritor.write(decodedString);
                escritor.newLine();
            }

            salida.println("OK: Archivo recibido correctamente.");
        } catch (IOException e) {
            salida.println("ERROR: No se pudo guardar el archivo.");
            e.printStackTrace();
        }
    }
}
