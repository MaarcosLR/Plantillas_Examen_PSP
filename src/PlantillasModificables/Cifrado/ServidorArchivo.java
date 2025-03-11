package PlantillasModificables.Cifrado;

import java.io.*;
import java.net.*;

public class ServidorArchivo {
    private static final int PUERTO = 5001;
    private static final char CLAVE_XOR = 'K';

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("📥 Servidor de archivos esperando en el puerto " + PUERTO + "...");

            while (true) {
                Socket socket = servidor.accept();
                recibirArchivo(socket, "archivo_recibido.txt");
                descifrarArchivoXOR("archivo_recibido.txt", "archivo_descifrado.txt");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recibirArchivo(Socket socket, String archivoSalida) throws IOException {
        InputStream entrada = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(archivoSalida);

        byte[] buffer = new byte[1024];
        int bytesLeidos;
        while ((bytesLeidos = entrada.read(buffer)) > 0) {
            fos.write(buffer, 0, bytesLeidos);
        }

        fos.close();
        System.out.println("✅ Archivo recibido y guardado como: " + archivoSalida);
    }

    private static void descifrarArchivoXOR(String rutaEntrada, String rutaSalida) throws IOException {
        try (FileInputStream fis = new FileInputStream(rutaEntrada);
             FileOutputStream fos = new FileOutputStream(rutaSalida)) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b ^ CLAVE_XOR);
            }
        }
        System.out.println("🔓 Archivo descifrado y guardado como: " + rutaSalida);
    }
}

