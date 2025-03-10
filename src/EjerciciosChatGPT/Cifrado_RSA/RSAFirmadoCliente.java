package EjerciciosChatGPT.Cifrado_RSA;
// Cliente TCP que firma un mensaje con RSA y lo envía

import javax.crypto.Cipher;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.util.Base64;

public class RSAFirmadoCliente {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 7101);
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        String mensaje = "Mensaje importante";
        Signature firma = Signature.getInstance("SHA256withRSA");
        firma.initSign(privateKey);
        firma.update(mensaje.getBytes("UTF-8"));
        byte[] firmaBytes = firma.sign();

        salida.println(Base64.getEncoder().encodeToString(firmaBytes));
        salida.println(mensaje);

        socket.close();
    }
}
