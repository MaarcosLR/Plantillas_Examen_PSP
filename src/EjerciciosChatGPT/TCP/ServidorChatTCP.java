package EjerciciosChatGPT.TCP;

// Servidor TCP para chat bidireccional

// Atiende múltiples clientes en paralelo



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.concurrent.CopyOnWriteArrayList;



// Clase principal que define la funcionalidad del programa
public class ServidorChatTCP {

    private static final int PORT = 6000;

// Permite escribir datos de salida en una conexión
    private static final CopyOnWriteArrayList<PrintWriter> clientes = new CopyOnWriteArrayList<>();



// Método principal que inicia la ejecución del programa
    public static void main(String[] args) {

        System.out.println("Servidor de chat TCP iniciado en el puerto " + PORT);

        

// Manejo de sockets para la comunicación en red
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

// Bucle que se ejecuta mientras la condición sea verdadera
            while (true) {

                // Aceptar nuevo cliente

// Manejo de sockets para la comunicación en red
                Socket cliente = serverSocket.accept();

                System.out.println("Nuevo cliente conectado: " + cliente.getInetAddress());

// Permite escribir datos de salida en una conexión
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

// Manejo de sockets para la comunicación en red
        private Socket cliente;



// Manejo de sockets para la comunicación en red
        ClienteHandler(Socket cliente) {

            this.cliente = cliente;

        }



        @Override

        public void run() {

// Permite leer datos de entrada de manera eficiente
            try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()))) {

                String mensaje;

// Bucle que se ejecuta mientras la condición sea verdadera
                while ((mensaje = entrada.readLine()) != null) {

                    System.out.println("Mensaje recibido: " + mensaje);

// Bucle que recorre un conjunto de elementos
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
