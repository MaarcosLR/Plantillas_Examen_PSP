package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;

// Importación de librerías necesarias para el funcionamiento
import java.util.Base64;



// Clase principal que define la funcionalidad del programa
public class GetCommand implements FTPCommand {

// Manejo de sockets para la comunicación en red
    private Socket clientSocket;

// Permite leer datos de entrada de manera eficiente
    private BufferedReader input;



// Manejo de sockets para la comunicación en red
    public GetCommand(Socket clientSocket, BufferedReader input) {

// Manejo de sockets para la comunicación en red
        this.clientSocket = clientSocket;

        this.input = input;

    }



    @Override

// Permite escribir datos de salida en una conexión
    public void execute(String[] args, PrintWriter salida) {

// Condición que evalúa una expresión booleana
        if (args.length < 2) {

            salida.println("ERROR: Debes proporcionar un nombre de archivo.");

            return;

        }



        String nombreArchivo = args[1];

        File archivo = new File(nombreArchivo);



// Condición que evalúa una expresión booleana
        if (!archivo.exists() || !archivo.isFile()) {

            salida.println("ERROR: El archivo no existe.");

            return;

        }



// Permite leer datos de entrada de manera eficiente
        try (BufferedReader lectorArchivo = new BufferedReader(new FileReader(archivo))) {

            salida.println("OK: Enviando archivo " + nombreArchivo);



            String linea;

// Bucle que se ejecuta mientras la condición sea verdadera
            while ((linea = lectorArchivo.readLine()) != null) {

                // Codificar la línea en Base64 y enviarla

                String encodedLine = Base64.getEncoder().encodeToString(linea.getBytes());

                salida.println(encodedLine);

            }



            // Enviar "EOF" al final para indicar el fin del archivo

            salida.println("EOF");



            System.out.println("Archivo enviado correctamente: " + nombreArchivo);

        } catch (IOException e) {

            salida.println("ERROR: No se pudo leer el archivo.");

            e.printStackTrace();

        }

    }

}
