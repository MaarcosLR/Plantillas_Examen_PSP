package practica1_UT4_FTPComplicado;

import java.io.*;
import java.net.Socket;

public class FTPClient {
    private static final String SERVER_IP = "localhost";
    private static final int PORT = 21;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, PORT);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Conectado al ServidorFTP en el puerto: " + PORT);

            // Leer el mensaje inicial del servidor
            String mensajeServidor = entrada.readLine();
            System.out.println("Servidor: " + mensajeServidor);

            // Crear un flujo para leer comandos desde la terminal
            BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

            String comando;
            while (true) {
                // Leer un comando de la terminal
                System.out.print("Ingresa un comando: ");
                comando = terminalReader.readLine();

                // Enviar el comando al servidor
                salida.println(comando);

                // Leer la respuesta del servidor
                String respuestaServidor = entrada.readLine();
                System.out.println("Servidor: " + respuestaServidor);

                // Si el comando es QUIT, cerramos la conexión
                if (comando.equalsIgnoreCase("QUIT")) {
                    break;
                }

                // Si el comando es GET, manejar la descarga del archivo
                if (comando.startsWith("GET")) {
                    String archivoDestino = "descargado_" + comando.split(" ")[1]; // Usamos el nombre original del archivo
                    try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(archivoDestino))) {
                        byte[] buffer = new byte[4096];  // Buffer para leer los datos en bloques
                        int bytesLeidos;

                        // Leer el archivo desde el servidor y escribirlo en el archivo local
                        while ((bytesLeidos = entrada.read()) != -1) {
                            fos.write(buffer, 0, bytesLeidos);
                        }

                        System.out.println("Archivo descargado: " + archivoDestino);
                    } catch (IOException e) {
                        System.err.println("Error al recibir el archivo: " + e.getMessage());
                    }
                }
            }

            // Cerrar la conexión al final
            salida.println("QUIT");
            System.out.println("Conexión cerrada.");

        } catch (IOException e) {
            System.err.println("Error al conectar al ServidorFTP: " + e.getMessage());
        }
    }
}
