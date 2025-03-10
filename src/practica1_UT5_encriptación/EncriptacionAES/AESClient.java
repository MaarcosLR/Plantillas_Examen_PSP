package practica1_UT5_encriptación.EncriptacionAES;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.net.Socket;
import java.util.Base64;

public class AESClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Conectado al servidor AES.");

        // Generar la clave AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        String secretKeyStr = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        System.out.println("Clave AES en Cliente: " + secretKeyStr);

        // Mensaje a cifrar
        String message = "Hola, me llamo Marcos"; //PRUEBA 5 (ENVIAR MENSAJE VACÍO)
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        String encryptedMessage = Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));

        // Flujo de salida para enviar datos al servidor
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Enviar la clave AES al servidor
        out.println(secretKeyStr); //COMENTAR ESTA LÍNEA PARA PRUEBA 4.

        // Enviar el mensaje cifrado
        out.println(encryptedMessage);
        System.out.println("Mensaje cifrado enviado al servidor: " + encryptedMessage);

        socket.close();
    }
}
