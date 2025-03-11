// Manejo de sockets para la comunicación en red
package SocketBasicoTCPHilos;



// Importación de librerías necesarias para el funcionamiento
import java.io.BufferedReader;

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
public class HiloServidor extends Thread {

	

	

// Manejo de sockets para la comunicación en red
	Socket cliente;



// Manejo de sockets para la comunicación en red
	public HiloServidor(Socket cliente) {

		super();

		this.cliente = cliente;

	}

	

	public void run() {

		

		System.out.println("Conexión establecida");

		InputStream is;

		try {

			is = cliente.getInputStream();

		

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

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}



}
