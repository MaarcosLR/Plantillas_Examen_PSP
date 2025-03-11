package EjerciciosChatGPT.UDP;

// Servidor UDP para chat bidireccional

// Recibe y responde mensajes del cliente



// Importación de librerías necesarias para el funcionamiento
import java.net.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class ServidorChatUDP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        DatagramSocket socket = new DatagramSocket(5557);

        byte[] buffer = new byte[1024];

        Scanner sc = new Scanner(System.in);



        System.out.println("Chat UDP iniciado. Esperando mensajes...");



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

            // Recibir mensaje del cliente

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);

            String mensaje = new String(packet.getData(), 0, packet.getLength());

            System.out.println("Cliente: " + mensaje);



// Condición que evalúa una expresión booleana
            if (mensaje.equalsIgnoreCase("salir")) break;



            // Enviar respuesta al cliente

            System.out.print("Tú: ");

            String respuesta = sc.nextLine();

            buffer = respuesta.getBytes();

            DatagramPacket respuestaPacket = new DatagramPacket(

                buffer, buffer.length, packet.getAddress(), packet.getPort());

            socket.send(respuestaPacket);

        }

        socket.close();

        sc.close();

    }

}
