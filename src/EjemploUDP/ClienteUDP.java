package EjemploUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClienteUDP {

	public static void main(String[] args) {
		DatagramSocket ds=null; 
	    String str = "Welcome java";  
	    InetAddress ip;
		try {
			ip = InetAddress.getByName("127.0.0.1");
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
