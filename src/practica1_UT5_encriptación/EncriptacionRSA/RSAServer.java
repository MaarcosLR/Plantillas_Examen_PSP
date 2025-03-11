package practica1_UT5_encriptación.EncriptacionRSA;



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



// Clase principal que define la funcionalidad del programa
public class RSAServer {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        ServerSocket serverSocket = new ServerSocket(6000);

        System.out.println("Servidor RSA esperando conexión...");



// Manejo de sockets para la comunicación en red
        Socket socket = serverSocket.accept();

        System.out.println("Cliente conectado.");



        // Generar claves RSA

// Generación de claves públicas y privadas para RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(2048);

        KeyPair keyPair = keyGen.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();

        PrivateKey privateKey = keyPair.getPrivate(); //COPIAR Y PEGAR PARA PRUEBA 2



        //POR ESTO:

        //KeyPair keyPair2 = keyGen.generateKeyPair(); // Nueva clave privada

        //PrivateKey privateKey = keyPair2.getPrivate();



        // Enviar clave pública al cliente

// Permite escribir datos de salida en una conexión
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));



        // Recibir mensaje cifrado

// Permite leer datos de entrada de manera eficiente
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String encryptedMessage = in.readLine();

        // encryptedMessage = encryptedMessage.substring(1); // Elimina el primer carácter (PRUEBA 3)

        System.out.println("Mensaje cifrado recibido: " + encryptedMessage);



        // Descifrar mensaje

// Configuración de cifrado para la protección de datos
        Cipher cipher = Cipher.getInstance("RSA");

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));

        System.out.println("Mensaje descifrado: " + new String(decryptedBytes, "UTF-8"));



        socket.close();

// Manejo de sockets para la comunicación en red
        serverSocket.close();

    }

}


