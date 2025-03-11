package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.*;



// Clase principal que define la funcionalidad del programa
public class CwdCommand implements FTPCommand {

    @Override

// Permite escribir datos de salida en una conexión
    public void execute(String[] args, PrintWriter salida) {

// Permite escribir datos de salida en una conexión
        PrintWriter writer = new PrintWriter(salida, true);  // Usamos PrintWriter para poder hacer println



// Condición que evalúa una expresión booleana
        if (args.length < 2) {

            writer.println("ERROR: Debes proporcionar un directorio.");

            return;

        }



        File nuevoDirectorio = new File(args[1]);

// Condición que evalúa una expresión booleana
        if (nuevoDirectorio.isDirectory() && nuevoDirectorio.exists()) {

            // Cambiar el directorio de trabajo para el servidor

            System.setProperty("user.dir", nuevoDirectorio.getAbsolutePath());  // Cambia la propiedad de directorio

            writer.println("Directorio cambiado a: " + nuevoDirectorio.getAbsolutePath());

        } else {

            writer.println("ERROR: No se pudo cambiar al directorio especificado.");

        }

    }

}
