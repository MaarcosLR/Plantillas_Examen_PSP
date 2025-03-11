package chatbasico;



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

// Importación de librerías necesarias para el funcionamiento
import java.util.Scanner;



// Clase principal que define la funcionalidad del programa
public class ServidorUDP {



	

// Método principal que inicia la ejecución del programa
	public static void main(String[] args) {

// Manejo de sockets para la comunicación en red
		DatagramSocket ds=null;

// Manejo de sockets para la comunicación en red
		DatagramSocket ds2=null;

		Scanner sc= new Scanner(System.in);

		InetAddress ip;

		String str = "";

		String str2 = "";

// Bucle que se ejecuta mientras la condición sea verdadera
	while(!str.equals("adios")&&!str2.equals("adios")) {

		try {

// Manejo de sockets para la comunicación en red
			ds = new DatagramSocket(5555);

			byte[] buf = new byte[1024];  

		    DatagramPacket dp = new DatagramPacket(buf, 1024);  

		    ds.receive(dp);  

		     str = new String(dp.getData(), 0, dp.getLength());  

		    System.out.println(str);  

		     ds.close(); 

// Manejo de sockets para la comunicación en red
		} catch (SocketException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}  

		

		

		 System.out.println("Introduzca mensaje");

	     str = sc.next();    

		try {

			ip = InetAddress.getByName("127.0.0.1");

// Manejo de sockets para la comunicación en red
			ds2 = new DatagramSocket();    

			DatagramPacket dp2 = new DatagramPacket(str.getBytes(), str.length(), ip, 6666);  

			ds2.send(dp2);

			ds2.close();

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

//	ds.close();

//	ds2.close();

	}

	

}
