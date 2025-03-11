package EjerciciosChatGPT.Cifrado_Manual;

// Servidor TCP que recibe un mensaje cifrado con XOR y lo descifra



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;



// Clase principal que define la funcionalidad del programa
public class CifradoXORServidor {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        ServerSocket serverSocket = new ServerSocket(7300);

        System.out.println("Servidor de Cifrado XOR en espera de conexión...");



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

// Manejo de sockets para la comunicación en red
            Socket socket = serverSocket.accept();

// Permite leer datos de entrada de manera eficiente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));



            String mensajeCifrado = entrada.readLine();

            String mensajeDescifrado = cifrarXOR(mensajeCifrado, "clave");



            System.out.println("Mensaje descifrado: " + mensajeDescifrado);



            socket.close();

        }

    }



    public static String cifrarXOR(String texto, String clave) {

        StringBuilder resultado = new StringBuilder();

// Bucle que recorre un conjunto de elementos
        for (int i = 0; i < texto.length(); i++) {

            resultado.append((char) (texto.charAt(i) ^ clave.charAt(i % clave.length())));

        }

        return resultado.toString();

    }

}
