package EjerciciosChatGPT.UDP;
// Cliente UDP para adivinar el número secreto
// Envia un intento al servidor y recibe si es mayor, menor o correcto

import java.net.*;
import java.util.Scanner;

public class ClienteAdivinaNumeroUDP {
    public static void main(String[] args) throws Exception {
        // Crear socket UDP
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        Scanner sc = new Scanner(System.in);
        String respuesta = "";

        // Bucle hasta adivinar el número
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
