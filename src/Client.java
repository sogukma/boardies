import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

private MessageHandler mh;
	
	public Client()
	{
		this.mh = null;
	}
	
	
	public static void main(String args[])
	{
		Client c = new Client();
		new Thread(c).start();
		c.machdas();
	}
	
	public void machdas() {
		while(true)
		{
			scan();
		}

	}
	
	//TODO hier soll die Scanner funktion des Clients aufgerufen werden, um Benutzereingaben an den Server zu schicken
	// und Server-Nachrichten dem jeweiligen Benutzer anzuzeigen. 
	
	//button clicked
	public String scan()
	{
		Scanner scan = new Scanner(System.in);
		String answer = scan.nextLine();
		mh.send(answer+"\n");
 		return answer;
	}

	@Override
	public void run() {
		mh = new MessageHandler("localhost", 8080);

		
		while(true)
		{
		String response = mh.receive();
		System.out.println(response);	
		//if chat -> anzeigen in textfeld ; if game -> game()
		}	
	}
	
	
}
