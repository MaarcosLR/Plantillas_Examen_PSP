package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.*;

// Importación de librerías necesarias para el funcionamiento
import java.util.HashMap;

// Importación de librerías necesarias para el funcionamiento
import java.util.Map;



// Clase principal que define la funcionalidad del programa
public class UserCommand implements FTPCommand {

    private static final Map<String, String> USERS = new HashMap<>();



    static {

        // Definimos varios usuarios con sus contraseñas

        USERS.put("admin", "admin");

        USERS.put("user1", "password1");

        USERS.put("user2", "password2");

    }



    private String username;



    public UserCommand(String argument) {

        this.username = argument;  // El nombre de usuario ingresado

    }



    @Override

// Permite escribir datos de salida en una conexión
    public void execute(String[] args, PrintWriter salida) {

// Permite escribir datos de salida en una conexión
        PrintWriter writer = new PrintWriter(salida, true);  // Usamos PrintWriter para poder hacer println



// Condición que evalúa una expresión booleana
        if (username == null || username.isEmpty()) {

            writer.println("ERROR: Debes proporcionar un nombre de usuario.");

            return;

        }



        // Verificamos si el nombre de usuario existe en el conjunto predefinido

// Condición que evalúa una expresión booleana
        if (USERS.containsKey(username)) {

            writer.println("Usuario " + username + " recibido. Introduce PASS.");

        } else {

            writer.println("ERROR: Usuario incorrecto.");

        }

    }



    // Getter para obtener el nombre de usuario

    public String getUsername() {

        return username;

    }

}
