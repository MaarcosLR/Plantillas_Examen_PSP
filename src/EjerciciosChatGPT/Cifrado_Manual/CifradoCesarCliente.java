package EjerciciosChatGPT.Cifrado_Manual;

// Cliente TCP que cifra un mensaje con el cifrado César antes de enviarlo



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class CifradoCesarCliente {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        Socket socket = new Socket("localhost", 7200);

// Permite escribir datos de salida en una conexión
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);



        System.out.print("Introduce mensaje: ");

        String mensaje = sc.nextLine();

        String mensajeCifrado = cifrarCesar(mensaje, 3);



        salida.println(mensajeCifrado);

        System.out.println("Mensaje cifrado enviado: " + mensajeCifrado);



        socket.close();

        sc.close();

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
