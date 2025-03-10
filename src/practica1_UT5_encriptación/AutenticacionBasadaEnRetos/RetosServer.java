package practica1_UT5_encriptación.AutenticacionBasadaEnRetos;

import javax.crypto.Cipher;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.util.Base64;
import java.util.Arrays;
import java.security.SecureRandom;

public class RetosServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5432);
        System.out.println("Servidor esperando conexión para autenticación...");

        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        // Generar claves RSA del servidor
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair serverKeyPair = keyGen.generateKeyPair();
        PublicKey serverPublicKey = serverKeyPair.getPublic();
        PrivateKey serverPrivateKey = serverKeyPair.getPrivate(); //COPIAR Y PEGAR POR:
        //KeyPair serverKeyPair2 = keyGen.generateKeyPair(); // Nueva clave
        //PrivateKey serverPrivateKey = serverKeyPair2.getPrivate();
        // PARA PRUEBA 4


        // Enviar clave pública del servidor al cliente
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(Base64.getEncoder().encodeToString(serverPublicKey.getEncoded()));

        // Recibir clave pública del cliente
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientPublicKeyStr = in.readLine();
        byte[] clientPublicKeyBytes = Base64.getDecoder().decode(clientPublicKeyStr);
        PublicKey clientPublicKey = KeyFactory.getInstance("RSA")
                .generatePublic(new java.security.spec.X509EncodedKeySpec(clientPublicKeyBytes));

        // Generar un reto aleatorio
        byte[] challenge = new byte[16]; //MODIFICAR 16 por 0 para PRUEBA 6
        new SecureRandom().nextBytes(challenge);
        System.out.println("Reto generado: " + Base64.getEncoder().encodeToString(challenge));

        // Cifrar el reto con la clave pública del cliente
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, clientPublicKey);
        byte[] encryptedChallenge = cipher.doFinal(challenge);
        //encryptedChallenge[0] ^= 1; // Modifica el primer byte del reto cifrado para PRUEBA 3

        // Enviar reto cifrado al cliente
        out.println(Base64.getEncoder().encodeToString(encryptedChallenge));

        // Recibir respuesta del cliente
        String encryptedResponseStr = in.readLine();
        byte[] encryptedResponse = Base64.getDecoder().decode(encryptedResponseStr);

        // Descifrar la respuesta con la clave privada del servidor
        cipher.init(Cipher.DECRYPT_MODE, serverPrivateKey);
        byte[] decryptedResponse = cipher.doFinal(encryptedResponse);
        System.out.println("Respuesta descifrada del cliente: " + Base64.getEncoder().encodeToString(decryptedResponse));

        // Verificar la respuesta
        byte[] expectedResponse = reverseBytes(challenge);
        if (Arrays.equals(decryptedResponse, expectedResponse)) {
            System.out.println("Autenticación exitosa. Cliente verificado.");
        } else {
            System.out.println("Error de autenticación. Respuesta incorrecta.");
        }

        socket.close();
        serverSocket.close();
    }

    private static byte[] reverseBytes(byte[] input) {
        byte[] output = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[input.length - 1 - i];
        }
        return output;
    }
}
