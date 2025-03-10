package practica1_UT4_FTPComplicado.commands;

import java.io.*;

public class QuitCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        PrintWriter writer = new PrintWriter(salida, true);

        writer.println("Desconectando... ¡Adiós!");
    }
}
