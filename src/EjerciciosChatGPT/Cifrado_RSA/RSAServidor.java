package EjerciciosChatGPT.Cifrado_RSA;

// Servidor TCP con descifrado RSA

// Recibe un mensaje cifrado y lo descifra usando su clave privada



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
public class RSAServidor {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

        // Iniciar servidor en el puerto 7100

// Manejo de sockets para la comunicación en red
        ServerSocket serverSocket = new ServerSocket(7100);

        System.out.println("Servidor RSA en espera de conexión...");



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

// Manejo de sockets para la comunicación en red
            Socket socket = serverSocket.accept();

            System.out.println("Cliente conectado.");



// Permite leer datos de entrada de manera eficiente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

// Permite escribir datos de salida en una conexión
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);



            // Generar claves RSA del servidor

// Generación de claves públicas y privadas para RSA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            keyGen.initialize(2048);

            KeyPair servidorKeyPair = keyGen.generateKeyPair();

            PublicKey servidorPublicKey = servidorKeyPair.getPublic();

            PrivateKey servidorPrivateKey = servidorKeyPair.getPrivate();



            // Enviar clave pública del servidor al cliente

            salida.println(Base64.getEncoder().encodeToString(servidorPublicKey.getEncoded()));



            // Recibir mensaje cifrado

            String mensajeCifrado = entrada.readLine();



            // Descifrar el mensaje

// Configuración de cifrado para la protección de datos
            Cipher cipher = Cipher.getInstance("RSA");

// Configuración de cifrado para la protección de datos
            cipher.init(Cipher.DECRYPT_MODE, servidorPrivateKey);

            byte[] mensajeDescifradoBytes = cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));

            String mensajeDescifrado = new String(mensajeDescifradoBytes, "UTF-8");



            System.out.println("Mensaje descifrado: " + mensajeDescifrado);



            socket.close();

        }

    }

}
