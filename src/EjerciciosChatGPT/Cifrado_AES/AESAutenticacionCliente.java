package EjerciciosChatGPT.Cifrado_AES;
// Cliente TCP que envía credenciales cifradas con AES

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class AESAutenticacionCliente {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 7001);
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        Scanner sc = new Scanner(System.in);
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();
        String credenciales = usuario + ":" + password;

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        String credencialesCifradas = Base64.getEncoder().encodeToString(cipher.doFinal(credenciales.getBytes("UTF-8")));

        salida.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        salida.println(credencialesCifradas);

        socket.close();
        sc.close();
    }
}
