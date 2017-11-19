package testingarea;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowSettings {

	public static void settingsDisplay(){
	
	Stage window = new Stage();
	window.initModality(Modality.APPLICATION_MODAL); 		//Blocks User Interaction with other windows
	window.setTitle("Einstellungen");
	window.setMinWidth(400);
	window.setMinHeight(200);
	Label label = new Label();
	label.setText("Sprache\nRundenzahl:");
	
	
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
	
	}
	
}
