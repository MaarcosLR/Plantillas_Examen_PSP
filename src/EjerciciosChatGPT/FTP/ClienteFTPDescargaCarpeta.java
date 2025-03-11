package EjerciciosChatGPT.FTP;

// Cliente FTP que descarga todos los archivos de una carpeta



// Importación de librerías necesarias para el funcionamiento
import org.apache.commons.net.ftp.FTPClient;

// Importación de librerías necesarias para el funcionamiento
import java.io.FileOutputStream;

// Importación de librerías necesarias para el funcionamiento
import java.io.IOException;



// Clase principal que define la funcionalidad del programa
public class ClienteFTPDescargaCarpeta {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) {

        String servidor = "localhost";

        int puerto = 21;

        String usuario = "usuario";

        String clave = "clave";



        FTPClient ftpCliente = new FTPClient();



        try {

            ftpCliente.connect(servidor, puerto);

            ftpCliente.login(usuario, clave);

            ftpCliente.enterLocalPassiveMode();



            System.out.println("Descargando archivos de la carpeta...");



// Bucle que recorre un conjunto de elementos
            for (String archivo : ftpCliente.listNames()) {

                FileOutputStream fos = new FileOutputStream("descargas/" + archivo);

                ftpCliente.retrieveFile(archivo, fos);

                fos.close();

                System.out.println("Descargado: " + archivo);

            }



            ftpCliente.logout();

            ftpCliente.disconnect();



        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

}
