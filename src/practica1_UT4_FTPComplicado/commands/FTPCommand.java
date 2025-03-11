package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.PrintWriter;



public interface FTPCommand {

// Permite escribir datos de salida en una conexión
    void execute(String[] args, PrintWriter salida);

}
