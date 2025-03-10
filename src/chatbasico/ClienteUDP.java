package chatbasico;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteUDP {

	public static void main(String[] args) {
		DatagramSocket ds=null;
		DatagramSocket ds2=null;
		Scanner sc= new Scanner(System.in);
		InetAddress ip;
		String str = "";
		String str2 = "";
	     
	    
	    while(!str.equals("adios")&&!str2.equals("adios")) {
	    	System.out.println("introduzca mensaje");
	    	 str= sc.next(); 
	    
		try {
			ip = InetAddress.getByName("127.0.0.1");
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
			ds2 = new DatagramSocket(6666);
			byte[] buf = new byte[1024];  
		    DatagramPacket dp2 = new DatagramPacket(buf, 1024);  
		    ds2.receive(dp2);  
		    str2 = new String(dp2.getData(), 0, dp2.getLength());  
		    System.out.println(str2);  
		    ds2.close();
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
