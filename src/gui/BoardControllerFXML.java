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
import javafx.geometry.Rectangle2D;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
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
	
	@FXML
	private GridPane GPane;

	Image imgGold = new Image("/Gold_mini.jpg");
	Image imgKupfer = new Image("/Kupfer.jpg");
	Image imgAnwesen = new Image("/Punkte01.jpg"); 
	Image imgDorf = new Image("/Dorf_new.jpg");
	Image imgHolz = new Image("/Holzfaeller_new2.jpg");
	Image imgLab = new Image("/Laboratorium_new.jpg");
	Image imgMarkt = new Image("/Markt_new.jpg");
	Image imgSchmied = new Image("/Schmiede_new.jpg");
	//HIGHLIGHTED
//	Image imgGoldHigh = new Image("/Gold_mini.jpg");
	Image imgKupferHigh = new Image("/Kupfer_high.jpg");
	Image imgAnwesenHigh = new Image("/Punkte01_high.jpg"); 
	Image imgDorfHigh = new Image("/Dorf_new_high.jpg");
	Image imgHolzHigh = new Image("/Holzfaeller_new2_high.jpg");
	Image imgLabHigh = new Image("/Laboratorium_new_high.jpg");
	Image imgMarktHigh = new Image("/Markt_new_high.jpg");
	Image imgSchmiedHigh = new Image("/Schmiede_new_high.jpg");
	//BLACK-WHITE
//	Image imgGoldSW = new Image("/Gold_mini.jpg");
	Image imgKupferSW = new Image("/Kupfer_sw.jpg");
	Image imgAnwesenSW = new Image("/Punkte01_sw.jpg"); 
	Image imgDorfSW = new Image("/Dorf_new_sw.jpg");
	Image imgHolzSW = new Image("/Holzfaeller_new2_sw.jpg");
	Image imgLabSW = new Image("/Laboratorium_new_sw.jpg");
	Image imgMarktSW = new Image("/Markt_new_sw.jpg");
	Image imgSchmiedSW = new Image("/Schmiede_new_sw.jpg");
	
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
		                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		                stage.setX(primaryScreenBounds.getMinX());
		                stage.setY(primaryScreenBounds.getMinY());
		                stage.setWidth(primaryScreenBounds.getWidth());
		                stage.setHeight(primaryScreenBounds.getHeight());
		                stage.setScene(BoardScene);
		                stage.setTitle("DOMINION");
		                stage.setFullScreen(false);
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
//		KImgAnwesen.setId("ImgAnwesen");
		
		SPane.setId("SPane");
		APane.setId("APane");
		HandBox.setId("HandBox");
		
		HandBox.setSpacing(5.0);
		
		BM.PrintStuff();
        
        
        RundenCounter.setText(BM.getSlider()+"");
        //HIGHLITINGS
        //Anwesen
        KImgAnwesen.setOnMouseEntered(e->{
        	KImgAnwesen.setFitHeight(190);
    		KImgAnwesen.setFitWidth(140);
    		KImgAnwesen.setImage(imgAnwesenHigh);
        });
        KImgAnwesen.setOnMouseExited(e->{
        	KImgAnwesen.setFitHeight(170);
        	KImgAnwesen.setFitWidth(120);
        	KImgAnwesen.setImage(imgAnwesen);
        });
        //Kupfer
        KImgKupfer.setOnMouseEntered(e->{
        	KImgKupfer.setFitHeight(190);
        	KImgKupfer.setFitWidth(140);
        	KImgKupfer.setImage(imgKupferHigh);
        });
        KImgKupfer.setOnMouseExited(e->{
        	KImgKupfer.setFitHeight(170);
        	KImgKupfer.setFitWidth(120);
        	KImgKupfer.setImage(imgKupfer);
        });
        //Holz
        KImgHolz.setOnMouseEntered(e->{
        	KImgHolz.setFitHeight(190);
        	KImgHolz.setFitWidth(140);
        	KImgHolz.setImage(imgHolzHigh);
        });
        KImgHolz.setOnMouseExited(e->{
        	KImgHolz.setFitHeight(170);
        	KImgHolz.setFitWidth(120);
        	KImgHolz.setImage(imgHolz);
        });
        //Dorf KImgDorf
        KImgDorf.setOnMouseEntered(e->{
        	KImgDorf.setFitHeight(190);
        	KImgDorf.setFitWidth(140);
        	KImgDorf.setImage(imgDorfHigh);
        });
        KImgDorf.setOnMouseExited(e->{
        	KImgDorf.setFitHeight(170);
        	KImgDorf.setFitWidth(120);
        	KImgDorf.setImage(imgDorf);
        });
        //Schmied
        KImgSchmied.setOnMouseEntered(e->{
        	KImgSchmied.setFitHeight(190);
        	KImgSchmied.setFitWidth(140);
        	KImgSchmied.setImage(imgSchmiedHigh);
        });
        KImgSchmied.setOnMouseExited(e->{
        	KImgSchmied.setFitHeight(170);
        	KImgSchmied.setFitWidth(120);
        	KImgSchmied.setImage(imgSchmied);
        });
        //Labor
        KImgLabor.setOnMouseEntered(e->{
        	KImgLabor.setFitHeight(190);
        	KImgLabor.setFitWidth(140);
        	KImgLabor.setImage(imgLabHigh);
        });
        KImgLabor.setOnMouseExited(e->{
        	KImgLabor.setFitHeight(170);
        	KImgLabor.setFitWidth(120);
        	KImgLabor.setImage(imgLab);
        });
        //Markt
        KImgMarkt.setOnMouseEntered(e->{
        	KImgMarkt.setFitHeight(190);
        	KImgMarkt.setFitWidth(140);
        	KImgMarkt.setImage(imgMarktHigh);
        });
        KImgMarkt.setOnMouseExited(e->{
        	KImgMarkt.setFitHeight(170);
        	KImgMarkt.setFitWidth(120);
        	KImgMarkt.setImage(imgMarkt);
        });
        
        
        KImgKupfer.setOnMouseClicked(
        	e->{BM.getMh().send(0+"");
        });
        
        KImgAnwesen.setOnMouseClicked(
            	e->{BM.getMh().send(1+"");
            });
        
        KImgLabor.setOnMouseClicked(
            	e->{BM.getMh().send(2+"");
            });
        
        KImgMarkt.setOnMouseClicked(
            	e->{BM.getMh().send(3+"");
            });
        
        KImgDorf.setOnMouseClicked(
            	e->{BM.getMh().send(4+"");
            });

        KImgSchmied.setOnMouseClicked(
            	e->{BM.getMh().send(5+"");
            });
        
        KImgHolz.setOnMouseClicked(
            	e->{BM.getMh().send(6+"");
            });

        

           	
//    	BM.newestMessage.addListener( (o, oldValue, newValue) -> FillHand());

		
        
//		RundenCounter.setText(Controller.getRundenZahl()+"");
}
	
	@FXML
	private void AktionButton(){
		System.out.println("hoi");
		DisableGrid();
	}
	
	@FXML
	private void KaufAnwesen(){
		System.out.println("Anwesen gekauft");
		
		FillHand("test");
		
	}
	
	@FXML 
	private void SkipButton(){			//Dies wahrsheinlich im CSS versuchen
		EnableGrid();
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
			        imgVKupfer.setOnMouseClicked(e->{	
			        BM.getMh().send(imgVKupfer.getId());
			        imgVKupfer.setDisable(true);
			        imgVKupfer.setImage(imgKupferSW);
			        });
			        
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
				        imgVAnwesen.setOnMouseClicked(e->{	
				        BM.getMh().send(imgVAnwesen.getId());
				        imgVAnwesen.setDisable(true);
				        imgVAnwesen.setImage(imgAnwesenSW);
				        });
				        
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
			        imgVDorf.setOnMouseClicked(e->{	
			        BM.getMh().send(imgVDorf.getId());
			        imgVDorf.setDisable(true);
			        imgVDorf.setImage(imgDorfSW);
			        });
			        
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
			        imgVHolz.setOnMouseClicked(e->{	
			        BM.getMh().send(imgVHolz.getId());
			        imgVHolz.setDisable(true);
			        imgVHolz.setImage(imgHolzSW);
			        });
			        
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
			        imgVLab.setOnMouseClicked(e->{	
			        BM.getMh().send(imgVLab.getId());
			        imgVLab.setDisable(true);
			        imgVLab.setImage(imgLabSW);
			        });
			        
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
			        imgVMarkt.setOnMouseClicked(e->{	
			        BM.getMh().send(imgVMarkt.getId());
			        imgVMarkt.setDisable(true);
			        imgVMarkt.setImage(imgMarktSW);
			        });

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
			        imgVSchmied.setOnMouseClicked(e->{	
			        BM.getMh().send(imgVSchmied.getId());
			        imgVSchmied.setDisable(true);
			        imgVSchmied.setImage(imgSchmiedSW);
			        });
				    
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

			
			
			if(iterable_element.toLowerCase().contains("info"))
			{
				
				LInfo.setText(iterable_element);
			}
			
			
			if(iterable_element.toLowerCase().contains("budget"))
			{
				
				//setze total budget in feld ein
			}
			
			
			if(iterable_element.toLowerCase().contains("yourpoints"))
			{
				
				LPointsP1.setText(iterable_element);
			}
			
			if(iterable_element.toLowerCase().contains("opponentpoints"))
			{
				LPointsP2.setText(iterable_element);
			}
			
			if(iterable_element.toLowerCase().contains("handextended"))
			{
				Hclear();
				
			}
			
			if(iterable_element.toLowerCase().contains("round"))
			{
				RundenCounter.setText(iterable_element+"/20");
				//enable aktionskarten
			}
			if(iterable_element.toLowerCase().contains("action"))
			{
				
				HandBox.setDisable(false);
				DisableGrid();
				//FOR SCHLEIFE MACHEN UM
			}
			
			if(iterable_element.toLowerCase().contains("purchasehand"))
			{
				//infobox melden
				
				for (Node child : HandBox.getChildren()) {
				    ImageView imgViewTest = (ImageView) child;
//				    if (){
//				    	imgViewTest.setDisable(true);
//				    }
				 
				}
				
				
				//disable aktionskarten
				//enable kaufkarten
			}
			
			if(iterable_element.toLowerCase().contains("purchasestock"))
			{
				//infobox melden
				
				//disable hand
				//enable vorrat
			}
			
			if(iterable_element.toLowerCase().contains("end"))
			{
				LInfo.setText(iterable_element);
				Hclear();
				HandBox.setDisable(true);
				//disable hand
				//disable vorrat
			}
			
			if(iterable_element.toLowerCase().contains("roundend"))
			{
				//infobox melden
				
				Hclear();
				
				
				//disable hand
				//disable vorrat
			}
			
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
	
	public void DisableGrid(){
		GPane.setDisable(true);
		KImgAnwesen.setImage(imgAnwesenSW);
		KImgKupfer.setImage(imgKupferSW);
		KImgHolz.setImage(imgHolzSW);
		KImgDorf.setImage(imgDorfSW);
		KImgSchmied.setImage(imgSchmiedSW);
		KImgLabor.setImage(imgLabSW);
		KImgMarkt.setImage(imgMarktSW);
	}
	public void EnableGrid(){
		GPane.setDisable(false);
		KImgAnwesen.setImage(imgAnwesen);
		KImgKupfer.setImage(imgKupfer);
		KImgHolz.setImage(imgHolz);
		KImgDorf.setImage(imgDorf);
		KImgSchmied.setImage(imgSchmied);
		KImgLabor.setImage(imgLab);
		KImgMarkt.setImage(imgMarkt);
	}
	public void Hclear(){
		HandBox.getChildren().clear();
	}
	
}