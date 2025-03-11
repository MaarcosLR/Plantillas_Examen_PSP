package PlantillasModificables.Cifrado;

import java.io.*;
import java.net.*;

public class ServidorTCPHilos {
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("📡 Servidor TCP esperando conexiones en el puerto " + PUERTO);
            while (true) {
                Socket socket = servidor.accept();
                new Thread(new ClientHandlerTCP(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandlerTCP implements Runnable {
    private Socket socket;

    public ClientHandlerTCP(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            String mensaje = entrada.readLine();
            System.out.println("📩 TCP Recibido: " + mensaje);
            salida.println("✅ Respuesta del servidor TCP: " + mensaje.toUpperCase());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

