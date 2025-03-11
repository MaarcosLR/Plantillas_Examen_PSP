// Manejo de sockets para la comunicación en red
package SocketBasicoTCPHilos;





// Importación de librerías necesarias para el funcionamiento
import java.io.IOException;

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

				HiloServidor hs = new HiloServidor(cliente);

				hs.start();

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
