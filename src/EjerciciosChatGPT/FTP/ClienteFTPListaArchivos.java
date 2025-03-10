package EjerciciosChatGPT.FTP;
// Cliente FTP para listar los archivos en el servidor

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class ClienteFTPListaArchivos {
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
