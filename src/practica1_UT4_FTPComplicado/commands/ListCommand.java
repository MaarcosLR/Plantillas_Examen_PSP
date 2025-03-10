package practica1_UT4_FTPComplicado.commands;

import java.io.*;

public class ListCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        PrintWriter writer = new PrintWriter(salida, true);  // Usamos PrintWriter para poder hacer println

        File directorioActual = new File(System.getProperty("user.dir"));  // Usa el directorio de trabajo actual

        // Listar los archivos del directorio actual
        File[] archivos = directorioActual.listFiles();
        if (archivos != null) {
            if (archivos.length == 0) {
                writer.println("El directorio está vacío.");
            } else {
                // Concatenar todos los nombres de los archivos en una sola línea
                StringBuilder sb = new StringBuilder();
                for (File archivo : archivos) {
                    sb.append(archivo.getName()).append(" ");  // Añadir cada archivo separado por un espacio
                }
                writer.println(sb.toString().trim());  // Enviar toda la lista como una sola cadena
            }
        } else {
            writer.println("ERROR: No se pudo listar el directorio.");
        }
    }
}
