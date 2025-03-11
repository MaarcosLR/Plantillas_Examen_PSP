package PlantillasModificables.TodoJunto;

import java.io.*;
import java.net.*;
import java.security.*;

public class ClienteRSA {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 5001;

    public static void main(String[] args) throws Exception {
        KeyPair claves = ServidorRSA.generarClavesRSA();
        PublicKey clavePublica = claves.getPublic();
        PrivateKey clavePrivada = claves.getPrivate();

        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("💬 Escriba su mensaje: ");
            String mensaje = teclado.readLine();

            String mensajeCifrado = ServidorRSA.cifrarRSA(mensaje, clavePublica);
            salida.println(mensajeCifrado);

            String respuestaCifrada = entrada.readLine();
            String respuestaDescifrada = ServidorRSA.descifrarRSA(respuestaCifrada, clavePrivada);

            System.out.println("🔓 Respuesta descifrada: " + respuestaDescifrada);
        }
    }
}
