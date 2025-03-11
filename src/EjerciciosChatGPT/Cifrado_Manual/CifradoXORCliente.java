package EjerciciosChatGPT.Cifrado_Manual;

// Cliente TCP que cifra un mensaje usando XOR antes de enviarlo



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class CifradoXORCliente {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        Socket socket = new Socket("localhost", 7300);

// Permite escribir datos de salida en una conexión
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);



        System.out.print("Introduce mensaje: ");

        String mensaje = sc.nextLine();

        String mensajeCifrado = cifrarXOR(mensaje, "clave");



        salida.println(mensajeCifrado);

        System.out.println("Mensaje cifrado enviado: " + mensajeCifrado);



        socket.close();

        sc.close();

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
