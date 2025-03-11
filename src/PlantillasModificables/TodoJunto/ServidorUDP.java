package PlantillasModificables.TodoJunto;

import java.io.IOException;
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        int puerto = 5001;
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("📡 Servidor UDP escuchando en el puerto " + puerto);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteEntrada);

                String mensaje = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("📩 UDP Recibido: " + mensaje);

                String respuesta = "✅ Respuesta del servidor UDP: " + mensaje.toUpperCase();
                DatagramPacket paqueteSalida = new DatagramPacket(respuesta.getBytes(), respuesta.length(),
                        paqueteEntrada.getAddress(), paqueteEntrada.getPort());
                socket.send(paqueteSalida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

