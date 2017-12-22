/**
 * MainGameFX ist die allererste Klasse die ausgeführt wird. Sie ist zuständig für das Einrichten des SpielServers, des ChatServers,
 * sowie dem GUI. 
 * 
 *  @author  Cenik Halil
 *	@version 1.0
 *	@since   2017-12-21 
 * 
 */

package gui;		

import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import backend.BoardModel;
import backend.Main;

import chat.ChatServer;
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

public class MainGameFX extends Application {

	public static AtomicInteger anzahlserver = new AtomicInteger(0);
	private Stage stage;
	
	/**
	 * Ladet die FXML-Datei des StartMenüs, 
	 * @param primaryStage Ist das StartMenü Stage
	 */
	public void start(Stage primaryStage) {
			try {
				URL fxmlUrl = getClass().getResource("Dominion.fxml");
				FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
				fxmlLoader.setResources(ResourceBundle.getBundle("Language_de", new Locale("de")));

				BoardModel m = new BoardModel();
				
				fxmlLoader.setController(new Controller(m));
				Parent root = fxmlLoader.load();
				Scene scene = new Scene(root,950,595);
				scene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("DOMINION - Start");
				root.requestFocus();
				primaryStage.setResizable(false);
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
			launch(args);
		}
		public void stop(){
			stage.hide();
		}
		public Stage getStage(){
			return stage;
		}
		
		/**
		 * Falls versucht wird ein Fenster zu schliessen, wird das CloseRequest abgefangen und durch diese Methode bearbeitet.
		 * Bei einem erfolgreichem Abschluss, werden alle Threads mitgeschlossen. 
		 * @author Halil Cenik 
		 */
		
		public void endProgram(){
			Stage Exitwindow = new Stage();
			
			Exitwindow.initModality(Modality.APPLICATION_MODAL); 		
			Exitwindow.setTitle("");
			Exitwindow.setMinWidth(250);
			Exitwindow.setMinHeight(300);
			Label label = new Label("Spiel Beenden?");   
			Button yesButton = new Button("JA");		
			Button noButton = new Button("NEIN");		
			
			yesButton.setOnAction(e -> {
				Exitwindow.close();
				Platform.exit();
				Map<Thread, StackTraceElement[]> m = Thread.getAllStackTraces();
				try{
				for(Map.Entry<Thread, StackTraceElement[]> entry : m.entrySet()){
					
					entry.getKey().sleep(50);
					entry.getKey().interrupt();	
					
				}}catch(Exception ee){}
				
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