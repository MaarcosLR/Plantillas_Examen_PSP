package practica1_UT4_FTPComplicado.commands;



// Importación de librerías necesarias para el funcionamiento
import java.io.BufferedReader;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;

// Importación de librerías necesarias para el funcionamiento
import java.util.HashMap;

// Importación de librerías necesarias para el funcionamiento
import java.util.Map;



// Clase principal que define la funcionalidad del programa
public class CommandParser {

    private final Map<String, Class<? extends FTPCommand>> commandMap = new HashMap<>();

    private String username;



    public CommandParser() {

        commandMap.put("USER", UserCommand.class);

        commandMap.put("PASS", PassCommand.class);

        commandMap.put("LIST", ListCommand.class);

        commandMap.put("PWD", PwdCommand.class);

        commandMap.put("CWD", CwdCommand.class);

        commandMap.put("GET", GetCommand.class);

        commandMap.put("PUT", PutCommand.class);

        commandMap.put("DELE", DeleCommand.class);

        commandMap.put("QUIT", QuitCommand.class);

    }



// Manejo de sockets para la comunicación en red
    public FTPCommand getCommand(String commandName, String argument, Socket clientSocket, BufferedReader input) {

        Class<? extends FTPCommand> commandClass = commandMap.get(commandName.toUpperCase());

// Condición que evalúa una expresión booleana
        if (commandClass == null) {

            return null;

        }



        try {

// Condición que evalúa una expresión booleana
            if (commandClass == UserCommand.class) {

                return new UserCommand(argument); // Solo el argumento para USER

            } else if (commandClass == PassCommand.class) {

// Condición que evalúa una expresión booleana
                if (username == null) {

                    return null;  // Si no hay un usuario válido, no procesamos el comando PASS

                }

                return new PassCommand(username, argument);  // Se pasa el nombre de usuario y la contraseña

            } else if (commandClass == GetCommand.class || commandClass == PutCommand.class) {

// Manejo de sockets para la comunicación en red
                // Para comandos que requieren Socket y BufferedReader

// Manejo de sockets para la comunicación en red
                return commandClass.getDeclaredConstructor(Socket.class, BufferedReader.class)

// Manejo de sockets para la comunicación en red
                        .newInstance(clientSocket, input);

            }

            return commandClass.getDeclaredConstructor().newInstance();  // Para comandos sin parámetros

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }



    public void setUsername(String username) {

        this.username = username;

    }



    public String getUsername() {

        return username;

    }

}
