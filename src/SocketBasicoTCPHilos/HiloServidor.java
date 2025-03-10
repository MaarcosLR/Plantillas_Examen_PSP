package SocketBasicoTCPHilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {
	
	
	Socket cliente;

	public HiloServidor(Socket cliente) {
		super();
		this.cliente = cliente;
	}
	
	public void run() {
		
		System.out.println("Conexión establecida");
		InputStream is;
		try {
			is = cliente.getInputStream();
		
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
