package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable{

	@FXML
	private Button Blogin, Banleitung, Beinstellungen, Bquit;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
	}
	
	
	@FXML
	private void quit(){
		Bquit.setOnAction(e-> Platform.exit());
	}
	
	
	@FXML
	private void Login(){
		
		
	}
	
	
	@FXML 
	private void Settings(){		//new class mache mit dem Fenster und klasse starte
		
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL); 		//Blocks User Interaction with other windows
		window.setTitle("Einstellungen");
		window.setMinWidth(400);
		window.setMinHeight(200);
		Label label = new Label();
		label.setText("   Sprache\n Rundenzahl:");
		
		
		Button Sok = new Button("OK");
		//SLIDER installieren
		//Toggle für Sprache
		Sok.setOnAction(e->{window.hide(); window.close();});
	
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, Sok);
		layout.setAlignment(Pos.CENTER);
		
		//Scene
		
		Scene sceneSettings = new Scene(layout);
		window.setScene(sceneSettings);
		window.showAndWait();
		
		
			
		//window.setScene();
		
		}

}
