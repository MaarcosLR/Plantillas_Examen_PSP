package practica1_UT4_FTPComplicado.commands;

import java.io.*;
import java.net.Socket;
import java.util.Base64;

public class GetCommand implements FTPCommand {
    private Socket clientSocket;
    private BufferedReader input;

    public GetCommand(Socket clientSocket, BufferedReader input) {
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

        if (!archivo.exists() || !archivo.isFile()) {
            salida.println("ERROR: El archivo no existe.");
            return;
        }

        try (BufferedReader lectorArchivo = new BufferedReader(new FileReader(archivo))) {
            salida.println("OK: Enviando archivo " + nombreArchivo);

            String linea;
            while ((linea = lectorArchivo.readLine()) != null) {
                // Codificar la línea en Base64 y enviarla
                String encodedLine = Base64.getEncoder().encodeToString(linea.getBytes());
                salida.println(encodedLine);
            }

            // Enviar "EOF" al final para indicar el fin del archivo
            salida.println("EOF");

            System.out.println("Archivo enviado correctamente: " + nombreArchivo);
        } catch (IOException e) {
            salida.println("ERROR: No se pudo leer el archivo.");
            e.printStackTrace();
        }
    }
}
