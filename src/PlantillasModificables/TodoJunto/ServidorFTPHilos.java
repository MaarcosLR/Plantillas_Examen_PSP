package PlantillasModificables.TodoJunto;

import java.io.*;
import java.net.*;

public class ServidorFTPHilos {
    private static final int PUERTO = 2121;
    private static final String CARPETA_SERVIDOR = "servidor_archivos/";

    public static void main(String[] args) {
        File directorio = new File(CARPETA_SERVIDOR);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("📂 Servidor FTP esperando conexiones en el puerto " + PUERTO);
            while (true) {
                Socket socket = servidor.accept();
                new Thread(new ClientHandlerFTP(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandlerFTP implements Runnable {
    private Socket socket;

    public ClientHandlerFTP(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            String comando = entrada.readLine();
            if ("LIST".equalsIgnoreCase(comando)) {
                File[] archivos = new File("servidor_archivos/").listFiles();
                if (archivos != null) {
                    for (File archivo : archivos) {
                        salida.println(archivo.getName());
                    }
                }
                salida.println("END");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

