package gui;						//window.setFullScreen(true) - vollbildschirm wie in youtube

import java.net.URL;

import gui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGameFX extends Application {

	public void start(Stage primaryStage) {
			try {
				URL fxmlUrl = getClass().getResource("Dominion.fxml");
				URL fxmlUrlBoard = getClass().getResource("MainBoard.fxml");
				FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
//				FXMLLoader fxmlLoaderBoard = new FXMLLoader(fxmlUrl);
				fxmlLoader.setController(new Controller());
//				fxmlLoaderBoard.setController(new BoardControllerFXML());
				Parent root = fxmlLoader.load();
//				Parent rootBoard = fxmlLoaderBoard.load();
				Scene scene = new Scene(root,955,600);//Fullscreen 1280,800
//				Scene sceneBoard = new Scene(rootBoard);
//				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("DOMINION - THE GAME");
				root.requestFocus();
				primaryStage.setResizable(false);
//				primaryStage.setFullScreen(true);
				primaryStage.show();
				
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	
		
		public static void main(String[] args) {
			launch(args);
		}

	}