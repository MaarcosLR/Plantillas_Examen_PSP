package PlantillasModificables.Cifrado;

import java.io.IOException;
import java.net.*;

public class ServidorUDPHilos {
    private static final int PUERTO = 5001;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("📡 Servidor UDP esperando mensajes en el puerto " + PUERTO);
            while (true) {
                new Thread(new ClientHandlerUDP(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandlerUDP implements Runnable {
    private DatagramSocket socket;

    public ClientHandlerUDP(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(paqueteEntrada);
            String mensaje = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
            System.out.println("📩 UDP Recibido: " + mensaje);

            String respuesta = "✅ Respuesta del servidor UDP: " + mensaje.toUpperCase();
            DatagramPacket paqueteSalida = new DatagramPacket(respuesta.getBytes(), respuesta.length(),
                    paqueteEntrada.getAddress(), paqueteEntrada.getPort());
            socket.send(paqueteSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

