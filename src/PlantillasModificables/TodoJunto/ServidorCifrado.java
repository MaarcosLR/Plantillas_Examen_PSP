package PlantillasModificables.TodoJunto;

import java.io.*;
import java.net.*;

public class ServidorCifrado {
    private static final int PUERTO = 5000; // Puerto del servidor
    private static final char CLAVE_XOR = 'K'; // Clave de cifrado

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("🔹 Servidor esperando conexiones en el puerto " + PUERTO + "...");

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("✅ Cliente conectado desde: " + socket.getInetAddress());

                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                String mensajeCifrado = entrada.readLine();
                System.out.println("📩 Mensaje cifrado recibido: " + mensajeCifrado);

                String mensajeDescifrado = descifrarXOR(mensajeCifrado, CLAVE_XOR);
                System.out.println("🔓 Mensaje descifrado: " + mensajeDescifrado);

                salida.println("✅ Mensaje recibido y descifrado correctamente.");

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para descifrar usando XOR
    public static String descifrarXOR(String texto, char clave) {
        StringBuilder descifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            descifrado.append((char) (c ^ clave));
        }
        return descifrado.toString();
    }
}

