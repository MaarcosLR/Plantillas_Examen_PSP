package EjerciciosChatGPT.UDP;
// Cliente UDP de Eco
// Envía un mensaje al servidor y recibe la misma respuesta

import java.net.*;
import java.util.Scanner;

public class ClienteEcoUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Mensaje para el servidor ('salir' para terminar): ");
            String mensaje = sc.nextLine();
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
