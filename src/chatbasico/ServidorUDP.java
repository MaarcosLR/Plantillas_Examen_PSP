package chatbasico;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServidorUDP {

	
	public static void main(String[] args) {
		DatagramSocket ds=null;
		DatagramSocket ds2=null;
		Scanner sc= new Scanner(System.in);
		InetAddress ip;
		String str = "";
		String str2 = "";
	while(!str.equals("adios")&&!str2.equals("adios")) {
		try {
			ds = new DatagramSocket(5555);
			byte[] buf = new byte[1024];  
		    DatagramPacket dp = new DatagramPacket(buf, 1024);  
		    ds.receive(dp);  
		     str = new String(dp.getData(), 0, dp.getLength());  
		    System.out.println(str);  
		     ds.close(); 
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		
		 System.out.println("Introduzca mensaje");
	     str = sc.next();    
		try {
			ip = InetAddress.getByName("127.0.0.1");
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
