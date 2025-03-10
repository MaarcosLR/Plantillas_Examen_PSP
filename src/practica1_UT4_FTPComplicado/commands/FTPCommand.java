package practica1_UT4_FTPComplicado.commands;

import java.io.PrintWriter;

public interface FTPCommand {
    void execute(String[] args, PrintWriter salida);
}
