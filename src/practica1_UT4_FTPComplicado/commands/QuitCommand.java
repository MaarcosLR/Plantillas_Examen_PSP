package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.*;



// Clase principal que define la funcionalidad del programa
public class QuitCommand implements FTPCommand {

    @Override

// Permite escribir datos de salida en una conexión
    public void execute(String[] args, PrintWriter salida) {

// Permite escribir datos de salida en una conexión
        PrintWriter writer = new PrintWriter(salida, true);



        writer.println("Desconectando... ¡Adiós!");

    }

}
