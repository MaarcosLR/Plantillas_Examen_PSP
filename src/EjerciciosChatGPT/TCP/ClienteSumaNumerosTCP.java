package EjerciciosChatGPT.TCP;

// Cliente TCP para enviar dos números y recibir la suma



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class ClienteSumaNumerosTCP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) {

// Manejo de sockets para la comunicación en red
        try (Socket socket = new Socket("localhost", 6002);

// Permite escribir datos de salida en una conexión
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

// Permite leer datos de entrada de manera eficiente
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {



            Scanner sc = new Scanner(System.in);



            System.out.print("Introduce el primer número: ");

            int num1 = sc.nextInt();

            System.out.print("Introduce el segundo número: ");

            int num2 = sc.nextInt();



            // Enviar los números

            salida.println(num1);

            salida.println(num2);



            // Recibir y mostrar la suma

            String resultado = entrada.readLine();

            System.out.println("Resultado de la suma: " + resultado);



            sc.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
