package practica1_UT4_FTPComplicado.commands;

import java.io.PrintWriter;

public class CommandExecutor {

    // Recibe OutputStream y lo convierte a PrintWriter dentro del método
    public void executeCommand(FTPCommand command, String[] args, PrintWriter salida) {
        try {
            PrintWriter writer = new PrintWriter(salida, true); // Convertimos a PrintWriter

            // Ejecutar el comando pasando los argumentos y la salida para la respuesta
            command.execute(args, writer);
        } catch (Exception e) {
            salida.println("ERROR: No se pudo ejecutar el comando.");
        }
    }
}
