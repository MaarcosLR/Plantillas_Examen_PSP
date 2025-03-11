package EjerciciosChatGPT.FTP;

import java.io.*;
import java.net.Socket;

public class ClienteFTPDescargaCarpeta {

    public static void main(String[] args) {
        String servidor = "localhost"; // Dirección del servidor
        int puerto = 2123; // Puerto de conexión
        String carpetaDescarga = "descargas/";

        File directorio = new File(carpetaDescarga);
        if (!directorio.exists()) {
            directorio.mkdirs(); // Crear carpeta si no existe
        }

        try (Socket socket = new Socket(servidor, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            salida.println("LIST"); // Enviar comando para listar archivos
            String archivo;
            while ((archivo = entrada.readLine()) != null && !archivo.equals("END")) {
                descargarArchivo(servidor, archivo, carpetaDescarga);
            }

            System.out.println("✅ Descarga completa.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void descargarArchivo(String servidor, String nombreArchivo, String carpetaDescarga) {
        int puertoDescarga = 2124; // Puerto para descargar archivos

        try (Socket socket = new Socket(servidor, puertoDescarga);
             InputStream entrada = socket.getInputStream();
             FileOutputStream fos = new FileOutputStream(carpetaDescarga + nombreArchivo)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;
            while ((bytesLeidos = entrada.read(buffer)) > 0) {
                fos.write(buffer, 0, bytesLeidos);
            }

            System.out.println("📥 Archivo descargado: " + nombreArchivo);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
