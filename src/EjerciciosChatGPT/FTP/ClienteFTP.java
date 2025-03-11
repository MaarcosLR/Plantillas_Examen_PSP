package EjerciciosChatGPT.FTP;

// Cliente FTP para subir y descargar archivos desde un servidor



// Importación de librerías necesarias para el funcionamiento
import org.apache.commons.net.ftp.FTP;

// Importación de librerías necesarias para el funcionamiento
import org.apache.commons.net.ftp.FTPClient;

// Importación de librerías necesarias para el funcionamiento
import java.io.File;

// Importación de librerías necesarias para el funcionamiento
import java.io.FileInputStream;

// Importación de librerías necesarias para el funcionamiento
import java.io.FileOutputStream;

// Importación de librerías necesarias para el funcionamiento
import java.io.IOException;



// Clase principal que define la funcionalidad del programa
public class ClienteFTP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) {

        String servidor = "localhost";

        int puerto = 21;

        String usuario = "usuario";

        String clave = "clave";



        FTPClient ftpCliente = new FTPClient();



        try {

            // Conectar al servidor FTP

            ftpCliente.connect(servidor, puerto);

            ftpCliente.login(usuario, clave);

            ftpCliente.enterLocalPassiveMode();

            ftpCliente.setFileType(FTP.BINARY_FILE_TYPE);



            // Subir un archivo

            String archivoLocal = "subir.txt";

            File archivo = new File(archivoLocal);

            FileInputStream fis = new FileInputStream(archivo);

            boolean subido = ftpCliente.storeFile("servidor.txt", fis);

            fis.close();

            System.out.println("Archivo subido: " + subido);



            // Descargar un archivo

            File archivoDescargado = new File("descargado.txt");

            FileOutputStream fos = new FileOutputStream(archivoDescargado);

            boolean descargado = ftpCliente.retrieveFile("servidor.txt", fos);

            fos.close();

            System.out.println("Archivo descargado: " + descargado);



            // Cerrar conexión

            ftpCliente.logout();

            ftpCliente.disconnect();



        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

}
