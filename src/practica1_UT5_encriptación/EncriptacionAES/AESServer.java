package practica1_UT5_encriptación.EncriptacionAES;



// Importación de librerías necesarias para el funcionamiento
import javax.crypto.Cipher;

// Importación de librerías necesarias para el funcionamiento
import javax.crypto.SecretKey;

// Importación de librerías necesarias para el funcionamiento
import javax.crypto.spec.SecretKeySpec;

// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.ServerSocket;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;

// Importación de librerías necesarias para el funcionamiento
import java.util.Base64;



// Clase principal que define la funcionalidad del programa
public class AESServer {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        ServerSocket serverSocket = new ServerSocket(5000);

        System.out.println("Servidor AES esperando conexión...");



// Manejo de sockets para la comunicación en red
        Socket socket = serverSocket.accept();

        System.out.println("Cliente conectado.");



        // Flujo de entrada para recibir datos del cliente

// Permite leer datos de entrada de manera eficiente
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));



        // Recibir la clave AES del cliente

        String secretKeyStr = in.readLine(); //COPIAR Y PEGAR

        byte[] keyBytes = Base64.getDecoder().decode(secretKeyStr); //COPIAR Y PEGAR

// Especificación de la clave secreta para AES
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES"); //COPIAR Y PEGAR

        //PARA PRUEBA 2, COPIAR Y PEGAR POR ESTO:

        //KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        //keyGen.init(128);

        //SecretKey secretKey = keyGen.generateKey();



        System.out.println("Clave AES recibida en Servidor: " + secretKeyStr); //modificar por secretKey (Prueba2)



        // Recibir el mensaje cifrado

        String encryptedMessage = in.readLine();

        //encryptedMessage = encryptedMessage.substring(1); // JUEGO DE PRUEBA 3



        System.out.println("Mensaje cifrado recibido: " + encryptedMessage);



        // Descifrar el mensaje recibido

// Configuración de cifrado para la protección de datos
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));

        String decryptedMessage = new String(decryptedBytes, "UTF-8");



        System.out.println("Mensaje descifrado: " + decryptedMessage);



        socket.close();

// Manejo de sockets para la comunicación en red
        serverSocket.close();

    }

}
