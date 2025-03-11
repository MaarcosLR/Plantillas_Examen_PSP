package PlantillasModificables.Cifrado;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class CifradoAES {

    // Generar clave AES
    public static SecretKey generarClaveAES() throws Exception {
        KeyGenerator generadorClave = KeyGenerator.getInstance("AES");
        generadorClave.init(128); // 128 bits de seguridad
        return generadorClave.generateKey();
    }

    // Cifrar texto con AES
    public static String cifrarAES(String texto, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        byte[] textoCifrado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoCifrado); // Convertir a Base64
    }

    // Descifrar texto con AES
    public static String descifrarAES(String textoCifrado, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] textoDescifrado = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
        return new String(textoDescifrado);
    }

    // Prueba del cifrado AES
    public static void main(String[] args) throws Exception {
        SecretKey clave = generarClaveAES();
        String mensaje = "Hola Mundo AES";

        String mensajeCifrado = cifrarAES(mensaje, clave);
        System.out.println("🔒 Cifrado AES: " + mensajeCifrado);

        String mensajeDescifrado = descifrarAES(mensajeCifrado, clave);
        System.out.println("🔓 Descifrado AES: " + mensajeDescifrado);
    }
}

