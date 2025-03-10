package EjerciciosChatGPT.UDP;
// Servidor UDP para chat bidireccional
// Recibe y responde mensajes del cliente

import java.net.*;
import java.util.Scanner;

public class ServidorChatUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5557);
        byte[] buffer = new byte[1024];
        Scanner sc = new Scanner(System.in);

        System.out.println("Chat UDP iniciado. Esperando mensajes...");

        while (true) {
            // Recibir mensaje del cliente
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String mensaje = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Cliente: " + mensaje);

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
