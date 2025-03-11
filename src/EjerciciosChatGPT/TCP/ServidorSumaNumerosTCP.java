package EjerciciosChatGPT.TCP;

// Servidor TCP que recibe dos números y devuelve la suma



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;



// Clase principal que define la funcionalidad del programa
public class ServidorSumaNumerosTCP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) {

// Manejo de sockets para la comunicación en red
        try (ServerSocket serverSocket = new ServerSocket(6002)) {

            System.out.println("Servidor de suma de números iniciado...");



// Bucle que se ejecuta mientras la condición sea verdadera
            while (true) {

// Manejo de sockets para la comunicación en red
                Socket socket = serverSocket.accept();

// Permite leer datos de entrada de manera eficiente
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

// Permite escribir datos de salida en una conexión
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);



                // Recibir los números

                int num1 = Integer.parseInt(entrada.readLine());

                int num2 = Integer.parseInt(entrada.readLine());



                // Enviar la suma

                int resultado = num1 + num2;

                salida.println(resultado);



                socket.close();

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
