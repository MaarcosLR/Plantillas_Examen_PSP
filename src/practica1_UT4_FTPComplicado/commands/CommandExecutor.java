package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.PrintWriter;



// Clase principal que define la funcionalidad del programa
public class CommandExecutor {



// Permite escribir datos de salida en una conexión
    // Recibe OutputStream y lo convierte a PrintWriter dentro del método

// Permite escribir datos de salida en una conexión
    public void executeCommand(FTPCommand command, String[] args, PrintWriter salida) {

        try {

// Permite escribir datos de salida en una conexión
            PrintWriter writer = new PrintWriter(salida, true); // Convertimos a PrintWriter



            // Ejecutar el comando pasando los argumentos y la salida para la respuesta

            command.execute(args, writer);

        } catch (Exception e) {

            salida.println("ERROR: No se pudo ejecutar el comando.");

        }

    }

}
