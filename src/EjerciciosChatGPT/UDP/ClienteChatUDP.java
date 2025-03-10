package EjerciciosChatGPT.UDP;
// Cliente UDP para chat bidireccional
// Permite al usuario enviar y recibir mensajes de un servidor

import java.net.*;
import java.util.Scanner;

public class ClienteChatUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        Scanner sc = new Scanner(System.in);
        byte[] buffer = new byte[1024];

        System.out.println("Escribe 'salir' para cerrar el chat.");

        while (true) {
            System.out.print("Tú: ");
            String mensaje = sc.nextLine();
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
