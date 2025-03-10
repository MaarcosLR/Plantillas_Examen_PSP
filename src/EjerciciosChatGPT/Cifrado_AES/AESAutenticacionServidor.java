package EjerciciosChatGPT.Cifrado_AES;
// Servidor TCP que recibe credenciales cifradas y las descifra

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class AESAutenticacionServidor {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(7001);
        System.out.println("Servidor de Autenticación AES en espera de conexión...");

        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String claveBase64 = entrada.readLine();
            byte[] claveBytes = Base64.getDecoder().decode(claveBase64);
            SecretKey secretKey = new SecretKeySpec(claveBytes, "AES");

            String credencialesCifradas = entrada.readLine();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] credencialesBytes = cipher.doFinal(Base64.getDecoder().decode(credencialesCifradas));
            String credenciales = new String(credencialesBytes, "UTF-8");

            System.out.println("Credenciales descifradas: " + credenciales);

            socket.close();
        }
    }
}
