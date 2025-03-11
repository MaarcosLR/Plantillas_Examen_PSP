package EjerciciosChatGPT.UDP;

// Cliente UDP de Eco

// Envía un mensaje al servidor y recibe la misma respuesta



// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class ClienteEcoUDP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        DatagramSocket socket = new DatagramSocket();

        InetAddress ip = InetAddress.getByName("localhost");

        Scanner sc = new Scanner(System.in);



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

            System.out.print("Mensaje para el servidor ('salir' para terminar): ");

            String mensaje = sc.nextLine();

// Condición que evalúa una expresión booleana
            if (mensaje.equalsIgnoreCase("salir")) break;



            byte[] buffer = mensaje.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, 5558);

            socket.send(packet);



            DatagramPacket respuestaPacket = new DatagramPacket(buffer, buffer.length);

            socket.receive(respuestaPacket);

            String respuesta = new String(respuestaPacket.getData(), 0, respuestaPacket.getLength());

            System.out.println("Servidor: " + respuesta);

        }

        socket.close();

        sc.close();

    }

}
