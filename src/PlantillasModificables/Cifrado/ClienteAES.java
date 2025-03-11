package PlantillasModificables.Cifrado;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.*;
import java.util.Base64;

public class ClienteAES {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 5000;
    private static final String CLAVE_AES = "1234567890123456"; // Clave de 16 bytes

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("💬 Escriba su mensaje: ");
            String mensaje = teclado.readLine();

            String mensajeCifrado = cifrarAES(mensaje, CLAVE_AES);
            System.out.println("🔒 Enviando mensaje cifrado: " + mensajeCifrado);
            salida.println(mensajeCifrado);

            String respuestaCifrada = entrada.readLine();
            System.out.println("📩 Respuesta cifrada del servidor: " + respuestaCifrada);

            String respuestaDescifrada = descifrarAES(respuestaCifrada, CLAVE_AES);
            System.out.println("🔓 Respuesta descifrada: " + respuestaDescifrada);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String cifrarAES(String texto, String clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec claveSecreta = new SecretKeySpec(clave.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, claveSecreta);
        byte[] textoCifrado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoCifrado);
    }

    public static String descifrarAES(String textoCifrado, String clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec claveSecreta = new SecretKeySpec(clave.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, claveSecreta);
        byte[] textoDescifrado = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
        return new String(textoDescifrado);
    }
}

