package backend;

import java.util.ArrayList;
import java.util.Scanner;

import gui.MessageHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

public class BoardModel implements Runnable {

	//Hand auffüllen mit Karten vom Server angegeben Methode hier.
	
	private MessageHandler mh;
//	protected static int SliderInt;
//	protected Stage LoginStage;
	protected static String PName;
	public SimpleStringProperty newMessage = new SimpleStringProperty();
	public SimpleStringProperty newCards = new SimpleStringProperty();
	
	public BoardModel(){
		this.mh = null;
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
		
		//mit worth wird Handkarten-String identifiziert
		if(responseLowerCase.contains("worth"))
		{
			newCards.set("");
			newCards.set(response);
//			newestCards.set("");
		}
		else
		{
			newMessage.set(response);
			newMessage.set("");
		}
			
		}
	
	}
}
