package EjerciciosChatGPT.TCP;
// Cliente TCP para enviar dos números y recibir la suma

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteSumaNumerosTCP {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6002);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            Scanner sc = new Scanner(System.in);

            System.out.print("Introduce el primer número: ");
            int num1 = sc.nextInt();
            System.out.print("Introduce el segundo número: ");
            int num2 = sc.nextInt();

            // Enviar los números
            salida.println(num1);
            salida.println(num2);

            // Recibir y mostrar la suma
            String resultado = entrada.readLine();
            System.out.println("Resultado de la suma: " + resultado);

            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
