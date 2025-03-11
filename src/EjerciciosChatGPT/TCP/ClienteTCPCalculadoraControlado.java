package EjerciciosChatGPT.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteTCPCalculadoraControlado {
    public static void main(String[] args) {
        String servidor = "localhost"; // Dirección del servidor
        int puerto = 5000; // Puerto del servidor

        try (Socket socket = new Socket(servidor, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor de calculadora");

            while (true) {
                System.out.print("Introduce operación (ej. 5 + 3) o escribe 'SALIR' para cerrar el servidor: ");
                String operacion = teclado.readLine(); // Leer la operación desde la consola

                salida.println(operacion); // Enviar operación al servidor
                String respuesta = entrada.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);

                if ("Servidor apagándose...".equals(respuesta)) {
                    System.out.println("El servidor ha cerrado la conexión.");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
