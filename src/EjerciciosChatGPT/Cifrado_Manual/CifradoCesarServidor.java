package EjerciciosChatGPT.Cifrado_Manual;
// Servidor TCP que recibe un mensaje cifrado con el cifrado César y lo descifra

import java.io.*;
import java.net.*;

public class CifradoCesarServidor {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(7200);
        System.out.println("Servidor de Cifrado César en espera de conexión...");

        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String mensajeCifrado = entrada.readLine();
            String mensajeDescifrado = descifrarCesar(mensajeCifrado, 3);

            System.out.println("Mensaje descifrado: " + mensajeDescifrado);

            socket.close();
        }
    }

    public static String descifrarCesar(String texto, int desplazamiento) {
        return cifrarCesar(texto, 26 - desplazamiento);
    }

    public static String cifrarCesar(String texto, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                resultado.append((char) ((c - base + desplazamiento) % 26 + base));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }
}
