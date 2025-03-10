package EjerciciosChatGPT.Cifrado_AES;
// Cliente TCP con cifrado AES
// Cifra un mensaje antes de enviarlo al servidor

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.net.Socket;
import java.util.Base64;

public class AESCliente {
    public static void main(String[] args) throws Exception {
        // Conectar con el servidor en localhost y puerto 7000
        Socket socket = new Socket("localhost", 7000);
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Generar clave AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        // Mensaje a cifrar
        String mensaje = "Hola, este es un mensaje cifrado con AES";
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        String mensajeCifrado = Base64.getEncoder().encodeToString(cipher.doFinal(mensaje.getBytes("UTF-8")));

        // Enviar la clave en Base64
        salida.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        // Enviar el mensaje cifrado
        salida.println(mensajeCifrado);

        System.out.println("Mensaje cifrado enviado al servidor: " + mensajeCifrado);

        // Cerrar conexión
        socket.close();
    }
}
