package EjerciciosChatGPT.UDP;
// Cliente UDP para calculadora básica
// Envía una operación matemática y recibe el resultado

import java.net.*;
import java.util.Scanner;

public class ClienteCalculadoraUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Introduce operación (ej: 5+3) o 'exit' para salir: ");
            String operacion = sc.nextLine();
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
