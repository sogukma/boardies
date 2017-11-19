package gui;
									//FRAGEN OB MÖGLICH BUTTONS IN HBOX REINZUGEBEN MIT BUTTONCLICK IN SCENEBUILDER
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BoardControllerFXML implements Initializable{

	//Wir könnten für Deck Stack benützen, pop() den obersten, randomize Methode selber schreiben..
	
	@FXML
	private ImageView ImgAnwesen, ImgKupfer, ImgHolz, ImgDorf, ImgSchmied, ImgLabor, ImgMarkt;
	
	@FXML
	private Label LPointsP1, LPointsP2, RundenCounter, DeckZahl, LInfo;
	
	@FXML
	private TextArea ChatArea;
	
	@FXML
	private TextField ChatField;
	
	@FXML
	private Button BAktion, BSkip;
	
	@FXML
	private HBox HandBox;
	
	@FXML 
	private ScrollPane SPane;
	
	@FXML 
	private AnchorPane APane;

	ImageView img2 = new ImageView("file:///Users/halilcenik/git/boardies/src/gui/Gold_mini.jpg");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		ImgAnwesen.setId("ImgAnwesen");
		SPane.setId("SPane");
		APane.setId("APane");
		HandBox.setId("HandBox");
		
		HandBox.setSpacing(5.0);
        
        img2.setFitHeight(140);
        img2.setFitWidth(90);
		
        
//		RundenCounter.setText(Controller.getRundenZahl()+"");
}
	
	@FXML
	private void AktionButton(){
		System.out.println("hoi");
	}
	
	@FXML
	private void KaufAnwesen(){
		System.out.println("Anwesen gekauft");
		
		ImageView img = new ImageView("../src/gui/Pictures/Gold_mini.jpg");  //BILD PATH RICHTIG MACHEN
		img.setId("image");
		img.setPickOnBounds(true);
        img.setFitHeight(140);
        img.setFitWidth(90);
		
		HandBox.getChildren().addAll(img);
	}
	
	@FXML 
	private void ZoomAnwesen(){			//Dies wahrsheinlich im CSS versuchen
//		ImgAnwesen.setFitHeight(180);
//		ImgAnwesen.setFitWidth(120);
	}
	
}