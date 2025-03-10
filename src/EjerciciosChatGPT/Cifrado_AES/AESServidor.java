package EjerciciosChatGPT.Cifrado_AES;
// Servidor TCP con descifrado AES
// Recibe un mensaje cifrado y lo descifra

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class AESServidor {
    public static void main(String[] args) throws Exception {
        // Iniciar servidor en el puerto 7000
        ServerSocket serverSocket = new ServerSocket(7000);
        System.out.println("Servidor AES en espera de conexión...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Recibir la clave AES
            String claveBase64 = entrada.readLine();
            byte[] claveBytes = Base64.getDecoder().decode(claveBase64);
            SecretKey secretKey = new SecretKeySpec(claveBytes, "AES");

            // Recibir el mensaje cifrado
            String mensajeCifrado = entrada.readLine();

            // Descifrar el mensaje
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] mensajeDescifradoBytes = cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));
            String mensajeDescifrado = new String(mensajeDescifradoBytes, "UTF-8");

            System.out.println("Mensaje descifrado: " + mensajeDescifrado);

            socket.close();
        }
    }
}
