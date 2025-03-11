package PlantillasModificables.TodoJunto;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.*;
import java.util.Base64;

public class ServidorAES {
    private static final int PUERTO = 5000;
    private static final String CLAVE_AES = "1234567890123456"; // Clave de 16 bytes

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("📡 Servidor AES escuchando en el puerto " + PUERTO);

            while (true) {
                Socket socket = servidor.accept();
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                String mensajeCifrado = entrada.readLine();
                System.out.println("📩 Recibido cifrado: " + mensajeCifrado);

                String mensajeDescifrado = descifrarAES(mensajeCifrado, CLAVE_AES);
                System.out.println("🔓 Descifrado: " + mensajeDescifrado);

                String respuestaCifrada = cifrarAES("✅ Respuesta del Servidor AES", CLAVE_AES);
                salida.println(respuestaCifrada);

                socket.close();
            }
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

