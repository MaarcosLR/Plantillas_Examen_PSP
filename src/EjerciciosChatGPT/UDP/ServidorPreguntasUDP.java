package EjerciciosChatGPT.UDP;

// Servidor UDP de Preguntas y Respuestas

// Envía una pregunta al cliente y recibe la respuesta



// Importación de librerías necesarias para el funcionamiento
import java.net.*;



// Clase principal que define la funcionalidad del programa
public class ServidorPreguntasUDP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        DatagramSocket socket = new DatagramSocket(5559);

        byte[] buffer = new byte[1024];



        String pregunta = "¿Cuál es la capital de Francia?";

        String respuestaCorrecta = "Paris";



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);

            String respuesta = new String(packet.getData(), 0, packet.getLength()).trim();



            String mensaje = respuesta.equalsIgnoreCase(respuestaCorrecta) ? "Correcto" : "Incorrecto";

            byte[] respuestaBytes = mensaje.getBytes();

            DatagramPacket respuestaPacket = new DatagramPacket(respuestaBytes, respuestaBytes.length, packet.getAddress(), packet.getPort());

            socket.send(respuestaPacket);

        }

    }

}
