package Chat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler implements Runnable{

	private Socket client;
	
	public Handler(Socket client){
		this.client = client;
	}
	
	
	@Override
	public void run() { //läuft der ganze Thread ab
		try{
		//Streams
		
		//Daten, die an den Client geschickt werden; OutputStream wurde gespeichert
		OutputStream out = client.getOutputStream();
		PrintWriter writer = new PrintWriter(out);
		
		//Daten, die vom Clent gesendet werden -> damit man damit etwas anfangen kann (bearbeiten
		InputStream in = client.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		//-------------------------------
		
		
		//Sachen, die wir vom Client bekommen, die man ausgeben kann auf dem Server
		String s = null;
		
		while((s = reader.readLine()) != null){
			writer.write(s + "\n"); // \n wegen readLine. Damit es erkennt, dass es ein Zeilenbruch ist.
			writer.flush();	// das, was er vom client erhalten hat, gibt er gleich wieder zurück
			System.out.println("received by client: "+s);
			
		}
		writer.close();
		reader.close(); //Beide Streams wurden geschlossen.
		
		client.close();
		} catch(Exception e){
			
		}
		
	}

}
