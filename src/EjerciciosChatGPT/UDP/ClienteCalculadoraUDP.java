package EjerciciosChatGPT.UDP;

// Cliente UDP para calculadora básica

// Envía una operación matemática y recibe el resultado



// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class ClienteCalculadoraUDP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        DatagramSocket socket = new DatagramSocket();

        InetAddress ip = InetAddress.getByName("localhost");

        Scanner sc = new Scanner(System.in);



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

            System.out.print("Introduce operación (ej: 5+3) o 'exit' para salir: ");

            String operacion = sc.nextLine();

// Condición que evalúa una expresión booleana
            if (operacion.equalsIgnoreCase("exit")) break;



            byte[] buffer = operacion.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, 5556);

            socket.send(packet);



            byte[] receiveBuffer = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            socket.receive(receivePacket);

            String resultado = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Resultado: " + resultado);

        }

        socket.close();

        sc.close();

    }

}
