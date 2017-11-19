package gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler implements Runnable{

	private Socket client;
	
	public Handler(Socket client)
	{
		this.client = client;
	}
	
	@Override
	public void run() {
		try{
			
		  ObjectInputStream input = new  ObjectInputStream(client.getInputStream()); 
		  ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
		  PrintWriter writeTo = new PrintWriter(output);
		  BufferedReader readFrom = new BufferedReader(new InputStreamReader(input));

		  
			writeTo.close();
			readFrom.close();		
			client.close();
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public int scan(String text)
	{
		
		return ScannerInterface.scan(text);
	}
	
	public String scanString(String text)
	{
		
		return ScannerInterface.scanString(text);
	}

}
