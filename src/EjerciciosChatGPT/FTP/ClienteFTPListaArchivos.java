package EjerciciosChatGPT.FTP;

import java.io.*;
import java.net.Socket;

public class ClienteFTPListaArchivos {

    public static void main(String[] args) {
        String servidor = "localhost"; // Dirección del servidor
        int puerto = 2125; // Puerto donde el servidor FTP escucha

        try (Socket socket = new Socket(servidor, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            salida.println("LIST"); // Comando para solicitar la lista de archivos
            System.out.println("📂 Lista de archivos en el servidor:");

            String archivo;
            while ((archivo = entrada.readLine()) != null && !archivo.equals("END")) {
                System.out.println(archivo);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
