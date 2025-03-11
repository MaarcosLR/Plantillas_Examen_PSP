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
import java.net.SocketException;

// Importación de librerías necesarias para el funcionamiento
import java.net.UnknownHostException;



// Clase principal que define la funcionalidad del programa
public class ServidorUDP {



	

// Método principal que inicia la ejecución del programa
	public static void main(String[] args) {

// Manejo de sockets para la comunicación en red
		DatagramSocket ds;

		try {

// Manejo de sockets para la comunicación en red
			ds = new DatagramSocket(5555);

			byte[] buf = new byte[1024];  

		    DatagramPacket dp = new DatagramPacket(buf, 1024);  

		    ds.receive(dp);  

		    String str = new String(dp.getData(), 0, dp.getLength());  

		    System.out.println(str);  

		    ds.close(); 

// Manejo de sockets para la comunicación en red
		} catch (SocketException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}  

	    

	}

	

}
