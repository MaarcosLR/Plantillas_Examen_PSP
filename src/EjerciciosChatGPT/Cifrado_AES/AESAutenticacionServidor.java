package EjerciciosChatGPT.Cifrado_AES;

// Servidor TCP que recibe credenciales cifradas y las descifra



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
public class AESAutenticacionServidor {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        ServerSocket serverSocket = new ServerSocket(7001);

        System.out.println("Servidor de Autenticación AES en espera de conexión...");



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

// Manejo de sockets para la comunicación en red
            Socket socket = serverSocket.accept();

// Permite leer datos de entrada de manera eficiente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));



            String claveBase64 = entrada.readLine();

            byte[] claveBytes = Base64.getDecoder().decode(claveBase64);

// Especificación de la clave secreta para AES
            SecretKey secretKey = new SecretKeySpec(claveBytes, "AES");



            String credencialesCifradas = entrada.readLine();

// Configuración de cifrado para la protección de datos
            Cipher cipher = Cipher.getInstance("AES");

// Configuración de cifrado para la protección de datos
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] credencialesBytes = cipher.doFinal(Base64.getDecoder().decode(credencialesCifradas));

            String credenciales = new String(credencialesBytes, "UTF-8");



            System.out.println("Credenciales descifradas: " + credenciales);



            socket.close();

        }

    }

}
