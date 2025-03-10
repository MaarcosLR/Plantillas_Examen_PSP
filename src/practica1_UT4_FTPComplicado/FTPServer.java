package practica1_UT4_FTPComplicado;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPServer {
    private static final int PORT = 21;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado correctamente en el puerto " + PORT);

            while (true) {
                // Aceptar la conexión del cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress());

                // Crear y ejecutar un nuevo hilo para manejar al cliente
                new ClientThread(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
