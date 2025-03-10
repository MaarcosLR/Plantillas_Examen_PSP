package EjerciciosChatGPT.Cifrado_Manual;
// Servidor TCP que recibe un mensaje cifrado con ROT13 y lo descifra

import java.io.*;
import java.net.*;

public class CifradoROT13Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(7400);
        System.out.println("Servidor de Cifrado ROT13 en espera de conexión...");

        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String mensajeCifrado = entrada.readLine();
            String mensajeDescifrado = cifrarROT13(mensajeCifrado);

            System.out.println("Mensaje descifrado: " + mensajeDescifrado);

            socket.close();
        }
    }

    public static String cifrarROT13(String texto) {
        return CifradoROT13Cliente.cifrarROT13(texto);
    }
}
