package EjerciciosChatGPT.UDP;

// Servidor UDP para calculadora

// Recibe una operación, la evalúa y devuelve el resultado



// Importación de librerías necesarias para el funcionamiento
import java.net.*;



// Clase principal que define la funcionalidad del programa
public class ServidorCalculadoraUDP {

// Método principal que inicia la ejecución del programa
    public static void main(String[] args) throws Exception {

// Manejo de sockets para la comunicación en red
        DatagramSocket socket = new DatagramSocket(5556);

        byte[] buffer = new byte[1024];



// Bucle que se ejecuta mientras la condición sea verdadera
        while (true) {

            // Recibir operación del cliente

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);

            String operacion = new String(packet.getData(), 0, packet.getLength()).trim();

            String resultado;



            try {

                resultado = String.valueOf(eval(operacion));

            } catch (Exception e) {

                resultado = "Operación inválida";

            }



            // Enviar resultado al cliente

            byte[] resultadoBytes = resultado.getBytes();

            DatagramPacket respuestaPacket = new DatagramPacket(

                resultadoBytes, resultadoBytes.length, packet.getAddress(), packet.getPort());

            socket.send(respuestaPacket);

        }

    }



    // Método para evaluar la operación matemática

    public static double eval(final String str) {

        return new Object() {

            int pos = -1, ch;



            void nextChar() {

                ch = (++pos < str.length()) ? str.charAt(pos) : -1;

            }



            boolean eat(int charToEat) {

// Bucle que se ejecuta mientras la condición sea verdadera
                while (ch == ' ') nextChar();

// Condición que evalúa una expresión booleana
                if (ch == charToEat) {

                    nextChar();

                    return true;

                }

                return false;

            }



            double parse() {

                nextChar();

                double x = parseExpression();

// Condición que evalúa una expresión booleana
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);

                return x;

            }



            double parseExpression() {

                double x = parseTerm();

// Bucle que recorre un conjunto de elementos
                for (;;) {

// Condición que evalúa una expresión booleana
                    if (eat('+')) x += parseTerm(); // suma

// Bloque de ejecución alternativa si la condición es falsa
                    else if (eat('-')) x -= parseTerm(); // resta

// Bloque de ejecución alternativa si la condición es falsa
                    else return x;

                }

            }



            double parseTerm() {

                double x = parseFactor();

// Bucle que recorre un conjunto de elementos
                for (;;) {

// Condición que evalúa una expresión booleana
                    if (eat('*')) x *= parseFactor(); // multiplicación

// Bloque de ejecución alternativa si la condición es falsa
                    else if (eat('/')) x /= parseFactor(); // división

// Bloque de ejecución alternativa si la condición es falsa
                    else return x;

                }

            }



            double parseFactor() {

// Condición que evalúa una expresión booleana
                if (eat('+')) return parseFactor(); // operador unario positivo

// Condición que evalúa una expresión booleana
                if (eat('-')) return -parseFactor(); // operador unario negativo



                double x;

                int startPos = this.pos;

// Condición que evalúa una expresión booleana
                if (eat('(')) { // paréntesis

                    x = parseExpression();

                    eat(')');

                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // números

// Bucle que se ejecuta mientras la condición sea verdadera
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();

                    x = Double.parseDouble(str.substring(startPos, this.pos));

                } else {

                    throw new RuntimeException("Unexpected: " + (char)ch);

                }

                return x;

            }

        }.parse();

    }

}
