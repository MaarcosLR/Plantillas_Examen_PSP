package practica1_UT5_encriptación.EncriptacionAES;



// Importación de librerías necesarias para el funcionamiento
import javax.crypto.Cipher;

// Importación de librerías necesarias para el funcionamiento
import javax.crypto.KeyGenerator;

// Importación de librerías necesarias para el funcionamiento
import javax.crypto.SecretKey;

// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;

// Importación de librerías necesarias para el funcionamiento
import java.util.Base64;



// Clase principal que define la funcionalidad del programa
public class AESClient {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        Socket socket = new Socket("localhost", 5000);

        System.out.println("Conectado al servidor AES.");



        // Generar la clave AES

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        keyGen.init(128);

        SecretKey secretKey = keyGen.generateKey();

        String secretKeyStr = Base64.getEncoder().encodeToString(secretKey.getEncoded());



        System.out.println("Clave AES en Cliente: " + secretKeyStr);



        // Mensaje a cifrar

        String message = "Hola, me llamo Marcos"; //PRUEBA 5 (ENVIAR MENSAJE VACÍO)

// Configuración de cifrado para la protección de datos
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        String encryptedMessage = Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));



        // Flujo de salida para enviar datos al servidor

// Permite escribir datos de salida en una conexión
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);



        // Enviar la clave AES al servidor

        out.println(secretKeyStr); //COMENTAR ESTA LÍNEA PARA PRUEBA 4.



        // Enviar el mensaje cifrado

        out.println(encryptedMessage);

        System.out.println("Mensaje cifrado enviado al servidor: " + encryptedMessage);



        socket.close();

    }

}
