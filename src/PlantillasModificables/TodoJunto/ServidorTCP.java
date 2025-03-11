package PlantillasModificables.TodoJunto;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        int puerto = 5000;
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("📡 Servidor TCP escuchando en el puerto " + puerto);
            while (true) {
                Socket socket = servidor.accept();
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                String mensaje = entrada.readLine();
                System.out.println("📩 TCP Recibido: " + mensaje);
                salida.println("✅ Respuesta del servidor TCP: " + mensaje.toUpperCase());

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

