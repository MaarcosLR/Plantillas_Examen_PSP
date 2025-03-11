package PlantillasModificables.Cifrado;

import java.io.*;
import java.net.*;

public class ClienteFTP {
    public static void main(String[] args) {
        String servidor = "localhost";
        int puerto = 2121;

        try (Socket socket = new Socket(servidor, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

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

