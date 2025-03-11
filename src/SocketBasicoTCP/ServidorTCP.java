// Manejo de sockets para la comunicación en red
package SocketBasicoTCP;



// Importación de librerías necesarias para el funcionamiento
import java.io.BufferedReader;

// Importación de librerías necesarias para el funcionamiento
import java.io.FileReader;

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
import java.net.ServerSocket;

// Importación de librerías necesarias para el funcionamiento
import java.net.Socket;



// Clase principal que define la funcionalidad del programa
public class ServidorTCP {



// Método principal que inicia la ejecución del programa
	public static void main(String[] args) {

// Manejo de sockets para la comunicación en red
		ServerSocket ss=null;

		try {

			System.out.println("Soy el servidor");

// Manejo de sockets para la comunicación en red
			 ss = new ServerSocket(22222);

// Bucle que se ejecuta mientras la condición sea verdadera
			while (true) {

// Manejo de sockets para la comunicación en red
				Socket cliente=ss.accept();		

				System.out.println("Conexión establecida");

				InputStream is= cliente.getInputStream();

				InputStreamReader isr = new InputStreamReader(is);

// Permite leer datos de entrada de manera eficiente
				BufferedReader br = new BufferedReader(isr);

				String linea=br.readLine();

				System.out.println(linea);

				

				OutputStream os =cliente.getOutputStream();

				OutputStreamWriter osw = new OutputStreamWriter(os);

// Permite escribir datos de salida en una conexión
				PrintWriter pw = new PrintWriter(osw);

				pw.println("mundo!");

				pw.flush();

				pw.close();

				os.close();

				is.close();

				

			

				}	

			

		} catch (IOException e) {

			e.printStackTrace();

		}

		try {

			ss.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

		



	}



}
