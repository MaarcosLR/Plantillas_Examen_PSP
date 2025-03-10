package EjerciciosChatGPT.FTP;
// Servidor FTP básico usando Apache FTPServer

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.UserFactory;
import org.apache.ftpserver.usermanager.impl.PropertiesUserManagerFactory;

import java.io.File;

public class ServidorFTP {
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
