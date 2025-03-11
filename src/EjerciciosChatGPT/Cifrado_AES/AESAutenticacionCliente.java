package EjerciciosChatGPT.Cifrado_AES;

// Cliente TCP que envía credenciales cifradas con AES



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

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class AESAutenticacionCliente {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        Socket socket = new Socket("localhost", 7001);

// Permite escribir datos de salida en una conexión
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);



        KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        keyGen.init(128);

        SecretKey secretKey = keyGen.generateKey();



        Scanner sc = new Scanner(System.in);

        System.out.print("Usuario: ");

        String usuario = sc.nextLine();

        System.out.print("Contraseña: ");

        String password = sc.nextLine();

        String credenciales = usuario + ":" + password;



// Configuración de cifrado para la protección de datos
        Cipher cipher = Cipher.getInstance("AES");

// Configuración de cifrado para la protección de datos
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        String credencialesCifradas = Base64.getEncoder().encodeToString(cipher.doFinal(credenciales.getBytes("UTF-8")));



        salida.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));

        salida.println(credencialesCifradas);



        socket.close();

        sc.close();

    }

}
