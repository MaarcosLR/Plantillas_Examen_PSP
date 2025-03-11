package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.File;

// Importación de librerías necesarias para el funcionamiento
import java.io.PrintWriter;



// Clase principal que define la funcionalidad del programa
public class PwdCommand implements FTPCommand {

    @Override

// Permite escribir datos de salida en una conexión
    public void execute(String[] args, PrintWriter salida) {

        File directorioActual = new File(".");

        salida.println("Directorio actual: " + directorioActual.getAbsolutePath());

    }

}
