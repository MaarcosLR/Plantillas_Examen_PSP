package EjerciciosChatGPT.TCP;

// Servidor TCP de Fecha y Hora

// Devuelve la fecha y hora actual cuando un cliente se conecta



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.time.LocalDateTime;



// Clase principal que define la funcionalidad del programa
public class ServidorTiempoTCP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) {

// Manejo de sockets para la comunicación en red
        try (ServerSocket serverSocket = new ServerSocket(6001)) {

            System.out.println("Servidor de tiempo en espera de conexiones...");



// Bucle que se ejecuta mientras la condición sea verdadera
            while (true) {

// Manejo de sockets para la comunicación en red
                Socket socket = serverSocket.accept();

// Permite escribir datos de salida en una conexión
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                salida.println("Fecha y hora actual: " + LocalDateTime.now());

                socket.close();

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
