// Manejo de sockets para la comunicación en red
package SocketBasicoTCPHilos;



// Importación de librerías necesarias para el funcionamiento
import java.io.BufferedReader;

// Importación de librerías necesarias para el funcionamiento
import java.io.FileOutputStream;

// Importación de librerías necesarias para el funcionamiento
import java.io.IOException;

// Importación de librerías necesarias para el funcionamiento
import java.io.InputStream;

// Importación de librerías necesarias para el funcionamiento
import java.io.InputStreamReader;

// Importación de librerías necesarias para el funcionamiento
import java.io.OutputStream;

// Importación de librerías necesarias para el funcionamiento
import java.io.OutputStreamWriter;

// Importación de librerías necesarias para el funcionamiento
import java.io.PrintWriter;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;



// Clase principal que define la funcionalidad del programa
public class ClienteTCP {



// Método principal que inicia la ejecución del programa
	public static void main(String[] args) {

		

		

		try {

			System.out.println("Soy el cliente");

// Manejo de sockets para la comunicación en red
			Socket s = new Socket("127.0.0.1", 22222);

			OutputStream os =s.getOutputStream();

			OutputStreamWriter osw = new OutputStreamWriter(os);

// Permite escribir datos de salida en una conexión
			PrintWriter pw = new PrintWriter(osw);

			pw.println("hola");	

			pw.flush();

			

			InputStream is = s.getInputStream();

			InputStreamReader isr = new InputStreamReader(is);

// Permite leer datos de entrada de manera eficiente
			BufferedReader br = new BufferedReader(isr);

			String linea=br.readLine();

			System.out.println(linea);

			br.close();

			is.close();

			pw.close();

			s.close();

			

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}



	}



}
