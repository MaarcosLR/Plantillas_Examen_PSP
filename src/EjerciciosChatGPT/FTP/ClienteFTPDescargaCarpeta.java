package EjerciciosChatGPT.FTP;
// Cliente FTP que descarga todos los archivos de una carpeta

import org.apache.commons.net.ftp.FTPClient;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClienteFTPDescargaCarpeta {
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
