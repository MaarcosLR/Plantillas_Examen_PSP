package EjerciciosChatGPT.FTP;

// Cliente FTP para subir y descargar archivos desde un servidor

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// Clase principal que define la funcionalidad del programa
public class ClienteFTP {

    public static void main(String[] args) {
        String servidor = "localhost";
        int puerto = 2121;
        String archivoLocal = "subir.txt";
        String archivoDescargado = "descargado.txt";

        try (Socket socket = new Socket(servidor, puerto);
             FileInputStream fis = new FileInputStream(archivoLocal);
             OutputStream salida = socket.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;
            while ((bytesLeidos = fis.read(buffer)) > 0) {
                salida.write(buffer, 0, bytesLeidos);
            }
            System.out.println("Archivo enviado al servidor.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (Socket socket = new Socket(servidor, puerto + 1);
             InputStream entrada = socket.getInputStream();
             FileOutputStream fos = new FileOutputStream(archivoDescargado)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;
            while ((bytesLeidos = entrada.read(buffer)) > 0) {
                fos.write(buffer, 0, bytesLeidos);
            }
            System.out.println("Archivo descargado correctamente.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}