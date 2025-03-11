package EjerciciosChatGPT.FTP;

// Servidor FTP básico usando Apache FTPServer



// Importación de librerías necesarias para el funcionamiento
import org.apache.ftpserver.FtpServer;

// Importación de librerías necesarias para el funcionamiento
import org.apache.ftpserver.FtpServerFactory;

// Importación de librerías necesarias para el funcionamiento
import org.apache.ftpserver.listener.ListenerFactory;

// Importación de librerías necesarias para el funcionamiento
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;

// Importación de librerías necesarias para el funcionamiento
import org.apache.ftpserver.usermanager.UserFactory;

// Importación de librerías necesarias para el funcionamiento
import org.apache.ftpserver.usermanager.impl.PropertiesUserManagerFactory;



// Importación de librerías necesarias para el funcionamiento
import java.io.File;



// Clase principal que define la funcionalidad del programa
public class ServidorFTP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) {

        try {

            FtpServerFactory serverFactory = new FtpServerFactory();

            ListenerFactory factory = new ListenerFactory();



            // Configurar puerto del servidor FTP

            factory.setPort(21);

            serverFactory.addListener("default", factory.createListener());



            // Configurar usuarios

            PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();

            userManagerFactory.setFile(new File("usuarios.properties"));

            userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());

            serverFactory.setUserManager(userManagerFactory.createUserManager());



            // Iniciar servidor FTP

            FtpServer servidor = serverFactory.createServer();

            servidor.start();



            System.out.println("Servidor FTP iniciado en el puerto 21.");



        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
