package gui;
									//FRAGEN OB MÖGLICH BUTTONS IN HBOX REINZUGEBEN MIT BUTTONCLICK IN SCENEBUILDER
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.image.Image;
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
	private ImageView KImgAnwesen, KImgKupfer, KImgHolz, KImgDorf, KImgSchmied, KImgLabor, KImgMarkt;
	
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
	private AnchorPane APane,MainPane;

	Image imgGold = new Image("/Gold_mini.jpg");
	Image imgKupfer = new Image("/Kupfer.jpg");
	Image imgAnwesen = new Image("/Punkte01.jpg"); 
	Image imgDorf = new Image("/Dorf_new.jpg");
	Image imgHolz = new Image("/Holzfaeller_new2.jpg");
	Image imgLab = new Image("/Laboratorium_new.jpg");
	Image imgMarkt = new Image("/Markt_new.jpg");
	Image imgSchmied = new Image("/Schmiede_new.jpg");
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		MainPane.setId("MainPane");
		KImgAnwesen.setId("ImgAnwesen");
		SPane.setId("SPane");
		APane.setId("APane");
		HandBox.setId("HandBox");
		
		HandBox.setSpacing(5.0);
		
		
        
        
        

		
        
//		RundenCounter.setText(Controller.getRundenZahl()+"");
}
	
	@FXML
	private void AktionButton(){
		System.out.println("hoi");
	}
	
	@FXML
	private void KaufAnwesen(){
		System.out.println("Anwesen gekauft");
		
		FillHand();
		
	}
	
	@FXML 
	private void ZoomAnwesen(){			//Dies wahrsheinlich im CSS versuchen
//		ImgAnwesen.setFitHeight(180);
//		ImgAnwesen.setFitWidth(120);
	}
	
	//public methode für alli Bilder um in Hand zu bekoh, wenn möglich include setOnMouseClick
	private void FillHand(){
	
		ArrayList<ImageView> ImagesHand = new ArrayList<ImageView>();
		
		ImageView imgVGold = new ImageView(imgGold);  //BILD PATH RICHTIG MACHEN
		imgVGold.setId("image");
		imgVGold.setPickOnBounds(true);
        imgVGold.setFitHeight(140);
        imgVGold.setFitWidth(90);
        ImagesHand.add(imgVGold);
        imgVGold.setOnMouseClicked(e->{
        imgVGold.setDisable(true);	
        System.out.println("Gold");
        });
        
        ImageView imgVKupfer = new ImageView(imgKupfer);  //BILD PATH RICHTIG MACHEN
		imgVKupfer.setId("image");
		imgVKupfer.setPickOnBounds(true);
        imgVKupfer.setFitHeight(140);
        imgVKupfer.setFitWidth(90);
        ImagesHand.add(imgVKupfer);
        imgVKupfer.setOnMouseClicked(e->System.out.println("Kupfer"));
        
        ImageView imgVAnwesen = new ImageView(imgAnwesen);  //BILD PATH RICHTIG MACHEN
        imgVAnwesen.setId("image");
        imgVAnwesen.setPickOnBounds(true);
        imgVAnwesen.setFitHeight(140);
        imgVAnwesen.setFitWidth(90);
        ImagesHand.add(imgVAnwesen);
        imgVAnwesen.setOnMouseClicked(e->System.out.println("Anwesen"));
        
        ImageView imgVDorf = new ImageView(imgDorf);  //BILD PATH RICHTIG MACHEN
        imgVDorf.setId("image");
        imgVDorf.setPickOnBounds(true);
        imgVDorf.setFitHeight(140);
        imgVDorf.setFitWidth(90);
        ImagesHand.add(imgVDorf);
        imgVDorf.setOnMouseClicked(e->System.out.println("Dorf"));
        
        ImageView imgVHolz = new ImageView(imgHolz);  //BILD PATH RICHTIG MACHEN
        imgVHolz.setId("image");
        imgVHolz.setPickOnBounds(true);
        imgVHolz.setFitHeight(140);
        imgVHolz.setFitWidth(90);
        ImagesHand.add(imgVHolz);
        imgVHolz.setOnMouseClicked(e->System.out.println("Holzfäller"));
        
        ImageView imgVLab = new ImageView(imgLab);  //BILD PATH RICHTIG MACHEN
		imgVLab.setId("image");
		imgVLab.setPickOnBounds(true);
        imgVLab.setFitHeight(140);
        imgVLab.setFitWidth(90);
        ImagesHand.add(imgVLab);
        imgVLab.setOnMouseClicked(e->System.out.println("Labor"));
        
        ImageView imgVMarkt = new ImageView(imgMarkt);  //BILD PATH RICHTIG MACHEN
		imgVMarkt.setId("image");
		imgVMarkt.setPickOnBounds(true);
        imgVMarkt.setFitHeight(140);
        imgVMarkt.setFitWidth(90);
        ImagesHand.add(imgVMarkt);
        imgVMarkt.setOnMouseClicked(e->System.out.println("Markt"));
        
        ImageView imgVSchmied = new ImageView(imgSchmied);  //BILD PATH RICHTIG MACHEN
		imgVSchmied.setId("image");
		imgVSchmied.setPickOnBounds(true);
        imgVSchmied.setFitHeight(140);
        imgVSchmied.setFitWidth(90);
        ImagesHand.add(imgVSchmied);
        imgVSchmied.setOnMouseClicked(e->System.out.println("Schmiede"));
      
        
   
		
        
        
        
        HandBox.getChildren().addAll(ImagesHand);
        
//		HandBox.getChildren().addAll(imgGold,imgKupfer,imgDorf,imgHolz,imgLab,imgMarkt,imgSchmied);
		
	}
	
}