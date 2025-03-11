package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.HashMap;

// Importación de librerías necesarias para el funcionamiento
import java.util.Map;



// Clase principal que define la funcionalidad del programa
public class PassCommand implements FTPCommand {

    private static final Map<String, String> USERS = new HashMap<>();



    static {

        // Los mismos usuarios y contraseñas que definimos en UserCommand

        USERS.put("admin", "admin");

        USERS.put("user1", "password1");

        USERS.put("user2", "password2");

    }



    private String password;

    private String username;



    // Constructor con el nombre de usuario y la contraseña

    public PassCommand(String username, String argument) {

        this.username = username;

        this.password = argument;  // La contraseña proporcionada por el cliente

    }



    @Override

// Permite escribir datos de salida en una conexión
    public void execute(String[] args, PrintWriter salida) {

// Permite escribir datos de salida en una conexión
        PrintWriter writer = new PrintWriter(salida, true);  // Usamos PrintWriter para poder hacer println



// Condición que evalúa una expresión booleana
        if (password == null || password.isEmpty()) {

            writer.println("ERROR: Debes proporcionar una contraseña.");

            return;

        }



        // Verificamos si la contraseña corresponde al usuario

        String correctPassword = USERS.get(username);

// Condición que evalúa una expresión booleana
        if (correctPassword != null && correctPassword.equals(password)) {

            writer.println("Inicio de sesión correcto.");

        } else {

            writer.println("ERROR: Contraseña incorrecta.");

        }

    }

}
