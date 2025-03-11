package practica1_UT5_encriptación.EncriptacionRSA;



// Importación de librerías necesarias para el funcionamiento
import javax.crypto.Cipher;

// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;

// Importación de librerías necesarias para el funcionamiento
import java.security.*;

// Importación de librerías necesarias para el funcionamiento
import java.security.spec.X509EncodedKeySpec;

// Importación de librerías necesarias para el funcionamiento
import java.util.Base64;



// Clase principal que define la funcionalidad del programa
public class RSAClient {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        Socket socket = new Socket("localhost", 6000);

        System.out.println("Conectado al servidor RSA.");



// Permite leer datos de entrada de manera eficiente
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String publicKeyStr = in.readLine();

        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));



        // Mensaje a cifrar

        String message = "Hola, me llamo Marcos y esto es con RSA"; //MANDAR MENSAJE VACÍO PARA PRUEBA 5

// Configuración de cifrado para la protección de datos
        Cipher cipher = Cipher.getInstance("RSA");

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        String encryptedMessage = Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));



        // Enviar mensaje cifrado

// Permite escribir datos de salida en una conexión
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println(encryptedMessage); //Comentar esta línea para PRUEBA 4

        System.out.println("Mensaje cifrado enviado al servidor.");



        socket.close();

    }

}
