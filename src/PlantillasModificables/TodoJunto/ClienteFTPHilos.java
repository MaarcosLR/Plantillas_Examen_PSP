package PlantillasModificables.TodoJunto;

import java.io.*;
import java.net.*;

public class ClienteFTPHilos {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 2121;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            salida.println("LIST");
            System.out.println("📂 Archivos en el servidor:");

            String archivo;
            while ((archivo = entrada.readLine()) != null && !archivo.equals("END")) {
                System.out.println("📄 " + archivo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

