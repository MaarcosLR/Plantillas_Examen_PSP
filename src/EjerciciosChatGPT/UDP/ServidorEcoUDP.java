package EjerciciosChatGPT.UDP;
// Servidor UDP de Eco
// Recibe un mensaje del cliente y lo devuelve tal cual

import java.net.*;

public class ServidorEcoUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5558);
        byte[] buffer = new byte[1024];

        System.out.println("Servidor de Eco UDP iniciado...");

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String mensaje = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Mensaje recibido: " + mensaje);

            DatagramPacket respuestaPacket = new DatagramPacket(
                buffer, buffer.length, packet.getAddress(), packet.getPort());
            socket.send(respuestaPacket);
        }
    }
}
