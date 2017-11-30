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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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
	
	private BoardModel BM;
	
	
	BoardControllerFXML(BoardModel bm)
	{
		this.BM = bm;
		
		
		  try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/MainBoard.fxml")); //"/Boardies/src/gui/MainBoard.fxml"
		             	//fxmlLoader setController noch machen
		        
		        		fxmlLoader.setController(this);
		        		Parent root1 = fxmlLoader.load();
		                Stage stage = new Stage();
		                Scene BoardScene = new Scene(root1);
		                stage.setScene(BoardScene);
		                stage.setTitle("DOMINION");
		                stage.setFullScreen(true);
		                BoardScene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		                stage.show();
		                
		            
		   
//		                BoardControllerFXML.setRundenZahl(RundenZahl);			//Geht nicht weil label static muss, DARF NICHT
		                
		               
		                
		        } catch(Exception e) {
		           e.printStackTrace();
		        }	
		  
			BM.newestMessage.addListener( (o, oldValue, newValue) -> this.FillHand(newValue));

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		MainPane.setId("MainPane");
		KImgAnwesen.setId("ImgAnwesen");
		SPane.setId("SPane");
		APane.setId("APane");
		HandBox.setId("HandBox");
		
		HandBox.setSpacing(5.0);
		
		BM.PrintStuff();
        
        
        RundenCounter.setText(BM.getSlider()+"");
        
        KImgAnwesen.setOnMouseEntered(e->{
        	KImgAnwesen.setFitHeight(180);
    		KImgAnwesen.setFitWidth(120);
        });

        KImgAnwesen.setOnMouseExited(e->{
        	KImgAnwesen.setFitHeight(140);
        	KImgAnwesen.setFitWidth(90);
        });
//    	BM.newestMessage.addListener( (o, oldValue, newValue) -> FillHand());

		
        
//		RundenCounter.setText(Controller.getRundenZahl()+"");
}
	
	@FXML
	private void AktionButton(){
		System.out.println("hoi");
	}
	
	@FXML
	private void KaufAnwesen(){
		System.out.println("Anwesen gekauft");
		
		FillHand("test");
		
	}
	
	@FXML 
	private void ZoomAnwesen(){			//Dies wahrsheinlich im CSS versuchen
//		ImgAnwesen.setFitHeight(180);
//		ImgAnwesen.setFitWidth(120);
	}
	
//	public static void setRundenZahl(Label l){		//geht so nicht
//		String x = l.getText();
//		RundenCounter.setText(x);
//	}
	
	//public methode für alli Bilder um in Hand zu bekoh, wenn möglich include setOnMouseClick
	
	private void fillGold()
	{
//		Platform.runLater(
//				()->{
					ImageView imgVGold = new ImageView(imgGold);  //BILD PATH RICHTIG MACHEN
					imgVGold.setId("image");
					imgVGold.setPickOnBounds(true);
			        imgVGold.setFitHeight(140);
			        imgVGold.setFitWidth(90);
			        imgVGold.setOnMouseClicked(e->{
			        imgVGold.setDisable(true);	
			        System.out.println("Gold");
			        });
			        
			        HandBox.getChildren().add(imgVGold);
						
//				});
	}
	
	
	private void fillKupfer(int id)
	{
//		Platform.runLater(
//				()->{
			        ImageView imgVKupfer = new ImageView(imgKupfer);  //BILD PATH RICHTIG MACHEN
					imgVKupfer.setId(id+"");
					imgVKupfer.setPickOnBounds(true);
			        imgVKupfer.setFitHeight(140);
			        imgVKupfer.setFitWidth(90);
			        imgVKupfer.setOnMouseClicked(e->BM.getMh().send(imgVKupfer.getId()));
			        
			        HandBox.getChildren().add(imgVKupfer);
						
//				});
	}
	
	private void fillAnwesen(int id)
	{
//		Platform.runLater(
//				()->{
					  ImageView imgVAnwesen = new ImageView(imgAnwesen);  //BILD PATH RICHTIG MACHEN
				        imgVAnwesen.setId(id+"");
				        imgVAnwesen.setPickOnBounds(true);
				        imgVAnwesen.setFitHeight(140);
				        imgVAnwesen.setFitWidth(90);
				        imgVAnwesen.setOnMouseClicked(e->BM.getMh().send(imgVAnwesen.getId()));
				        
				        HandBox.getChildren().add(imgVAnwesen);
//				});
	}
	
	private void fillDorf(int id)
	{
//		Platform.runLater(
//				()->{
			        ImageView imgVDorf = new ImageView(imgDorf);  //BILD PATH RICHTIG MACHEN
			        imgVDorf.setId(id+"");
			        imgVDorf.setPickOnBounds(true);
			        imgVDorf.setFitHeight(140);
			        imgVDorf.setFitWidth(90);
			        imgVDorf.setOnMouseClicked(e->BM.getMh().send(imgVDorf.getId()));
			        
			        HandBox.getChildren().add(imgVDorf);
//				});
	}
	
	private void fillHolz(int id)
	{
//		Platform.runLater(
//				()->{
			        ImageView imgVHolz = new ImageView(imgHolz);  //BILD PATH RICHTIG MACHEN
			        imgVHolz.setId(id+"");
			        imgVHolz.setPickOnBounds(true);
			        imgVHolz.setFitHeight(140);
			        imgVHolz.setFitWidth(90);
			        imgVHolz.setOnMouseClicked(e->BM.getMh().send(imgVHolz.getId()));
			        
			        HandBox.getChildren().add(imgVHolz);
//				});
	}
	
	private void fillLab(int id)
	{
//		Platform.runLater(
//				()->{
			        ImageView imgVLab = new ImageView(imgLab);  //BILD PATH RICHTIG MACHEN
					imgVLab.setId(id+"");
					imgVLab.setPickOnBounds(true);
			        imgVLab.setFitHeight(140);
			        imgVLab.setFitWidth(90);
			        imgVLab.setOnMouseClicked(e->BM.getMh().send(imgVLab.getId()));
			        
			        HandBox.getChildren().add(imgVLab);
//				});
	}
	
	private void fillMarkt(int id)
	{
//		Platform.runLater(
//				()->{
			           
			        ImageView imgVMarkt = new ImageView(imgMarkt);  //BILD PATH RICHTIG MACHEN
					imgVMarkt.setId(id+"");
					imgVMarkt.setPickOnBounds(true);
			        imgVMarkt.setFitHeight(140);
			        imgVMarkt.setFitWidth(90);
			        imgVMarkt.setOnMouseClicked(e->BM.getMh().send(imgVMarkt.getId()));

			        HandBox.getChildren().add(imgVMarkt);
			        
//				});
	}
	
	
	private void fillSchmied(int id)
	{
//		Platform.runLater(
//				()->{
				        ImageView imgVSchmied = new ImageView(imgSchmied);  //BILD PATH RICHTIG MACHEN
					imgVSchmied.setId(id+"");
					imgVSchmied.setPickOnBounds(true);
			        imgVSchmied.setFitHeight(140);
			        imgVSchmied.setFitWidth(90);
			        imgVSchmied.setOnMouseClicked(e->BM.getMh().send(imgVSchmied.getId()));
				        HandBox.getChildren().add(imgVSchmied);
			        
//				});
	}
	
	public void FillHand(String response){

		Platform.runLater(
				()->{
		int id = 0;
	ArrayList<String> splittedResponse = new ArrayList<String>();
		
		for (String iterable_element : response.split(",")) {
			splittedResponse.add(iterable_element);
					
			if(iterable_element.toLowerCase().contains("estate"))
				{
					fillAnwesen(id);
				}
			if(iterable_element.toLowerCase().contains("copper"))
				{
				fillKupfer(id);			
				}
			if(iterable_element.toLowerCase().contains("laboratory"))
			{
				fillLab(id);			
			}
			if(iterable_element.toLowerCase().contains("market"))
			{
				fillMarkt(id);			
			}
			if(iterable_element.toLowerCase().contains("valley"))
			{
				fillDorf(id);			
			}
			
			if(iterable_element.toLowerCase().contains("smith"))
			{
				fillSchmied(id);			
			}
			if(iterable_element.toLowerCase().contains("lumberjack"))
			{
				fillHolz(id);			
			}


			
			id++;
		}
				});
		
//		Platform.runLater(
//				()->{
//		System.out.println("ich bin hier");
//		
//		ArrayList<ImageView> ImagesHand = new ArrayList<ImageView>();
//		
//		
//
//        
//   
//		
//        
//        
//        
//        HandBox.getChildren().addAll(ImagesHand);
        
//		HandBox.getChildren().addAll(imgGold,imgKupfer,imgDorf,imgHolz,imgLab,imgMarkt,imgSchmied);
//				});	
		
	}
	
}