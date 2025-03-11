package EjerciciosChatGPT.UDP;

// Cliente UDP de Preguntas y Respuestas

// Recibe una pregunta y envía la respuesta al servidor



// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class ClientePreguntasUDP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        DatagramSocket socket = new DatagramSocket();

        InetAddress ip = InetAddress.getByName("localhost");

        Scanner sc = new Scanner(System.in);



        System.out.print("¿Cuál es la capital de Francia? ");

        String respuesta = sc.nextLine();



        byte[] buffer = respuesta.getBytes();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, 5559);

        socket.send(packet);



        byte[] receiveBuffer = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        socket.receive(receivePacket);



        String resultado = new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Servidor dice: " + resultado);



        socket.close();

        sc.close();

    }

}
