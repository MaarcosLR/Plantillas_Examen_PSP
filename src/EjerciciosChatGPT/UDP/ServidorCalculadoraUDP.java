package EjerciciosChatGPT.UDP;
// Servidor UDP para calculadora
// Recibe una operación, la evalúa y devuelve el resultado

import java.net.*;

public class ServidorCalculadoraUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5556);
        byte[] buffer = new byte[1024];

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
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm(); // suma
                    else if (eat('-')) x -= parseTerm(); // resta
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor(); // multiplicación
                    else if (eat('/')) x /= parseFactor(); // división
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // operador unario positivo
                if (eat('-')) return -parseFactor(); // operador unario negativo

                double x;
                int startPos = this.pos;
                if (eat('(')) { // paréntesis
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // números
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
