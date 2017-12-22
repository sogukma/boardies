/**
* Der ChatServer ist das Server der Chat-Umgebung.
* Hier werden die ein- und ausgehenden Nachrichten vom Client abgearbeitet.
*
* @author  Van Necati
* @version 1.0
* @since   2017-12-22 
*/

package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Necati
 *
 */
public class ChatServer implements Runnable{
	
	ServerSocket serverSocket;
	ArrayList<PrintWriter> clientList;
	
	final int NOT = 1;
	final int OKAY = 0;
	
	@Override
	public void run(){
		
		if(serverStart()){
		lookingforClients();
		}
	}
	
	/**
	 * Server wird gestartet und läuft
	 * @param kann nun die PrintWriter der Clients in die ArrayListe speichern
	 */
	private boolean serverStart() {
		try{
			serverSocket = new ServerSocket(5555);
			String s1 = "Server has startet";
			consoleText(s1, OKAY);
			clientList = new ArrayList<PrintWriter>();
			
			return true;
		} catch (Exception e){
			String s0 = "Server failed to start";
			consoleText(s0, NOT);
			
			return false;
		}
	}
	
	private void consoleText(String text, int ok_not) {
		if(ok_not == OKAY){
			System.out.println(text + "\n");
		}else{
			System.err.println(text + "\n");
		}
	}
	
	/**
	 * Die beiden clients werden aufgenommen.
	 * @param clients client, der sich beim serverSocket meldet und von ihm akzeptiert wird, wird gespeichert.
	 * @param print Output vom Client wird empfangen.
	 * @param clientList print von vorhin wird die ArrayList gespeichert; alle PrintWriter der CLients.
	 * @param tClient für jeden aufgenommenen Client wird ein Thread gestartet.
	 */
	public void lookingforClients(){
		while(true){
			try{
				Socket clients = serverSocket.accept();
				PrintWriter print = new PrintWriter(clients.getOutputStream());
				clientList.add(print);
				
				Thread tCLient = new Thread(new Handler(clients));
				tCLient.start();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Für jeden Client, der aufgenommen wurde, wird ein Thread gestartet.
	 * @param clients global. Uebergebener client wird gespeichert
	 * @param bufferedReader der BufferedReader des clients.
	 */
	public class Handler implements Runnable{
		Socket clients;
		BufferedReader bufferedReader;

		public Handler(Socket clients) {
			try{
				this.clients = clients;
				bufferedReader = new BufferedReader(new InputStreamReader(clients.getInputStream()));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		/**
		 * Die Nachrichten der Clients werden in der Konsole angezeigt. 
		 */
		@Override
		public void run() {
			String message;
			String fromClient = "Client: " ;
			
			try{
				while((message = bufferedReader.readLine()) != null){
					consoleText(fromClient+"\n" + message, OKAY);
					toClients(message);
				}
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
	}
	
	/**
	 * Der Iterator geht durch die Arraylist durch. 
	 * @param note Die Nachrichten aus der Liste werden gespeichert. 
	 */
	public void toClients(String note){
		Iterator list = clientList.iterator();
		
		while(list.hasNext()){
			PrintWriter print = (PrintWriter) list.next();
			print.println(note);
			print.flush();
		}
	}

}