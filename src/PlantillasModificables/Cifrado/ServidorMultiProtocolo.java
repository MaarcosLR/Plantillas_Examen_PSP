package PlantillasModificables.Cifrado;

import java.io.*;
import java.net.*;

public class ServidorMultiProtocolo {
    private static final int PUERTO_TCP = 5000;
    private static final int PUERTO_UDP = 5001;
    private static final int PUERTO_FTP = 2121;

    public static void main(String[] args) {
        System.out.println("🔹 Servidor Multi-Protocolo iniciado...");

        // Crear hilos para manejar cada protocolo simultáneamente
        new Thread(ServidorMultiProtocolo::iniciarServidorTCP).start();
        new Thread(ServidorMultiProtocolo::iniciarServidorUDP).start();
        new Thread(ServidorMultiProtocolo::iniciarServidorFTP).start();
    }

    private static void iniciarServidorTCP() {
        try (ServerSocket servidor = new ServerSocket(PUERTO_TCP)) {
            System.out.println("📡 Servidor TCP escuchando en el puerto " + PUERTO_TCP);
            while (true) {
                Socket socket = servidor.accept();
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                String mensaje = entrada.readLine();
                System.out.println("📩 TCP Recibido: " + mensaje);
                salida.println("✅ Respuesta del servidor TCP: " + mensaje.toUpperCase());

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void iniciarServidorUDP() {
        try (DatagramSocket socket = new DatagramSocket(PUERTO_UDP)) {
            System.out.println("📡 Servidor UDP escuchando en el puerto " + PUERTO_UDP);
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

    private static void iniciarServidorFTP() {
        int puertoDescarga = 2122;
        String carpetaServidor = "servidor_archivos/";

        File directorio = new File(carpetaServidor);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        try (ServerSocket servidor = new ServerSocket(PUERTO_FTP)) {
            System.out.println("📂 Servidor FTP esperando conexiones en el puerto " + PUERTO_FTP);
            while (true) {
                Socket socket = servidor.accept();
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                String comando = entrada.readLine();

                if ("LIST".equalsIgnoreCase(comando)) {
                    File[] archivos = directorio.listFiles();
                    if (archivos != null) {
                        for (File archivo : archivos) {
                            salida.println(archivo.getName());
                        }
                    }
                    salida.println("END");
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

