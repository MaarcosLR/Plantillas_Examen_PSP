package practica1_UT4_FTPComplicado;



// Importación de librerías necesarias para el funcionamiento
import java.io.IOException;

// Importación de librerías necesarias para el funcionamiento
import java.net.ServerSocket;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;



// Clase principal que define la funcionalidad del programa
public class FTPServer {

    private static final int PORT = 21;



// Método principal que inicia la ejecución del programa
    public static void main(String[] args) {

// Manejo de sockets para la comunicación en red
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Servidor iniciado correctamente en el puerto " + PORT);



// Bucle que se ejecuta mientras la condición sea verdadera
            while (true) {

                // Aceptar la conexión del cliente

// Manejo de sockets para la comunicación en red
                Socket clientSocket = serverSocket.accept();

// Manejo de sockets para la comunicación en red
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress());



                // Crear y ejecutar un nuevo hilo para manejar al cliente

// Manejo de sockets para la comunicación en red
                new ClientThread(clientSocket).start();

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
