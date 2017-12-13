package gui;						//window.setFullScreen(true) - vollbildschirm wie in youtube

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import Chat.ChatClient;
import Chat.ChatServer;
import gui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//model hier 
public class MainGameFX extends Application {

	public static AtomicInteger anzahlserver = new AtomicInteger(0);
	
	Stage AprimaryStage;
	public void start(Stage primaryStage) {
			try {
				URL fxmlUrl = getClass().getResource("Dominion.fxml");
				URL fxmlUrlBoard = getClass().getResource("MainBoard.fxml");
				FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
				fxmlLoader.setResources(ResourceBundle.getBundle("Lecture04_sampleSolution_de", new Locale("de", "de")));

				
				FXMLLoader fxmlLoaderBoard = new FXMLLoader(fxmlUrl);
				BoardModel m = new BoardModel();

				
				String[] abc = {};
				ChatClient.main(abc);
				fxmlLoader.setController(new Controller(m));
//				fxmlLoaderBoard.setController(new BoardControllerFXML());
				Parent root = fxmlLoader.load();
//				Parent rootBoard = fxmlLoaderBoard.load();
				Scene scene = new Scene(root,950,595);//Fullscreen 1280,800
//				Scene sceneBoard = new Scene(rootBoard);
//				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("DOMINION - THE GAME");
				root.requestFocus();
				primaryStage.setResizable(false);
//				primaryStage.setFullScreen(true);
				AprimaryStage=primaryStage;
				primaryStage.show();
				
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		
		public static void main(String[] args) {

				if(anzahlserver.get() < 1)
				{
					System.out.println(anzahlserver.get());
					new Thread(new Main()).start();
					new Thread(new ChatServer()).start();
					anzahlserver.incrementAndGet();
				}
			
			//			synchronized(anzahlserver)
//			{
//				if(anzahlserver < 1)
//				{
//					new Thread(new Main()).start();
//					anzahlserver++;
//				}
//			}
			launch(args);
		}
		public Stage getStage(){
			return AprimaryStage;
		}
	}