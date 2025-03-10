package EjerciciosChatGPT.TCP;
// Cliente TCP para solicitar la fecha y hora del servidor

import java.io.*;
import java.net.*;

public class ClienteTiempoTCP {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6001);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
