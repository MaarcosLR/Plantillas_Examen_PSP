package EjerciciosChatGPT.FTP;

// Servidor FTP básico sin usar Apache FTPServer

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// Clase principal que define la funcionalidad del programa
public class ServidorFTP {

    public static void main(String[] args) {
        int puerto = 2121;
        int puertoDescarga = 2122;

        Thread subirThread = new Thread(() -> {
            try (ServerSocket servidor = new ServerSocket(puerto)) {
                System.out.println("Servidor esperando archivos en el puerto " + puerto);

                while (true) {
                    Socket socket = servidor.accept();
                    InputStream entrada = socket.getInputStream();
                    FileOutputStream fos = new FileOutputStream("servidor.txt");

                    byte[] buffer = new byte[1024];
                    int bytesLeidos;
                    while ((bytesLeidos = entrada.read(buffer)) > 0) {
                        fos.write(buffer, 0, bytesLeidos);
                    }
                    fos.close();
                    socket.close();
                    System.out.println("Archivo recibido y guardado en servidor.txt");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Thread descargaThread = new Thread(() -> {
            try (ServerSocket servidor = new ServerSocket(puertoDescarga)) {
                System.out.println("Servidor esperando solicitudes de descarga en el puerto " + puertoDescarga);

                while (true) {
                    Socket socket = servidor.accept();
                    OutputStream salida = socket.getOutputStream();
                    FileInputStream fis = new FileInputStream("servidor.txt");

                    byte[] buffer = new byte[1024];
                    int bytesLeidos;
                    while ((bytesLeidos = fis.read(buffer)) > 0) {
                        salida.write(buffer, 0, bytesLeidos);
                    }
                    fis.close();
                    socket.close();
                    System.out.println("Archivo enviado correctamente.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        subirThread.start();
        descargaThread.start();
    }
}
