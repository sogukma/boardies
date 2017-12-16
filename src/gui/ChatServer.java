package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer implements Runnable{
	
	private BoardModel Bm;
	ServerSocket server;
	ArrayList<PrintWriter> list_clientWriter;
	
	final int LEVEL_ERROR = 1;
	final int LEVEL_NORMAL = 0;

	@Override
	public void run() {
//		ChatServer s = new ChatServer();
		if(runServer()){
			listenToClients();
		}else{
			//do nothing
		}
	}
	
	public class ClientHandler implements Runnable{
		
		Socket client;
		BufferedReader reader;
		
		public ClientHandler(Socket client){
			try{
				this.client = client;
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			} catch (IOException e){
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			String message;
			
			try{
				while((message = reader.readLine()) != null){
					appendTextToConsole("From client: \n" + message, LEVEL_NORMAL);
					sendToAllClients(message);
				}
			} catch (IOException e){
				e.printStackTrace();
			}
			
		}
	}

	private void listenToClients() {
		while(true){ //l�uft die ganze Zeit
			try{
				Socket client = server.accept();
				
				PrintWriter writer = new PrintWriter(client.getOutputStream());
				list_clientWriter.add(writer);
				
				Thread clientThread = new Thread(new ClientHandler(client));
				clientThread.start();	
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		
	}

	private boolean runServer() {
		try{
			server = new ServerSocket(5555);
			appendTextToConsole("sever has started", LEVEL_ERROR);
			list_clientWriter = new ArrayList<PrintWriter>();
		return true;
	}	catch (IOException e){
		appendTextToConsole("server couldn't start", LEVEL_ERROR);
		e.printStackTrace();
		return false;
	}
		
  }

	private void appendTextToConsole(String message, int level) {
		if(level == LEVEL_ERROR){
			System.err.println(message+"\n");
		}else{
			System.out.println(message+"\n");
		}
	}
	
	public void sendToAllClients(String mesage){
		Iterator it = list_clientWriter.iterator();
		
		while(it.hasNext()){
			PrintWriter writer = (PrintWriter) it.next();
			writer.println(mesage);
			writer.flush();
		}
	}

}
