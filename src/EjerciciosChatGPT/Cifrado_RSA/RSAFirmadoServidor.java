package EjerciciosChatGPT.Cifrado_RSA;
// Servidor TCP que recibe un mensaje firmado y verifica su autenticidad

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.util.Base64;

public class RSAFirmadoServidor {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(7101);
        System.out.println("Servidor RSA de Firma Digital en espera de conexión...");

        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String firmaBase64 = entrada.readLine();
            String mensaje = entrada.readLine();

            byte[] firmaBytes = Base64.getDecoder().decode(firmaBase64);

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();

            Signature firma = Signature.getInstance("SHA256withRSA");
            firma.initVerify(publicKey);
            firma.update(mensaje.getBytes("UTF-8"));

            boolean verificado = firma.verify(firmaBytes);
            System.out.println("Firma válida: " + verificado);

            socket.close();
        }
    }
}
