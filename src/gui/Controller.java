package gui;
									//FRAGEN OB MÖGLICH BUTTONS IN HBOX REINZUGEBEN MIT BUTTONCLICK IN SCENEBUILDER
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
									//FÜR CSS, IN SCENEBUILDER STYLESHEETS: DOMINION.CSS EINFÜGEN
public class Controller implements Initializable{

	@FXML
	private Button Blogin, Banleitung, Beinstellungen, Bquit, Bback1,Bback2,Bback3, Bjoin, Bserver, BreadmeD, BreadmeE ;
	
	@FXML
	private ToggleButton ToggleD, ToggleE;
	
	@FXML
	private HBox StartBox, SettingsBox, AnleitungBox, LoginBox;
	
	@FXML 
	private TextField TName;
	
	@FXML 
	private Label RundenText,SpracheText;
	
	@FXML
	private Label RundenZahl; //muss static machen glaub
	
	@FXML
	private Slider RundenSlider;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
//		ImageView img = new ImageView("file:///Users/halilcenik/git/boardies/src/gui/Gold_mini.jpg");
//		img.setId("image");
//		img.setPickOnBounds(true);
//        img.setFitHeight(140);
//        img.setFitWidth(90);
        
        
//        
//        StartBox.getChildren().add(img);
////        Bquit.setGraphic(img);
////        Bquit.setText("");
//        img.setOnMouseClicked(e -> System.out.println("hoi"));
		
		 ToggleGroup group = new ToggleGroup();
		 ToggleD.setToggleGroup(group);
		 ToggleE.setToggleGroup(group);
		 ToggleD.setSelected(true);
		 
		
		
		Blogin.setId("button");
		Beinstellungen.setId("button");
		Bquit.setId("button");
		
		RundenZahl.setText(RundenSlider.getValue()+"");
		
		RundenSlider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {

		        RundenZahl.setText(newValue.intValue()+"");
		    }
		});
		

		
	}
	
//	ImageView img = new ImageView("file:///Users/halilcenik/git/boardies/src/gui/Gold_mini.jpg");
//    img.setPickOnBounds(true);
	
	
	
	
	@FXML
	private void quit(){
		Platform.exit();
	}
	
	
	@FXML
	private void Login(){
//		WindowLogin.loginDisplay();
//		Button BTest = new Button();
//		BTest.setText("Digga");
//		LoginBox.getChildren().add(BTest);
		StartBox.setVisible(false);
		LoginBox.setVisible(true);
		
	}
	
	@FXML
	private void Anleitung(){
		StartBox.setVisible(false);
		AnleitungBox.setVisible(true);
	}
	
	
	
	@FXML 
	private void Settings(){		//new class mache mit dem Fenster und klasse starte
//		WindowSettings.settingsDisplay();
		StartBox.setVisible(false);
		SettingsBox.setVisible(true);
		}

	
	@FXML
	private void Back(){
		LoginBox.setVisible(false);
		SettingsBox.setVisible(false);
		AnleitungBox.setVisible(false);
		StartBox.setVisible(true);
		
		//Quasi SAVED kann man machen so für Toggle
//		 if(ToggleD.isSelected())
//		 {System.out.println("Really Chosen");}
	}
	
	@FXML 
	private void Join(){
		String PlayerName = TName.getText();
		System.out.println(PlayerName);
		//Open Board
		  try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/MainBoard.fxml")); //"/Boardies/src/gui/MainBoard.fxml"
		             	//fxmlLoader setController noch machen
		        		fxmlLoader.setController(new BoardControllerFXML());
		        		Parent root1 = fxmlLoader.load();
		                Stage stage = new Stage();
		                Scene BoardScene = new Scene(root1);
		                stage.setScene(BoardScene);
		                stage.setTitle("DOMINION");
		                stage.setFullScreen(true);
		                BoardScene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		                stage.show();
		               
		                
		        } catch(Exception e) {
		           e.printStackTrace();
		        }	
	}
	
	@FXML
	private void ServerStart(){
		//Überprüfen ob Server schon läuft, sonst starten
	}
	
	@FXML
	private void AnleitungD(){
		//open Deutsche Anleitung
	}
	
	@FXML 
	private void AnleitungE(){
		//open English Anleitung
	}
	
	@FXML
	private void ChangeDeutsch(){
		//Alle SprachEinstellungen Deutsch machen
		System.out.println("Deutsch gewählt");
	}
	
	@FXML 
	private void ChangeEnglish(){
		//Alle SprachEinstellungen Englisch machen
		System.out.println("English chosen");
	}

//	public static Label getRundenZahl() {
//		return RundenZahl;
//	}

//	public static void setRundenZahl(Label rundenZahl) {
//		RundenZahl = rundenZahl;
//	}
	
	
}
