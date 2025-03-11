package practica1_UT5_encriptación.AutenticacionBasadaEnRetos;



// Importación de librerías necesarias para el funcionamiento
import javax.crypto.Cipher;

// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.ServerSocket;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;

// Importación de librerías necesarias para el funcionamiento
import java.security.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Base64;

// Importación de librerías necesarias para el funcionamiento
import java.util.Arrays;

// Importación de librerías necesarias para el funcionamiento
import java.security.SecureRandom;



// Clase principal que define la funcionalidad del programa
public class RetosServer {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        ServerSocket serverSocket = new ServerSocket(5432);

        System.out.println("Servidor esperando conexión para autenticación...");



// Manejo de sockets para la comunicación en red
        Socket socket = serverSocket.accept();

        System.out.println("Cliente conectado.");



        // Generar claves RSA del servidor

// Generación de claves públicas y privadas para RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(2048);

        KeyPair serverKeyPair = keyGen.generateKeyPair();

        PublicKey serverPublicKey = serverKeyPair.getPublic();

        PrivateKey serverPrivateKey = serverKeyPair.getPrivate(); //COPIAR Y PEGAR POR:

        //KeyPair serverKeyPair2 = keyGen.generateKeyPair(); // Nueva clave

        //PrivateKey serverPrivateKey = serverKeyPair2.getPrivate();

        // PARA PRUEBA 4





        // Enviar clave pública del servidor al cliente

// Permite escribir datos de salida en una conexión
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println(Base64.getEncoder().encodeToString(serverPublicKey.getEncoded()));



        // Recibir clave pública del cliente

// Permite leer datos de entrada de manera eficiente
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String clientPublicKeyStr = in.readLine();

        byte[] clientPublicKeyBytes = Base64.getDecoder().decode(clientPublicKeyStr);

        PublicKey clientPublicKey = KeyFactory.getInstance("RSA")

                .generatePublic(new java.security.spec.X509EncodedKeySpec(clientPublicKeyBytes));



        // Generar un reto aleatorio

        byte[] challenge = new byte[16]; //MODIFICAR 16 por 0 para PRUEBA 6

        new SecureRandom().nextBytes(challenge);

        System.out.println("Reto generado: " + Base64.getEncoder().encodeToString(challenge));



        // Cifrar el reto con la clave pública del cliente

// Configuración de cifrado para la protección de datos
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.ENCRYPT_MODE, clientPublicKey);

        byte[] encryptedChallenge = cipher.doFinal(challenge);

        //encryptedChallenge[0] ^= 1; // Modifica el primer byte del reto cifrado para PRUEBA 3



        // Enviar reto cifrado al cliente

        out.println(Base64.getEncoder().encodeToString(encryptedChallenge));



        // Recibir respuesta del cliente

        String encryptedResponseStr = in.readLine();

        byte[] encryptedResponse = Base64.getDecoder().decode(encryptedResponseStr);



        // Descifrar la respuesta con la clave privada del servidor

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.DECRYPT_MODE, serverPrivateKey);

        byte[] decryptedResponse = cipher.doFinal(encryptedResponse);

        System.out.println("Respuesta descifrada del cliente: " + Base64.getEncoder().encodeToString(decryptedResponse));



        // Verificar la respuesta

        byte[] expectedResponse = reverseBytes(challenge);

// Condición que evalúa una expresión booleana
        if (Arrays.equals(decryptedResponse, expectedResponse)) {

            System.out.println("Autenticación exitosa. Cliente verificado.");

        } else {

            System.out.println("Error de autenticación. Respuesta incorrecta.");

        }



        socket.close();

// Manejo de sockets para la comunicación en red
        serverSocket.close();

    }



    private static byte[] reverseBytes(byte[] input) {

        byte[] output = new byte[input.length];

// Bucle que recorre un conjunto de elementos
        for (int i = 0; i < input.length; i++) {

            output[i] = input[input.length - 1 - i];

        }

        return output;

    }

}
