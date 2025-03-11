package EjerciciosChatGPT.UDP;

// Cliente UDP para adivinar el número secreto

// Envia un intento al servidor y recibe si es mayor, menor o correcto



// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class ClienteAdivinaNumeroUDP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

        // Crear socket UDP

// Manejo de sockets para la comunicación en red
        DatagramSocket socket = new DatagramSocket();

        InetAddress ip = InetAddress.getByName("localhost");

        Scanner sc = new Scanner(System.in);

        String respuesta = "";



        // Bucle hasta adivinar el número

// Bucle que se ejecuta mientras la condición sea verdadera
        while (!respuesta.equals("Correcto")) {

            System.out.print("Adivina el número (1-100): ");

            String intento = sc.nextLine();



            // Enviar intento al servidor

            byte[] buffer = intento.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, 5555);

            socket.send(packet);



            // Recibir respuesta del servidor

            byte[] receiveBuffer = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            socket.receive(receivePacket);

            respuesta = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Servidor dice: " + respuesta);

        }

        socket.close();

        sc.close();

    }

}
