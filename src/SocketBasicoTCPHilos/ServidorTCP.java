package SocketBasicoTCPHilos;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

	public static void main(String[] args) {
		ServerSocket ss=null;
		try {
			System.out.println("Soy el servidor");
			 ss = new ServerSocket(22222);
			while (true) {
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
