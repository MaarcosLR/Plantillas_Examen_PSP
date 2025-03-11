package EjerciciosChatGPT.Cifrado_RSA;

// Cliente TCP que firma un mensaje con RSA y lo envía



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
public class RSAFirmadoCliente {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        Socket socket = new Socket("localhost", 7101);

// Permite escribir datos de salida en una conexión
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);



// Generación de claves públicas y privadas para RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(2048);

        KeyPair keyPair = keyGen.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();



        String mensaje = "Mensaje importante";

        Signature firma = Signature.getInstance("SHA256withRSA");

        firma.initSign(privateKey);

        firma.update(mensaje.getBytes("UTF-8"));

        byte[] firmaBytes = firma.sign();



        salida.println(Base64.getEncoder().encodeToString(firmaBytes));

        salida.println(mensaje);



        socket.close();

    }

}
