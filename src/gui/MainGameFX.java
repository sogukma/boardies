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
				FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
				fxmlLoader.setController(new Controller());
				Parent root = fxmlLoader.load();
				Scene scene = new Scene(root,955,600);//Fullscreen 1280,800
//				scene.getStylesheets().add("Dominion.css");
				primaryStage.setScene(scene);
				primaryStage.setTitle("DOMINION - THE GAME");
				root.requestFocus();
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