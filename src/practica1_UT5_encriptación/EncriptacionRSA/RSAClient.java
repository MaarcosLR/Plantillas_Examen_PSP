package practica1_UT5_encriptación.EncriptacionRSA;

import javax.crypto.Cipher;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 6000);
        System.out.println("Conectado al servidor RSA.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String publicKeyStr = in.readLine();
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

        // Mensaje a cifrar
        String message = "Hola, me llamo Marcos y esto es con RSA"; //MANDAR MENSAJE VACÍO PARA PRUEBA 5
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        String encryptedMessage = Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));

        // Enviar mensaje cifrado
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(encryptedMessage); //Comentar esta línea para PRUEBA 4
        System.out.println("Mensaje cifrado enviado al servidor.");

        socket.close();
    }
}
