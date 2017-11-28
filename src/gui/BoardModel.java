package gui;

public class BoardModel {

	//Hand auffüllen mit Karten vom Server angegeben Methode hier.
	
	protected static int SliderInt;
	
	
	public BoardModel(){
	System.out.println("BoardModel in use");		
	}
	protected void PrintStuff(){
		System.out.println("Printing Random Stuff mate");
	}
	
	protected void PrintStuff2(){
		System.out.println("2Printing Random Stuff mate");
	}
	
	protected void SaveSlider(int x){
		SliderInt = x;
		System.out.println(SliderInt+"SaveSlider");
		
	}
	protected int getSlider(){
		System.out.println(SliderInt+"getSlider");
		return SliderInt;
		
	}
}
