package EjerciciosChatGPT.Cifrado_RSA;
// Cliente TCP con cifrado RSA
// Cifra un mensaje con la clave pública del servidor antes de enviarlo

import javax.crypto.Cipher;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.util.Base64;

public class RSACliente {
    public static void main(String[] args) throws Exception {
        // Conectar con el servidor en localhost y puerto 7100
        Socket socket = new Socket("localhost", 7100);
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Generar claves RSA del cliente
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair clienteKeyPair = keyGen.generateKeyPair();
        PublicKey clientePublicKey = clienteKeyPair.getPublic();
        PrivateKey clientePrivateKey = clienteKeyPair.getPrivate();

        // Enviar clave pública al servidor
        salida.println(Base64.getEncoder().encodeToString(clientePublicKey.getEncoded()));

        // Recibir clave pública del servidor
        String clavePublicaServidor = entrada.readLine();
        byte[] claveServidorBytes = Base64.getDecoder().decode(clavePublicaServidor);
        PublicKey servidorPublicKey = KeyFactory.getInstance("RSA")
                .generatePublic(new java.security.spec.X509EncodedKeySpec(claveServidorBytes));

        // Cifrar mensaje con clave pública del servidor
        String mensaje = "Hola, este es un mensaje cifrado con RSA";
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, servidorPublicKey);
        String mensajeCifrado = Base64.getEncoder().encodeToString(cipher.doFinal(mensaje.getBytes("UTF-8")));

        // Enviar mensaje cifrado
        salida.println(mensajeCifrado);
        System.out.println("Mensaje cifrado enviado: " + mensajeCifrado);

        socket.close();
    }
}
