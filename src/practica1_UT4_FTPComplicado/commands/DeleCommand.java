package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.*;



// Clase principal que define la funcionalidad del programa
public class DeleCommand implements FTPCommand {

    @Override

// Permite escribir datos de salida en una conexión
    public void execute(String[] args, PrintWriter salida) {

// Permite escribir datos de salida en una conexión
        PrintWriter writer = new PrintWriter(salida, true);  // Usamos PrintWriter para poder hacer println



// Condición que evalúa una expresión booleana
        if (args.length < 2) {

            writer.println("ERROR: Debes proporcionar un nombre de archivo.");

            return;

        }



        File archivo = new File(args[1]);

// Condición que evalúa una expresión booleana
        if (archivo.exists() && archivo.isFile()) {

// Condición que evalúa una expresión booleana
            if (archivo.delete()) {

                writer.println("Archivo " + args[1] + " eliminado con éxito.");

            } else {

                writer.println("ERROR: No se pudo eliminar el archivo.");

            }

        } else {

            writer.println("ERROR: El archivo no existe.");

        }

    }

}
