package practica1_UT4_FTPComplicado.commands;

import java.io.*;

public class DeleCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        PrintWriter writer = new PrintWriter(salida, true);  // Usamos PrintWriter para poder hacer println

        if (args.length < 2) {
            writer.println("ERROR: Debes proporcionar un nombre de archivo.");
            return;
        }

        File archivo = new File(args[1]);
        if (archivo.exists() && archivo.isFile()) {
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
