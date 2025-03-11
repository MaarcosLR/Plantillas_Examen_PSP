package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.*;



// Clase principal que define la funcionalidad del programa
public class ListCommand implements FTPCommand {

    @Override

// Permite escribir datos de salida en una conexión
    public void execute(String[] args, PrintWriter salida) {

// Permite escribir datos de salida en una conexión
        PrintWriter writer = new PrintWriter(salida, true);  // Usamos PrintWriter para poder hacer println



        File directorioActual = new File(System.getProperty("user.dir"));  // Usa el directorio de trabajo actual



        // Listar los archivos del directorio actual

        File[] archivos = directorioActual.listFiles();

// Condición que evalúa una expresión booleana
        if (archivos != null) {

// Condición que evalúa una expresión booleana
            if (archivos.length == 0) {

                writer.println("El directorio está vacío.");

            } else {

                // Concatenar todos los nombres de los archivos en una sola línea

                StringBuilder sb = new StringBuilder();

// Bucle que recorre un conjunto de elementos
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
