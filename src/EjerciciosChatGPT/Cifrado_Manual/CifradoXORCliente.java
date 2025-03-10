package EjerciciosChatGPT.Cifrado_Manual;
// Cliente TCP que cifra un mensaje usando XOR antes de enviarlo

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CifradoXORCliente {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 7300);
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce mensaje: ");
        String mensaje = sc.nextLine();
        String mensajeCifrado = cifrarXOR(mensaje, "clave");

        salida.println(mensajeCifrado);
        System.out.println("Mensaje cifrado enviado: " + mensajeCifrado);

        socket.close();
        sc.close();
    }

    public static String cifrarXOR(String texto, String clave) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            resultado.append((char) (texto.charAt(i) ^ clave.charAt(i % clave.length())));
        }
        return resultado.toString();
    }
}
