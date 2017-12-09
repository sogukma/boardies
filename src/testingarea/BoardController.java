package testingarea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

public class BoardController implements EventHandler<ActionEvent> {

//	 private BoardModel model;
	 private BoardView view;
	
	protected BoardController(BoardView view){
//		this.model = model;
		this.view = view;
		
		//Clicks hier machen
//		for (ImageView i : view.iVorrat){
//			i.setOnMouseClicked(this);
//		}
		
//		view.ImgKupfer.setOnMouseClicked(this);
		
		
		view.TestButton.setOnAction(this);
	
		view.ImgKupfer.setOnMouseClicked(e -> System.out.println("hey"));
	}
	
	
	
	@Override
	public void handle(ActionEvent event){
		ImageView i=(ImageView) event.getSource();
	}
	
	
	
	
	
}
