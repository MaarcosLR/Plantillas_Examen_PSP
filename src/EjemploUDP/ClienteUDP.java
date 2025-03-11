package EjemploUDP;



// Importación de librerías necesarias para el funcionamiento
import java.io.IOException;

// Importación de librerías necesarias para el funcionamiento
import java.net.DatagramPacket;

// Importación de librerías necesarias para el funcionamiento
import java.net.DatagramSocket;

// Importación de librerías necesarias para el funcionamiento
import java.net.InetAddress;

// Importación de librerías necesarias para el funcionamiento
import java.net.UnknownHostException;



// Clase principal que define la funcionalidad del programa
public class ClienteUDP {



// Método principal que inicia la ejecución del programa
	public static void main(String[] args) {

// Manejo de sockets para la comunicación en red
		DatagramSocket ds=null; 

	    String str = "Welcome java";  

	    InetAddress ip;

		try {

			ip = InetAddress.getByName("127.0.0.1");

// Manejo de sockets para la comunicación en red
			ds = new DatagramSocket();    

			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 5555);  

			ds.send(dp);

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}



	}



}
