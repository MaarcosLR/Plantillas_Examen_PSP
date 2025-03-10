package EjemploUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServidorUDP {

	
	public static void main(String[] args) {
		DatagramSocket ds;
		try {
			ds = new DatagramSocket(5555);
			byte[] buf = new byte[1024];  
		    DatagramPacket dp = new DatagramPacket(buf, 1024);  
		    ds.receive(dp);  
		    String str = new String(dp.getData(), 0, dp.getLength());  
		    System.out.println(str);  
		    ds.close(); 
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    
	}
	
}
