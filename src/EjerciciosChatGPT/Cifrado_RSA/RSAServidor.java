package EjerciciosChatGPT.Cifrado_RSA;
// Servidor TCP con descifrado RSA
// Recibe un mensaje cifrado y lo descifra usando su clave privada

import javax.crypto.Cipher;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.util.Base64;

public class RSAServidor {
    public static void main(String[] args) throws Exception {
        // Iniciar servidor en el puerto 7100
        ServerSocket serverSocket = new ServerSocket(7100);
        System.out.println("Servidor RSA en espera de conexión...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Generar claves RSA del servidor
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair servidorKeyPair = keyGen.generateKeyPair();
            PublicKey servidorPublicKey = servidorKeyPair.getPublic();
            PrivateKey servidorPrivateKey = servidorKeyPair.getPrivate();

            // Enviar clave pública del servidor al cliente
            salida.println(Base64.getEncoder().encodeToString(servidorPublicKey.getEncoded()));

            // Recibir mensaje cifrado
            String mensajeCifrado = entrada.readLine();

            // Descifrar el mensaje
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, servidorPrivateKey);
            byte[] mensajeDescifradoBytes = cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));
            String mensajeDescifrado = new String(mensajeDescifradoBytes, "UTF-8");

            System.out.println("Mensaje descifrado: " + mensajeDescifrado);

            socket.close();
        }
    }
}
