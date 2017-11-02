package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(5555);
			System.out.println("Server starts"); //Nach dem Serverobjekt angelegt wurde, wurde der Server gestartet
			
			//eingehende Verbindung
			Socket client = server.accept();
			
			//Streams
			
			//Daten, die an den Client geschickt werden; OutputStream wurde gespeichert
			OutputStream out = client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			
			//Daten, die vom Clent gesendet werden -> damit man damit etwas anfangen kann (bearbeiten
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			//Sachen, die wir vom Client bekommen, die man ausgeben kann auf dem Server
			String s = null;
			
			while((s = reader.readLine()) != null){
				System.out.println("received by client: "+s);
				
			}
			writer.close();
			reader.close(); //Beide Streams wurden geschlossen.
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
