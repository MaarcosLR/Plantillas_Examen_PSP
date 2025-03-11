package EjerciciosChatGPT.TCP;

// Cliente TCP para chat bidireccional

// Permite enviar y recibir mensajes con un servidor



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class ClienteChatTCP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) {

        try {

            // Conectar al servidor en localhost y puerto 6000

// Manejo de sockets para la comunicación en red
            Socket socket = new Socket("localhost", 6000);

// Permite leer datos de entrada de manera eficiente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

// Permite escribir datos de salida en una conexión
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);



            System.out.println("Conectado al chat. Escribe 'salir' para cerrar.");

            

            // Hilo para recibir mensajes del servidor

            new Thread(() -> {

                try {

                    String mensaje;

// Bucle que se ejecuta mientras la condición sea verdadera
                    while ((mensaje = entrada.readLine()) != null) {

                        System.out.println("Servidor: " + mensaje);

                    }

                } catch (IOException e) {

                    System.out.println("Chat cerrado.");

                }

            }).start();



            // Enviar mensajes al servidor

            String mensaje;

// Bucle que se ejecuta mientras la condición sea verdadera
            while (!(mensaje = sc.nextLine()).equalsIgnoreCase("salir")) {

                salida.println(mensaje);

            }



            // Cerrar conexión

            socket.close();

            sc.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
