package PlantillasModificables.TodoJunto;

import java.io.*;
import java.net.*;

public class ServidorFTP {
    public static void main(String[] args) {
        int puerto = 2121;
        String carpetaServidor = "servidor_archivos/";
        File directorio = new File(carpetaServidor);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("📂 Servidor FTP esperando en el puerto " + puerto);

            while (true) {
                Socket socket = servidor.accept();
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                File[] archivos = directorio.listFiles();
                if (archivos != null) {
                    for (File archivo : archivos) {
                        salida.println(archivo.getName());
                    }
                }
                salida.println("END");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

