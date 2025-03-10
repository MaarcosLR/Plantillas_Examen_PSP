package practica1_UT5_encriptación.AutenticacionBasadaEnRetos;

import javax.crypto.Cipher;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.util.Base64;

public class RetosClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5432);
        System.out.println("Conectado al servidor para autenticación.");

        // Generar claves RSA del cliente
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair clientKeyPair = keyGen.generateKeyPair();
        PublicKey clientPublicKey = clientKeyPair.getPublic();
        PrivateKey clientPrivateKey = clientKeyPair.getPrivate();

        // Enviar clave pública al servidor
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(Base64.getEncoder().encodeToString(clientPublicKey.getEncoded()));

        // Recibir clave pública del servidor
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String serverPublicKeyStr = in.readLine();
        byte[] serverPublicKeyBytes = Base64.getDecoder().decode(serverPublicKeyStr);
        PublicKey serverPublicKey = KeyFactory.getInstance("RSA")
                .generatePublic(new java.security.spec.X509EncodedKeySpec(serverPublicKeyBytes));

        // Recibir reto cifrado
        String encryptedChallengeStr = in.readLine();
        byte[] encryptedChallenge = Base64.getDecoder().decode(encryptedChallengeStr);

        // Descifrar el reto con la clave privada del cliente
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, clientPrivateKey);
        byte[] decryptedChallenge = cipher.doFinal(encryptedChallenge);
        System.out.println("Reto descifrado: " + Base64.getEncoder().encodeToString(decryptedChallenge));

        // Operación sobre el reto (invertir los bytes)
        byte[] response = reverseBytes(decryptedChallenge); //MODIFICAR POR byte[] response = "respuesta_incorrecta".getBytes();
        //para PRUEBA 2

        // Cifrar la respuesta con la clave pública del servidor
        cipher.init(Cipher.ENCRYPT_MODE, serverPublicKey);
        byte[] encryptedResponse = cipher.doFinal(response);

        // Enviar la respuesta cifrada al servidor
        out.println(Base64.getEncoder().encodeToString(encryptedResponse)); //COMENTAR ESTA LÍNEA PARA PRUEBA 5
        System.out.println("Respuesta enviada al servidor.");

        socket.close();
    }

    private static byte[] reverseBytes(byte[] input) {
        byte[] output = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[input.length - 1 - i];
        }
        return output;
    }
}
