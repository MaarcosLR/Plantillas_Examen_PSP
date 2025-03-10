package EjerciciosChatGPT.UDP;
// Servidor UDP para juego de adivinar número
// Genera un número aleatorio y responde a los intentos del cliente

import java.net.*;
import java.util.Random;

public class ServidorAdivinaNumeroUDP {
    public static void main(String[] args) throws Exception {
        // Crear socket UDP
        DatagramSocket socket = new DatagramSocket(5555);
        byte[] buffer = new byte[1024];

        // Generar número secreto aleatorio
        Random random = new Random();
        int numeroSecreto = random.nextInt(100) + 1;
        System.out.println("Número secreto generado: " + numeroSecreto);

        while (true) {
            // Recibir intento del cliente
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String recibido = new String(packet.getData(), 0, packet.getLength());
            int intento = Integer.parseInt(recibido.trim());
            String respuesta;

            // Evaluar intento
            if (intento > numeroSecreto) {
                respuesta = "Menor";
            } else if (intento < numeroSecreto) {
                respuesta = "Mayor";
            } else {
                respuesta = "Correcto";
            }

            // Enviar respuesta al cliente
            byte[] respuestaBytes = respuesta.getBytes();
            DatagramPacket respuestaPacket = new DatagramPacket(
                respuestaBytes, respuestaBytes.length, packet.getAddress(), packet.getPort());
            socket.send(respuestaPacket);
        }
    }
}
