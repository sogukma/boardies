package gui;						//window.setFullScreen(true) - vollbildschirm wie in youtube

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

//import Chat.ChatClient;
//import Chat.ChatServer;
import gui.Controller;
import javafx.application.Application;
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
//model hier 
public class MainGameFX extends Application {

	public static AtomicInteger anzahlserver = new AtomicInteger(0);
	private Stage stage;
	
	public void start(Stage primaryStage) {
			try {
				URL fxmlUrl = getClass().getResource("Dominion.fxml");
				URL fxmlUrlBoard = getClass().getResource("MainBoard.fxml");
				FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
				fxmlLoader.setResources(ResourceBundle.getBundle("Language_de", new Locale("de")));

				
				FXMLLoader fxmlLoaderBoard = new FXMLLoader(fxmlUrl);
				BoardModel m = new BoardModel();

				this.stage=primaryStage;
				
				fxmlLoader.setController(new Controller(m));
//				fxmlLoaderBoard.setController(new BoardControllerFXML());
				Parent root = fxmlLoader.load();
//				Parent rootBoard = fxmlLoaderBoard.load();
				Scene scene = new Scene(root,950,595);//Fullscreen 1280,800
//				Scene sceneBoard = new Scene(rootBoard);
//				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("DOMINION");
				root.requestFocus();
				primaryStage.setResizable(false);
//				primaryStage.setFullScreen(true);
				primaryStage.setOnCloseRequest(e-> endProgram());
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
		public void stop(){
			stage.hide();
		}
		public Stage getStage(){
			return stage;
		}
		public void endProgram(){
			Stage Exitwindow = new Stage();
			
			Exitwindow.initModality(Modality.APPLICATION_MODAL); 		
			Exitwindow.setTitle("");
			Exitwindow.setMinWidth(250);
			Exitwindow.setMinHeight(300);
			Label label = new Label("Spiel Beenden?");   //CLOSE GAME?
			Button yesButton = new Button("JA");		//YES
			Button noButton = new Button("NEIN");		//NO
			
			yesButton.setOnAction(e -> {
				Exitwindow.close();
				Platform.exit();
				Map<Thread, StackTraceElement[]> m = Thread.getAllStackTraces();
				try{
				for(Map.Entry<Thread, StackTraceElement[]> entry : m.entrySet()){
					
					entry.getKey().sleep(500);
					entry.getKey().interrupt();	
					
				}}catch(InterruptedException ee){
					Thread.currentThread().interrupt();
				}catch(Exception eee){}
			});
			
			noButton.setOnAction(e -> {
				Exitwindow.close();
			});
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label, yesButton, noButton);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			Exitwindow.setScene(scene);
			Exitwindow.showAndWait();  
		}
	}