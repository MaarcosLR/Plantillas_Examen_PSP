package EjerciciosChatGPT.Cifrado_Manual;

// Servidor TCP que recibe un mensaje cifrado con ROT13 y lo descifra



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;



// Clase principal que define la funcionalidad del programa
public class CifradoROT13Servidor {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        ServerSocket serverSocket = new ServerSocket(7400);

        System.out.println("Servidor de Cifrado ROT13 en espera de conexión...");



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

// Manejo de sockets para la comunicación en red
            Socket socket = serverSocket.accept();

// Permite leer datos de entrada de manera eficiente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));



            String mensajeCifrado = entrada.readLine();

            String mensajeDescifrado = cifrarROT13(mensajeCifrado);



            System.out.println("Mensaje descifrado: " + mensajeDescifrado);



            socket.close();

        }

    }



    public static String cifrarROT13(String texto) {

        return CifradoROT13Cliente.cifrarROT13(texto);

    }

}
