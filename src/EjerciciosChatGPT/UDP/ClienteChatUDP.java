package EjerciciosChatGPT.UDP;

// Cliente UDP para chat bidireccional

// Permite al usuario enviar y recibir mensajes de un servidor



// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class ClienteChatUDP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        DatagramSocket socket = new DatagramSocket();

        InetAddress ip = InetAddress.getByName("localhost");

        Scanner sc = new Scanner(System.in);

        byte[] buffer = new byte[1024];



        System.out.println("Escribe 'salir' para cerrar el chat.");



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

            System.out.print("Tú: ");

            String mensaje = sc.nextLine();

// Condición que evalúa una expresión booleana
            if (mensaje.equalsIgnoreCase("salir")) break;



            // Enviar mensaje al servidor

            buffer = mensaje.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, 5557);

            socket.send(packet);



            // Recibir respuesta del servidor

            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

            socket.receive(receivePacket);

            String respuesta = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Servidor: " + respuesta);

        }

        socket.close();

        sc.close();

    }

}
