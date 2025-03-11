package EjerciciosChatGPT.UDP;

// Servidor UDP de Eco

// Recibe un mensaje del cliente y lo devuelve tal cual



// Importación de librerías necesarias para el funcionamiento
import java.net.*;



// Clase principal que define la funcionalidad del programa
public class ServidorEcoUDP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        DatagramSocket socket = new DatagramSocket(5558);

        byte[] buffer = new byte[1024];



        System.out.println("Servidor de Eco UDP iniciado...");



// Bucle que se ejecuta mientras la condición sea verdadera
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
