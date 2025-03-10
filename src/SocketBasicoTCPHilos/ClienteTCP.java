package SocketBasicoTCPHilos;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteTCP {

	public static void main(String[] args) {
		
		
		try {
			System.out.println("Soy el cliente");
			Socket s = new Socket("127.0.0.1", 22222);
			OutputStream os =s.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw);
			pw.println("hola");	
			pw.flush();
			
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String linea=br.readLine();
			System.out.println(linea);
			br.close();
			is.close();
			pw.close();
			s.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
