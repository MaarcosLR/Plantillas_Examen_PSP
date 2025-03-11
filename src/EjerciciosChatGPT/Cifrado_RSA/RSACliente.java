package EjerciciosChatGPT.Cifrado_RSA;

// Cliente TCP con cifrado RSA

// Cifra un mensaje con la clave pública del servidor antes de enviarlo



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
public class RSACliente {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

        // Conectar con el servidor en localhost y puerto 7100

// Manejo de sockets para la comunicación en red
        Socket socket = new Socket("localhost", 7100);

// Permite escribir datos de salida en una conexión
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

// Permite leer datos de entrada de manera eficiente
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));



        // Generar claves RSA del cliente

// Generación de claves públicas y privadas para RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(2048);

        KeyPair clienteKeyPair = keyGen.generateKeyPair();

        PublicKey clientePublicKey = clienteKeyPair.getPublic();

        PrivateKey clientePrivateKey = clienteKeyPair.getPrivate();



        // Enviar clave pública al servidor

        salida.println(Base64.getEncoder().encodeToString(clientePublicKey.getEncoded()));



        // Recibir clave pública del servidor

        String clavePublicaServidor = entrada.readLine();

        byte[] claveServidorBytes = Base64.getDecoder().decode(clavePublicaServidor);

        PublicKey servidorPublicKey = KeyFactory.getInstance("RSA")

                .generatePublic(new java.security.spec.X509EncodedKeySpec(claveServidorBytes));



        // Cifrar mensaje con clave pública del servidor

        String mensaje = "Hola, este es un mensaje cifrado con RSA";

// Configuración de cifrado para la protección de datos
        Cipher cipher = Cipher.getInstance("RSA");

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.ENCRYPT_MODE, servidorPublicKey);

        String mensajeCifrado = Base64.getEncoder().encodeToString(cipher.doFinal(mensaje.getBytes("UTF-8")));



        // Enviar mensaje cifrado

        salida.println(mensajeCifrado);

        System.out.println("Mensaje cifrado enviado: " + mensajeCifrado);



        socket.close();

    }

}
