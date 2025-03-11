// Paquete donde se encuentran los archivos
package EjerciciosChatGPT.TCP;

import java.io.*;
import java.net.*;

// Servidor TCP que realiza operaciones matemáticas básicas
public class ServidorControladoCalculadoraTCP {
    public static void main(String[] args) {
        int puerto = 5000; // Puerto donde el servidor escuchará las conexiones
        boolean ejecutando = true; // Variable para controlar la ejecución del servidor

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor de calculadora esperando conexiones en el puerto " + puerto);

            while (ejecutando) {
                Socket socket = servidor.accept(); // Acepta la conexión del cliente
                System.out.println("Cliente conectado desde " + socket.getInetAddress());

                // Flujos de entrada y salida
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                // Recibir operación del cliente
                String operacion = entrada.readLine();
                System.out.println("Operación recibida: " + operacion);

                if ("SALIR".equalsIgnoreCase(operacion)) { // Si el cliente envía "SALIR", se detiene el servidor
                    System.out.println("Se recibió la señal de cierre. Apagando servidor...");
                    salida.println("Servidor apagándose...");
                    ejecutando = false;
                } else {
                    try {
                        double resultado = calcular(operacion);
                        salida.println("Resultado: " + resultado);
                    } catch (Exception e) {
                        salida.println("Error en la operación");
                    }
                }

                socket.close(); // Cerrar conexión con el cliente
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    // Método que interpreta y calcula la operación recibida
    private static double calcular(String operacion) throws Exception {
        String[] partes = operacion.split(" ");
        if (partes.length != 3) throw new Exception("Formato incorrecto");

        double num1 = Double.parseDouble(partes[0]);
        double num2 = Double.parseDouble(partes[2]);
        String operador = partes[1];

        return switch (operador) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new Exception("Operador no válido");
        };
    }
}