package EjerciciosChatGPT.TCP;

// Cliente TCP para solicitar la fecha y hora del servidor



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;



// Clase principal que define la funcionalidad del programa
public class ClienteTiempoTCP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) {

// Manejo de sockets para la comunicación en red
        try (Socket socket = new Socket("localhost", 6001);

// Permite leer datos de entrada de manera eficiente
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {



            String respuesta = entrada.readLine();

            System.out.println("Respuesta del servidor: " + respuesta);



        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
