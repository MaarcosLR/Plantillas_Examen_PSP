package EjerciciosChatGPT.TCP;
// Cliente TCP para chat bidireccional
// Permite enviar y recibir mensajes con un servidor

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteChatTCP {
    public static void main(String[] args) {
        try {
            // Conectar al servidor en localhost y puerto 6000
            Socket socket = new Socket("localhost", 6000);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);

            System.out.println("Conectado al chat. Escribe 'salir' para cerrar.");
            
            // Hilo para recibir mensajes del servidor
            new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = entrada.readLine()) != null) {
                        System.out.println("Servidor: " + mensaje);
                    }
                } catch (IOException e) {
                    System.out.println("Chat cerrado.");
                }
            }).start();

            // Enviar mensajes al servidor
            String mensaje;
            while (!(mensaje = sc.nextLine()).equalsIgnoreCase("salir")) {
                salida.println(mensaje);
            }

            // Cerrar conexión
            socket.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
