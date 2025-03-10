package EjerciciosChatGPT.TCP;
// Servidor TCP para chat bidireccional
// Atiende múltiples clientes en paralelo

import java.io.*;
import java.net.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServidorChatTCP {
    private static final int PORT = 6000;
    private static final CopyOnWriteArrayList<PrintWriter> clientes = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("Servidor de chat TCP iniciado en el puerto " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // Aceptar nuevo cliente
                Socket cliente = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + cliente.getInetAddress());
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
                clientes.add(salida);

                // Crear hilo para manejar al cliente
                new Thread(new ClienteHandler(cliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClienteHandler implements Runnable {
        private Socket cliente;

        ClienteHandler(Socket cliente) {
            this.cliente = cliente;
        }

        @Override
        public void run() {
            try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()))) {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + mensaje);
                    for (PrintWriter writer : clientes) {
                        writer.println(mensaje);
                    }
                }
            } catch (IOException e) {
                System.out.println("Cliente desconectado.");
            } finally {
                try {
                    cliente.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
