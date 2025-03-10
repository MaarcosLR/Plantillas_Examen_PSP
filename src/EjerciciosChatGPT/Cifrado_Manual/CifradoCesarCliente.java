package EjerciciosChatGPT.Cifrado_Manual;
// Cliente TCP que cifra un mensaje con el cifrado César antes de enviarlo

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CifradoCesarCliente {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 7200);
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce mensaje: ");
        String mensaje = sc.nextLine();
        String mensajeCifrado = cifrarCesar(mensaje, 3);

        salida.println(mensajeCifrado);
        System.out.println("Mensaje cifrado enviado: " + mensajeCifrado);

        socket.close();
        sc.close();
    }

    public static String cifrarCesar(String texto, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                resultado.append((char) ((c - base + desplazamiento) % 26 + base));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }
}
