package PlantillasModificables.Cifrado;
import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.Scanner;

public class ClienteCifrado {
    private static final String SERVIDOR = "localhost"; // Dirección del servidor
    private static final int PUERTO = 5000; // Puerto del servidor
    private static final char CLAVE_XOR = 'K'; // Clave de cifrado

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("💬 Ingresa el mensaje a cifrar y enviar: ");
        String mensaje = scanner.nextLine();

        String mensajeCifrado = cifrarXOR(mensaje, CLAVE_XOR);
        System.out.println("🔒 Mensaje cifrado: " + mensajeCifrado);

        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            salida.println(mensajeCifrado);

            String respuesta = entrada.readLine();
            System.out.println("📩 Respuesta del servidor: " + respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cifrar usando XOR
    public static String cifrarXOR(String texto, char clave) {
        StringBuilder cifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            cifrado.append((char) (c ^ clave));
        }
        return cifrado.toString();
    }
}

