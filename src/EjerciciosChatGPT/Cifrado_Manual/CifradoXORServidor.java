package EjerciciosChatGPT.Cifrado_Manual;
// Servidor TCP que recibe un mensaje cifrado con XOR y lo descifra

import java.io.*;
import java.net.*;

public class CifradoXORServidor {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(7300);
        System.out.println("Servidor de Cifrado XOR en espera de conexión...");

        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String mensajeCifrado = entrada.readLine();
            String mensajeDescifrado = cifrarXOR(mensajeCifrado, "clave");

            System.out.println("Mensaje descifrado: " + mensajeDescifrado);

            socket.close();
        }
    }

    public static String cifrarXOR(String texto, String clave) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            resultado.append((char) (texto.charAt(i) ^ clave.charAt(i % clave.length())));
        }
        return resultado.toString();
    }
}
