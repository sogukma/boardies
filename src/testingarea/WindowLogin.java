package testingarea;

import java.net.URL;


//import gui.BoardView;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowLogin {

	public static void loginDisplay(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL); 		//Blocks User Interaction with other windows
		window.setTitle("Network Login");
		window.setMinWidth(400);
		window.setMinHeight(200);
		Label label = new Label();
		label.setText("Host\nJoin:");
		
		Button ServerButton = new Button("Server starten!");
		Button Lok = new Button("OK");
		Button goB = new Button("Go Board");
		
		
		ServerButton.setOnAction(e -> ServerButton.setDisable(true));
		Lok.setOnAction(e->{window.hide(); window.close();});
		goB.setOnAction(e->{ 
			
			
			
			
			
			
			
			
			
//			  try {
//			        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Boardies/src/gui/MainBoard.fxml"));
//			                Parent root1 = (Parent) fxmlLoader.load();
//			                Stage stage = new Stage();
//			                stage.setScene(new Scene(root1));  
//			                stage.show();
//			        } catch(Exception ez) {
//			           ez.printStackTrace();
//			          }
			
			
			
			
			
//			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Boardies/src/gui/MainBoard.fxml"));
	
//		BoardGame bg= new BoardGame();
//		BoardView bw = new BoardView();
//		bg.start(bw.getWindow());
		
//			window.setScene());
//			window.getScene().setRoot(BoardGame.start());
//			BoardGame.start(); 
			
			window.hide();
			});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,ServerButton, Lok, goB);
		layout.setAlignment(Pos.CENTER);
		
		//Scene
		
		Scene sceneSettings = new Scene(layout);
		window.setScene(sceneSettings);
		window.showAndWait();
	}
	
}
