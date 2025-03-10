package practica1_UT5_encriptación.EncriptacionRSA;

import javax.crypto.Cipher;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.util.Base64;

public class RSAServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Servidor RSA esperando conexión...");

        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        // Generar claves RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate(); //COPIAR Y PEGAR PARA PRUEBA 2

        //POR ESTO:
        //KeyPair keyPair2 = keyGen.generateKeyPair(); // Nueva clave privada
        //PrivateKey privateKey = keyPair2.getPrivate();

        // Enviar clave pública al cliente
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        // Recibir mensaje cifrado
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String encryptedMessage = in.readLine();
        // encryptedMessage = encryptedMessage.substring(1); // Elimina el primer carácter (PRUEBA 3)
        System.out.println("Mensaje cifrado recibido: " + encryptedMessage);

        // Descifrar mensaje
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        System.out.println("Mensaje descifrado: " + new String(decryptedBytes, "UTF-8"));

        socket.close();
        serverSocket.close();
    }
}

