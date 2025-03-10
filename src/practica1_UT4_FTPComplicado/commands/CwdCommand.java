package practica1_UT4_FTPComplicado.commands;

import java.io.*;

public class CwdCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        PrintWriter writer = new PrintWriter(salida, true);  // Usamos PrintWriter para poder hacer println

        if (args.length < 2) {
            writer.println("ERROR: Debes proporcionar un directorio.");
            return;
        }

        File nuevoDirectorio = new File(args[1]);
        if (nuevoDirectorio.isDirectory() && nuevoDirectorio.exists()) {
            // Cambiar el directorio de trabajo para el servidor
            System.setProperty("user.dir", nuevoDirectorio.getAbsolutePath());  // Cambia la propiedad de directorio
            writer.println("Directorio cambiado a: " + nuevoDirectorio.getAbsolutePath());
        } else {
            writer.println("ERROR: No se pudo cambiar al directorio especificado.");
        }
    }
}
