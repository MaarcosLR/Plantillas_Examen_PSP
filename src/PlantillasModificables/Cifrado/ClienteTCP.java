package PlantillasModificables.Cifrado;

import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void main(String[] args) {
        String servidor = "localhost";
        int puerto = 5000;
        try (Socket socket = new Socket(servidor, puerto);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("💬 Escriba su mensaje: ");
            String mensaje = teclado.readLine();
            salida.println(mensaje);
            System.out.println("📩 Respuesta del servidor: " + entrada.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

