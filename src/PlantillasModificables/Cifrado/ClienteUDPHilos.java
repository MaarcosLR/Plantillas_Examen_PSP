package PlantillasModificables.Cifrado;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClienteUDPHilos {
    public static void main(String[] args) {
        String servidor = "localhost";
        int puerto = 5001;
        try (DatagramSocket socket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("💬 Escriba su mensaje: ");
            String mensaje = scanner.nextLine();

            byte[] buffer = mensaje.getBytes();
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(servidor), puerto);
            socket.send(paquete);

            buffer = new byte[1024];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
            socket.receive(respuesta);
            System.out.println("📩 Respuesta del servidor: " + new String(respuesta.getData(), 0, respuesta.getLength()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

