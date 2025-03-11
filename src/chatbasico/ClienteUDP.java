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
public class ClienteUDP {



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

	    	System.out.println("introduzca mensaje");

	    	 str= sc.next(); 

	    

		try {

			ip = InetAddress.getByName("127.0.0.1");

// Manejo de sockets para la comunicación en red
			ds = new DatagramSocket();    

			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 5555);  

			ds.send(dp);

			ds.close();

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}



		

		try {

// Manejo de sockets para la comunicación en red
			ds2 = new DatagramSocket(6666);

			byte[] buf = new byte[1024];  

		    DatagramPacket dp2 = new DatagramPacket(buf, 1024);  

		    ds2.receive(dp2);  

		    str2 = new String(dp2.getData(), 0, dp2.getLength());  

		    System.out.println(str2);  

		    ds2.close();

// Manejo de sockets para la comunicación en red
		} catch (SocketException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}  

	}

//	    ds.close();

//	    ds2.close();

	}



}
