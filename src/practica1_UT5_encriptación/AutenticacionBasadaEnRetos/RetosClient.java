package practica1_UT5_encriptación.AutenticacionBasadaEnRetos;



// Importación de librerías necesarias para el funcionamiento
import javax.crypto.Cipher;

// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;

// Importación de librerías necesarias para el funcionamiento
import java.security.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.Base64;



// Clase principal que define la funcionalidad del programa
public class RetosClient {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        Socket socket = new Socket("localhost", 5432);

        System.out.println("Conectado al servidor para autenticación.");



        // Generar claves RSA del cliente

// Generación de claves públicas y privadas para RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(2048);

        KeyPair clientKeyPair = keyGen.generateKeyPair();

        PublicKey clientPublicKey = clientKeyPair.getPublic();

        PrivateKey clientPrivateKey = clientKeyPair.getPrivate();



        // Enviar clave pública al servidor

// Permite escribir datos de salida en una conexión
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println(Base64.getEncoder().encodeToString(clientPublicKey.getEncoded()));



        // Recibir clave pública del servidor

// Permite leer datos de entrada de manera eficiente
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String serverPublicKeyStr = in.readLine();

        byte[] serverPublicKeyBytes = Base64.getDecoder().decode(serverPublicKeyStr);

        PublicKey serverPublicKey = KeyFactory.getInstance("RSA")

                .generatePublic(new java.security.spec.X509EncodedKeySpec(serverPublicKeyBytes));



        // Recibir reto cifrado

        String encryptedChallengeStr = in.readLine();

        byte[] encryptedChallenge = Base64.getDecoder().decode(encryptedChallengeStr);



        // Descifrar el reto con la clave privada del cliente

// Configuración de cifrado para la protección de datos
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.DECRYPT_MODE, clientPrivateKey);

        byte[] decryptedChallenge = cipher.doFinal(encryptedChallenge);

        System.out.println("Reto descifrado: " + Base64.getEncoder().encodeToString(decryptedChallenge));



        // Operación sobre el reto (invertir los bytes)

        byte[] response = reverseBytes(decryptedChallenge); //MODIFICAR POR byte[] response = "respuesta_incorrecta".getBytes();

        //para PRUEBA 2



        // Cifrar la respuesta con la clave pública del servidor

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.ENCRYPT_MODE, serverPublicKey);

        byte[] encryptedResponse = cipher.doFinal(response);



        // Enviar la respuesta cifrada al servidor

        out.println(Base64.getEncoder().encodeToString(encryptedResponse)); //COMENTAR ESTA LÍNEA PARA PRUEBA 5

        System.out.println("Respuesta enviada al servidor.");



        socket.close();

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
