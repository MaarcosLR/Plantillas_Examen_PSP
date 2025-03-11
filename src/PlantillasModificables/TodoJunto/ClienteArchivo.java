package PlantillasModificables.TodoJunto;

import java.io.*;
import java.net.*;

public class ClienteArchivo {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 5001;
    private static final char CLAVE_XOR = 'K';

    public static void main(String[] args) {
        String archivoEntrada = "ejemplo.txt";
        String archivoCifrado = "ejemplo_cifrado.txt";

        try {
            cifrarArchivoXOR(archivoEntrada, archivoCifrado);
            enviarArchivo(archivoCifrado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cifrarArchivoXOR(String rutaEntrada, String rutaSalida) throws IOException {
        try (FileInputStream fis = new FileInputStream(rutaEntrada);
             FileOutputStream fos = new FileOutputStream(rutaSalida)) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b ^ CLAVE_XOR);
            }
        }
        System.out.println("🔒 Archivo cifrado y guardado como: " + rutaSalida);
    }

    private static void enviarArchivo(String archivo) throws IOException {
        Socket socket = new Socket(SERVIDOR, PUERTO);
        OutputStream salida = socket.getOutputStream();
        FileInputStream fis = new FileInputStream(archivo);

        byte[] buffer = new byte[1024];
        int bytesLeidos;
        while ((bytesLeidos = fis.read(buffer)) > 0) {
            salida.write(buffer, 0, bytesLeidos);
        }

        fis.close();
        salida.close();
        socket.close();
        System.out.println("📤 Archivo enviado al servidor.");
    }
}

