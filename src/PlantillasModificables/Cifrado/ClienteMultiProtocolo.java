package PlantillasModificables.Cifrado;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteMultiProtocolo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n🔹 Menú Cliente");
            System.out.println("1. Enviar mensaje por TCP");
            System.out.println("2. Enviar mensaje por UDP");
            System.out.println("3. Listar archivos FTP");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir línea

            switch (opcion) {
                case 1 -> enviarTCP(scanner);
                case 2 -> enviarUDP(scanner);
                case 3 -> listarFTP();
                case 4 -> {
                    System.out.println("👋 Saliendo...");
                    return;
                }
                default -> System.out.println("❌ Opción inválida.");
            }
        }
    }

    private static void enviarTCP(Scanner scanner) {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.print("💬 Escriba su mensaje (TCP): ");
            String mensaje = scanner.nextLine();
            salida.println(mensaje);
            System.out.println("📩 Respuesta del servidor: " + entrada.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarUDP(Scanner scanner) {
        try (DatagramSocket socket = new DatagramSocket()) {
            System.out.print("💬 Escriba su mensaje (UDP): ");
            String mensaje = scanner.nextLine();
            byte[] buffer = mensaje.getBytes();
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 5001);
            socket.send(paquete);

            buffer = new byte[1024];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
            socket.receive(respuesta);
            System.out.println("📩 Respuesta del servidor: " + new String(respuesta.getData(), 0, respuesta.getLength()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listarFTP() {
        try (Socket socket = new Socket("localhost", 2121);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            salida.println("LIST");
            System.out.println("📂 Archivos en el servidor:");
            String archivo;
            while ((archivo = entrada.readLine()) != null && !archivo.equals("END")) {
                System.out.println("📄 " + archivo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
