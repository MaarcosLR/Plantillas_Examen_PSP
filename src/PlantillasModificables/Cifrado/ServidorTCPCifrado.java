package PlantillasModificables.Cifrado;

import java.io.*;
import java.net.*;

public class ServidorTCPCifrado {
    private static final char CLAVE_XOR = 'K';

    public static void main(String[] args) {
        int puerto = 5002;
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("📡 Servidor TCP con Cifrado escuchando en " + puerto);

            while (true) {
                Socket socket = servidor.accept();
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                String mensajeCifrado = entrada.readLine();
                System.out.println("📩 Recibido cifrado: " + mensajeCifrado);

                String mensajeDescifrado = cifrarXOR(mensajeCifrado);
                System.out.println("🔓 Descifrado: " + mensajeDescifrado);

                salida.println("✅ Respuesta cifrada: " + cifrarXOR(mensajeDescifrado.toUpperCase()));
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String cifrarXOR(String texto) {
        StringBuilder cifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            cifrado.append((char) (c ^ CLAVE_XOR));
        }
        return cifrado.toString();
    }
}

