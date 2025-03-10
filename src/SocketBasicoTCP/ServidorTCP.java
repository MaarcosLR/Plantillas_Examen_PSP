package SocketBasicoTCP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
				System.out.println("Conexión establecida");
				InputStream is= cliente.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String linea=br.readLine();
				System.out.println(linea);
				
				OutputStream os =cliente.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				PrintWriter pw = new PrintWriter(osw);
				pw.println("mundo!");
				pw.flush();
				pw.close();
				os.close();
				is.close();
				
			
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
