package EjerciciosChatGPT.Cifrado_AES;

// Cliente TCP con cifrado AES

// Cifra un mensaje antes de enviarlo al servidor



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
public class AESCliente {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

        // Conectar con el servidor en localhost y puerto 7000

// Manejo de sockets para la comunicación en red
        Socket socket = new Socket("localhost", 7000);

// Permite escribir datos de salida en una conexión
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

// Permite leer datos de entrada de manera eficiente
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));



        // Generar clave AES

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        keyGen.init(128);

        SecretKey secretKey = keyGen.generateKey();



        // Mensaje a cifrar

        String mensaje = "Hola, este es un mensaje cifrado con AES";

// Configuración de cifrado para la protección de datos
        Cipher cipher = Cipher.getInstance("AES");

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        String mensajeCifrado = Base64.getEncoder().encodeToString(cipher.doFinal(mensaje.getBytes("UTF-8")));



        // Enviar la clave en Base64

        salida.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));

        // Enviar el mensaje cifrado

        salida.println(mensajeCifrado);



        System.out.println("Mensaje cifrado enviado al servidor: " + mensajeCifrado);



        // Cerrar conexión

        socket.close();

    }

}
