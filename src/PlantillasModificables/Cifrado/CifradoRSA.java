package PlantillasModificables.Cifrado;

import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class CifradoRSA {

    // Generar claves RSA (pública y privada)
    public static KeyPair generarClavesRSA() throws Exception {
        KeyPairGenerator generadorClaves = KeyPairGenerator.getInstance("RSA");
        generadorClaves.initialize(2048); // Tamaño de clave 2048 bits
        return generadorClaves.generateKeyPair();
    }

    // Cifrar texto con la clave pública
    public static String cifrarRSA(String texto, PublicKey clavePublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clavePublica);
        byte[] textoCifrado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoCifrado); // Convertir a Base64
    }

    // Descifrar texto con la clave privada
    public static String descifrarRSA(String textoCifrado, PrivateKey clavePrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
        byte[] textoDescifrado = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
        return new String(textoDescifrado);
    }

    // Prueba del cifrado RSA
    public static void main(String[] args) throws Exception {
        KeyPair claves = generarClavesRSA();
        PublicKey clavePublica = claves.getPublic();
        PrivateKey clavePrivada = claves.getPrivate();

        String mensaje = "Hola Mundo RSA";

        String mensajeCifrado = cifrarRSA(mensaje, clavePublica);
        System.out.println("🔒 Cifrado RSA: " + mensajeCifrado);

        String mensajeDescifrado = descifrarRSA(mensajeCifrado, clavePrivada);
        System.out.println("🔓 Descifrado RSA: " + mensajeDescifrado);
    }
}

