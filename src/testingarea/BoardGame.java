package testingarea;

import testingarea.BoardController;
import testingarea.BoardModel;
import testingarea.BoardView;
import javafx.application.Application;
import javafx.stage.Stage;

public class BoardGame extends Application {

	private BoardModel model;
	private BoardView view;
	private BoardController controller;

//	public static void main(String [] args){
//		launch();
//	}
	
	
	
	@Override
	public void start(Stage window) {
		model = new BoardModel();
		view = new BoardView(window, model);
		controller = new BoardController(model, view);
		
		// Display the GUI after all initialization is complete
		view.start();
	}
	
	@Override
	public void stop() {
		if (view != null) view.stop();
	}



	
	
	
}
