package EjerciciosChatGPT.FTP;

// Cliente FTP para listar los archivos en el servidor



// Importación de librerías necesarias para el funcionamiento
import org.apache.commons.net.ftp.FTPClient;

// Importación de librerías necesarias para el funcionamiento
import java.io.IOException;



// Clase principal que define la funcionalidad del programa
public class ClienteFTPListaArchivos {

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



            System.out.println("Lista de archivos en el servidor:");

// Bucle que recorre un conjunto de elementos
            for (String archivo : ftpCliente.listNames()) {

                System.out.println(archivo);

            }



            ftpCliente.logout();

            ftpCliente.disconnect();



        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

}
