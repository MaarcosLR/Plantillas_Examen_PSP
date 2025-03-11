package PlantillasModificables.TodoJunto;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class ServidorRSA {
    private static final int PUERTO = 5001;

    public static void main(String[] args) {
        try {
            KeyPair claves = generarClavesRSA();
            PrivateKey clavePrivada = claves.getPrivate();
            PublicKey clavePublica = claves.getPublic();

            System.out.println("📡 Servidor RSA escuchando en el puerto " + PUERTO);
            try (ServerSocket servidor = new ServerSocket(PUERTO)) {
                while (true) {
                    Socket socket = servidor.accept();
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                    String mensajeCifrado = entrada.readLine();
                    System.out.println("📩 Recibido cifrado: " + mensajeCifrado);

                    String mensajeDescifrado = descifrarRSA(mensajeCifrado, clavePrivada);
                    System.out.println("🔓 Descifrado: " + mensajeDescifrado);

                    String respuestaCifrada = cifrarRSA("✅ Respuesta del Servidor RSA", clavePublica);
                    salida.println(respuestaCifrada);

                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static KeyPair generarClavesRSA() throws Exception {
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(2048);
        return generador.generateKeyPair();
    }

    public static String cifrarRSA(String texto, PublicKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        return Base64.getEncoder().encodeToString(cipher.doFinal(texto.getBytes()));
    }

    public static String descifrarRSA(String textoCifrado, PrivateKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        return new String(cipher.doFinal(Base64.getDecoder().decode(textoCifrado)));
    }
}

