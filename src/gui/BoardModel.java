package gui;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

public class BoardModel implements Runnable {

	//Hand auffüllen mit Karten vom Server angegeben Methode hier.
	
	private MessageHandler mh;
//	protected static int SliderInt;
//	protected Stage LoginStage;
	protected static String PName;
	protected SimpleStringProperty newestMessage = new SimpleStringProperty();
	protected SimpleStringProperty newestCards = new SimpleStringProperty();
	
	public BoardModel(){
		this.mh = null;
//		System.out.println("BoardModel in use");		
	}
	
	
	
	public void machdas() {
		while(true)
		{
			scan();
		}

	}
	
	public String scan()
	{
		Scanner scan = new Scanner(System.in);
		String answer = scan.nextLine();
		mh.send(answer+"\n");
 		return answer;
	}

	public MessageHandler getMh() {
		return mh;
	}



	public void setMh(MessageHandler mh) {
		this.mh = mh;
	}



	public void sendName(String PlayerName){
		mh.send(PlayerName.toString());
	}
	
	public void SaveName(String PlayerName){
		PName =PlayerName;
	}
	public String getName(){
		return PName;
	}

//	protected void PrintStuff(){
//		System.out.println("Printing Random Stuff mate");
//	}
//	
//	protected void PrintStuff2(){
//		System.out.println("2Printing Random Stuff mate");
//	}
	
//	protected void SaveSlider(int x){
//		SliderInt = x;
//		System.out.println(SliderInt+"SaveSlider");
//		
//	}
//	protected int getSlider(){
//		System.out.println(SliderInt+"getSlider");
//		return SliderInt;
//		
//	}
	
	@Override
	public void run() {
		mh = new MessageHandler("localhost", 8080);


		while(true)
		{		
		String response = mh.receive();
		System.out.println(response);
		/*
		ArrayList<String> splittedResponse = new ArrayList<String>();
		
		for (String iterable_element : response.split(",")) {
			splittedResponse.add(iterable_element);
					
			if(iterable_element.toLowerCase().contains("action"))
				{
				//TODO	bcf.FillHand();
				}
			
			}
			*/

		String responseLowerCase = response.toLowerCase();
//		if(responseLowerCase.contains("worth") && (responseLowerCase.contains("copper|estate|laboratory|market|valley|smith|lumberjack")))
		
		if(responseLowerCase.contains("worth"))
		{
			newestCards.set("");
			newestCards.set(response);
//			newestCards.set("");
		}
		else
		{
			newestMessage.set(response);
			newestMessage.set("");
		}
			
		}
	
	}
}
