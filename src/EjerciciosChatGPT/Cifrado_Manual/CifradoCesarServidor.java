package EjerciciosChatGPT.Cifrado_Manual;

// Servidor TCP que recibe un mensaje cifrado con el cifrado César y lo descifra



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;



// Clase principal que define la funcionalidad del programa
public class CifradoCesarServidor {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        ServerSocket serverSocket = new ServerSocket(7200);

        System.out.println("Servidor de Cifrado César en espera de conexión...");



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

// Manejo de sockets para la comunicación en red
            Socket socket = serverSocket.accept();

// Permite leer datos de entrada de manera eficiente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));



            String mensajeCifrado = entrada.readLine();

            String mensajeDescifrado = descifrarCesar(mensajeCifrado, 3);



            System.out.println("Mensaje descifrado: " + mensajeDescifrado);



            socket.close();

        }

    }



    public static String descifrarCesar(String texto, int desplazamiento) {

        return cifrarCesar(texto, 26 - desplazamiento);

    }



    public static String cifrarCesar(String texto, int desplazamiento) {

        StringBuilder resultado = new StringBuilder();

// Bucle que recorre un conjunto de elementos
        for (char c : texto.toCharArray()) {

// Condición que evalúa una expresión booleana
            if (Character.isLetter(c)) {

                char base = Character.isUpperCase(c) ? 'A' : 'a';

                resultado.append((char) ((c - base + desplazamiento) % 26 + base));

            } else {

                resultado.append(c);

            }

        }

        return resultado.toString();

    }

}
