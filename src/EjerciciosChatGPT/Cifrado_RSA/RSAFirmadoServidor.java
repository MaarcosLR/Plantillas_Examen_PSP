package EjerciciosChatGPT.Cifrado_RSA;

// Servidor TCP que recibe un mensaje firmado y verifica su autenticidad



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
public class RSAFirmadoServidor {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        ServerSocket serverSocket = new ServerSocket(7101);

        System.out.println("Servidor RSA de Firma Digital en espera de conexión...");



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

// Manejo de sockets para la comunicación en red
            Socket socket = serverSocket.accept();

// Permite leer datos de entrada de manera eficiente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));



            String firmaBase64 = entrada.readLine();

            String mensaje = entrada.readLine();



            byte[] firmaBytes = Base64.getDecoder().decode(firmaBase64);



// Generación de claves públicas y privadas para RSA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            keyGen.initialize(2048);

            KeyPair keyPair = keyGen.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();



            Signature firma = Signature.getInstance("SHA256withRSA");

            firma.initVerify(publicKey);

            firma.update(mensaje.getBytes("UTF-8"));



            boolean verificado = firma.verify(firmaBytes);

            System.out.println("Firma válida: " + verificado);



            socket.close();

        }

    }

}
