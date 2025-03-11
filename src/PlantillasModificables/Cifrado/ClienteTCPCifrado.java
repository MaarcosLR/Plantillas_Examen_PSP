package PlantillasModificables.Cifrado;

import java.io.*;
import java.net.*;

public class ClienteTCPCifrado {
    private static final char CLAVE_XOR = 'K'; // Clave de cifrado

    public static void main(String[] args) {
        String servidor = "localhost"; // Dirección del servidor
        int puerto = 5002; // Puerto donde escucha el servidor

        try (Socket socket = new Socket(servidor, puerto);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("💬 Escriba su mensaje: ");
            String mensaje = teclado.readLine();

            // Cifrar el mensaje antes de enviarlo
            String mensajeCifrado = cifrarXOR(mensaje);
            System.out.println("🔒 Enviando mensaje cifrado: " + mensajeCifrado);

            salida.println(mensajeCifrado); // Enviar mensaje cifrado al servidor

            // Recibir la respuesta cifrada del servidor
            String respuestaCifrada = entrada.readLine();
            System.out.println("📩 Respuesta cifrada del servidor: " + respuestaCifrada);

            // Descifrar la respuesta
            String respuestaDescifrada = cifrarXOR(respuestaCifrada);
            System.out.println("🔓 Respuesta descifrada: " + respuestaDescifrada);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método de cifrado y descifrado XOR
    public static String cifrarXOR(String texto) {
        StringBuilder cifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            cifrado.append((char) (c ^ CLAVE_XOR)); // XOR con la clave
        }
        return cifrado.toString();
    }
}

