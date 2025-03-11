package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;

// Importación de librerías necesarias para el funcionamiento
import java.util.Base64;



// Clase principal que define la funcionalidad del programa
public class PutCommand implements FTPCommand {

// Manejo de sockets para la comunicación en red
    private Socket clientSocket;

// Permite leer datos de entrada de manera eficiente
    private BufferedReader input;



// Manejo de sockets para la comunicación en red
    public PutCommand(Socket clientSocket, BufferedReader input) {

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



        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {

            salida.println("OK: Esperando contenido del archivo...");



            String linea;

// Bucle que se ejecuta mientras la condición sea verdadera
            while ((linea = input.readLine()) != null) {

// Condición que evalúa una expresión booleana
                if ("EOF".equals(linea)) {

                    break;

                }



                // Decodificar la línea de Base64

                byte[] decodedBytes = Base64.getDecoder().decode(linea);

                String decodedString = new String(decodedBytes);



                // Escribir la línea decodificada en el archivo

                escritor.write(decodedString);

                escritor.newLine();

            }



            salida.println("OK: Archivo recibido correctamente.");

        } catch (IOException e) {

            salida.println("ERROR: No se pudo guardar el archivo.");

            e.printStackTrace();

        }

    }

}
