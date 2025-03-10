package EjerciciosChatGPT.TCP;
// Servidor TCP de Fecha y Hora
// Devuelve la fecha y hora actual cuando un cliente se conecta

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class ServidorTiempoTCP {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6001)) {
            System.out.println("Servidor de tiempo en espera de conexiones...");

            while (true) {
                Socket socket = serverSocket.accept();
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                salida.println("Fecha y hora actual: " + LocalDateTime.now());
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
