package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		
		try {
			
			Socket client = new Socket("localhost", 5555);
			System.out.println("client has started");
			
			//Streams
			
			//Daten, die an den Client geschickt werden; OutputStream wurde gespeichert
			OutputStream out = client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			
			//Daten, die vom Clent gesendet werden -> damit man damit etwas anfangen kann (bearbeiten
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			//-------------------------------
			
			writer.write("Hallo");
			writer.flush(); //damit es aktualisiert
			
			reader.close(); //damit es sendet
			writer.close();
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
