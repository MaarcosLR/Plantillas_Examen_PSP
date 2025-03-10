package EjerciciosChatGPT.TCP;
// Servidor TCP que recibe dos números y devuelve la suma

import java.io.*;
import java.net.*;

public class ServidorSumaNumerosTCP {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6002)) {
            System.out.println("Servidor de suma de números iniciado...");

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                // Recibir los números
                int num1 = Integer.parseInt(entrada.readLine());
                int num2 = Integer.parseInt(entrada.readLine());

                // Enviar la suma
                int resultado = num1 + num2;
                salida.println(resultado);

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
