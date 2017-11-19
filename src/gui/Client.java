package gui;

import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Runnable{

private MessageHandler mh;
	
	public Client()
	{
		this.mh = null;
	}
	
	
//	public static void main(String args[])
//	{
//		Client c = new Client();
//		new Thread(c).start();
//		c.machdas();
//	}
	
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

	public void sendName(String PlayerName){
		mh.send(PlayerName.toString());
	}
	
	@Override
	public void run() {
		mh = new MessageHandler("localhost", 8080);

		
		while(true)
		{
		String response = mh.receive();
		System.out.println(response);
		ArrayList<String> splittedResponse = new ArrayList<String>();
		
		for (String iterable_element : response.split(",")) {
			splittedResponse.add(iterable_element);
					
			if(iterable_element.toLowerCase().contains("action"))
				{
					//erstelle aktionskarte
				}
			
			}
		}
		
		//if chat -> anzeigen in textfeld ; if game -> game()
		}	
	
	
	
}
