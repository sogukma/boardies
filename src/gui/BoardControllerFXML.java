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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BoardControllerFXML implements Initializable{

	//Wir könnten für Deck Stack benützen, pop() den obersten, randomize Methode selber schreiben..
	
	@FXML
	private ImageView KImgAnwesen, KImgKupfer, KImgHolz, KImgDorf, KImgSchmied, KImgLabor, KImgMarkt;
	
	@FXML
	private Label LPointsP1, LPointsP2, RundenCounter, DeckZahl, LInfo, LKonto;
	
	@FXML
	private TextArea ChatArea;
	
	@FXML
	private TextField ChatField;
	
	@FXML
	private HBox HandBox;
	
	@FXML 
	private ScrollPane SPane;
	
	@FXML 
	private AnchorPane APane,MainPane;
	
	@FXML
	private GridPane GPane;
	
	@FXML
	private ResourceBundle resources;
	
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
	
	//VorratBilder
	Image imgKupferVV = new Image("/KupferVV.jpg");
	Image imgAnwesenVV = new Image("/Punkte01VV.jpg"); 
	Image imgDorfVV = new Image("/Dorf_newVV.jpg");
	Image imgHolzVV = new Image("/Holzfaeller_newVV.jpg");
	Image imgLabVV = new Image("/Laboratorium_newVV.jpg");
	Image imgMarktVV = new Image("/Markt_newVV.jpg");
	Image imgSchmiedVV = new Image("/Schmiede_newVV.jpg");
	//HIGHLIGHTED
	Image imgKupferHighVV = new Image("/Kupfer_highVV.jpg");
	Image imgAnwesenHighVV = new Image("/Punkte01_highVV.jpg"); 
	Image imgDorfHighVV = new Image("/Dorf_new_highVV.jpg");
	Image imgHolzHighVV = new Image("/Holzfaeller_new2_highVV.jpg");
	Image imgLabHighVV = new Image("/Laboratorium_new_highVV.jpg");
	Image imgMarktHighVV = new Image("/Markt_new_highVV.jpg");
	Image imgSchmiedHighVV = new Image("/Schmiede_new_highVV.jpg");
	//BLACK-WHITE
	Image imgKupferSWVV = new Image("/Kupfer_swVV.jpg");
	Image imgAnwesenSWVV = new Image("/Punkte01_swVV.jpg"); 
	Image imgDorfSWVV = new Image("/Dorf_new_swVV.jpg");
	Image imgHolzSWVV = new Image("/Holzfaeller_new2_swVV.jpg");
	Image imgLabSWVV = new Image("/Laboratorium_new_swVV.jpg");
	Image imgMarktSWVV = new Image("/Markt_new_swVV.jpg");
	Image imgSchmiedSWVV = new Image("/Schmiede_new_swVV.jpg");
	
	private BoardModel BM;
	Stage stage = new Stage();
	boolean answer;
	
	ImageView imgVAnwesen = new ImageView(imgAnwesen); 
	ImageView imgVSchmied = new ImageView(imgSchmied); //testing stuff still here
	
	BoardControllerFXML(BoardModel bm, ResourceBundle resourceBundle)
	{
		this.BM = bm;
		
		
		  try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/MainBoard.fxml")); //"/Boardies/src/gui/MainBoard.fxml"
		        fxmlLoader.setResources(resourceBundle); 
		        //fxmlLoader setController noch machen
		        
		        		fxmlLoader.setController(this);
		        		Parent root1 = fxmlLoader.load();
		                
		                Scene BoardScene = new Scene(root1);
//		                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//		                stage.setX(primaryScreenBounds.getMinX());
//		                stage.setY(primaryScreenBounds.getMinY());
//		                stage.setWidth(primaryScreenBounds.getWidth());
//		                stage.setHeight(primaryScreenBounds.getHeight());
		                stage.setOnCloseRequest(e->{
		                	System.out.println("CLOSE REQUEST");
		                	e.consume();
		                	endProgram();
		                });
		                stage.setScene(BoardScene);
		                stage.setTitle("DOMINION");
		                stage.setFullScreen(false);
		                stage.setResizable(false);
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
	
//		BSkip.setText(resources.getString("options.title"));
		//Bjoin.textProperty().bind(RESOURCE_FACTORY.getStringBinding("options.title"));
		
		MainPane.setId("MainPane");
//		KImgAnwesen.setId("ImgAnwesen");
		
		SPane.setId("SPane");
		APane.setId("APane");
		HandBox.setId("HandBox");
		
		HandBox.setSpacing(5.0);
		
		KImgAnwesen.setImage(imgAnwesenVV);
		KImgKupfer.setImage(imgKupferVV);
		KImgDorf.setImage(imgDorfVV);
		KImgHolz.setImage(imgHolzVV);
		KImgLabor.setImage(imgLabVV);
		KImgSchmied.setImage(imgSchmiedVV);
		KImgMarkt.setImage(imgMarktVV);
		
        
//        RundenCounter.setText(BM.getSlider()+"");
        //HIGHLITINGS
        //Anwesen
        KImgAnwesen.setOnMouseEntered(e->{
        	KImgAnwesen.setFitHeight(190);
    		KImgAnwesen.setFitWidth(140);
    		KImgAnwesen.setImage(imgAnwesenHighVV);
        });
        KImgAnwesen.setOnMouseExited(e->{
        	KImgAnwesen.setFitHeight(170);
        	KImgAnwesen.setFitWidth(120);
        	KImgAnwesen.setImage(imgAnwesenVV);
        });
        //Kupfer
        KImgKupfer.setOnMouseEntered(e->{
        	KImgKupfer.setFitHeight(190);
        	KImgKupfer.setFitWidth(140);
        	KImgKupfer.setImage(imgKupferHighVV);
        });
        KImgKupfer.setOnMouseExited(e->{
        	KImgKupfer.setFitHeight(170);
        	KImgKupfer.setFitWidth(120);
        	KImgKupfer.setImage(imgKupferVV);
        });
        //Holz
        KImgHolz.setOnMouseEntered(e->{
        	KImgHolz.setFitHeight(190);
        	KImgHolz.setFitWidth(140);
        	KImgHolz.setImage(imgHolzHighVV);
        });
        KImgHolz.setOnMouseExited(e->{
        	KImgHolz.setFitHeight(170);
        	KImgHolz.setFitWidth(120);
        	KImgHolz.setImage(imgHolzVV);
        });
        //Dorf KImgDorf
        KImgDorf.setOnMouseEntered(e->{
        	KImgDorf.setFitHeight(190);
        	KImgDorf.setFitWidth(140);
        	KImgDorf.setImage(imgDorfHighVV);
        });
        KImgDorf.setOnMouseExited(e->{
        	KImgDorf.setFitHeight(170);
        	KImgDorf.setFitWidth(120);
        	KImgDorf.setImage(imgDorfVV);
        });
        //Schmied
        KImgSchmied.setOnMouseEntered(e->{
        	KImgSchmied.setFitHeight(190);
        	KImgSchmied.setFitWidth(140);
        	KImgSchmied.setImage(imgSchmiedHighVV);
        });
        KImgSchmied.setOnMouseExited(e->{
        	KImgSchmied.setFitHeight(170);
        	KImgSchmied.setFitWidth(120);
        	KImgSchmied.setImage(imgSchmiedVV);
        });
        //Labor
        KImgLabor.setOnMouseEntered(e->{
        	KImgLabor.setFitHeight(190);
        	KImgLabor.setFitWidth(140);
        	KImgLabor.setImage(imgLabHighVV);
        });
        KImgLabor.setOnMouseExited(e->{
        	KImgLabor.setFitHeight(170);
        	KImgLabor.setFitWidth(120);
        	KImgLabor.setImage(imgLabVV);
        });
        //Markt
        KImgMarkt.setOnMouseEntered(e->{
        	KImgMarkt.setFitHeight(190);
        	KImgMarkt.setFitWidth(140);
        	KImgMarkt.setImage(imgMarktHighVV);
        });
        KImgMarkt.setOnMouseExited(e->{
        	KImgMarkt.setFitHeight(170);
        	KImgMarkt.setFitWidth(120);
        	KImgMarkt.setImage(imgMarktVV);
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

        //BINDINGS korrekt einstellen
//        setStageBindings(MainPane, stage,1,1);
//        
//        setBindings(GPane, MainPane, 0.001 , 0.7);
//        
//        setBindings(HandBox, SPane, 1, 1);
//        setBindings(SPane, MainPane, 0.29, 0.69);
//        setBindings(LInfo, MainPane, 0.1, 0.5);
//        setBindings(LPointsP1, MainPane, 0.1 ,0.5);
//        setBindings(LPointsP2, MainPane, 0.1, 0.5);
//        setBindings(RundenCounter, MainPane, 0.1,0.5);
//        setBindings(DeckZahl, MainPane, 0.1, 0.5);
           	
//    	BM.newestMessage.addListener( (o, oldValue, newValue) -> FillHand());

		
        
//		RundenCounter.setText(Controller.getRundenZahl()+"");
}
	
//	@FXML
//	private void AktionButton(){
//		System.out.println("hoi");
//		DisableGrid();
//	}
//	
//	@FXML
//	private void KaufAnwesen(){
//		System.out.println("Anwesen gekauft");
//		
//		FillHand("test");
//		
//	}
//	
//	@FXML 
//	private void SkipButton(){			//Dies wahrsheinlich im CSS versuchen
//		EnableGrid();
//
//	}
	
	
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
//				        imgVAnwesen.setOnMouseClicked(e->{	
//				        BM.getMh().send(imgVAnwesen.getId());
//				        imgVAnwesen.setDisable(true);
//				        imgVAnwesen.setImage(imgAnwesenSW);
//				        });
				        
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
			
			if(iterable_element.toLowerCase().contains("deck"))
			{
				
				DeckZahl.setText(iterable_element);
			}
			
			
			if(iterable_element.toLowerCase().contains("budget"))
			{
				
				LKonto.setText(iterable_element);
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
				HandBox.setDisable(false);
				DisableGrid();
				//enable aktionskarten
				for (Node child : HandBox.getChildren()) {
				    ImageView imgViewTest = (ImageView) child;
				    if (imgViewTest.getImage().equals(imgDorf)){
				    	imgViewTest.setDisable(false);
				    	imgViewTest.setImage(imgDorf);
				    }
				    if (imgViewTest.getImage().equals(imgMarkt)){
				    	imgViewTest.setDisable(false);
				    	imgViewTest.setImage(imgMarkt);
				    }
				    if (imgViewTest.getImage().equals(imgSchmied)){
				    	imgViewTest.setDisable(false);
				    	imgViewTest.setImage(imgSchmied);
				    }
				    if (imgViewTest.getImage().equals(imgHolz)){
				    	imgViewTest.setDisable(false);
				    	imgViewTest.setImage(imgHolz);
				    }
				    if (imgViewTest.getImage().equals(imgLab)){
				    	imgViewTest.setDisable(false);
				    	imgViewTest.setImage(imgLab);
				    }
				    if (imgViewTest.getImage().equals(imgKupfer) && !imgViewTest.isDisable()){
				    	imgViewTest.setDisable(true);
				    	imgViewTest.setImage(imgKupferSW);
				    }
			}}
			if(iterable_element.toLowerCase().contains("action"))
			{
				
				HandBox.setDisable(false);
				DisableGrid();
				//FOR SCHLEIFE MACHEN UM
			}
			
			if(iterable_element.toLowerCase().contains("purchasehand"))
			{	
				//infobox melden	//test mit anwesen machen //schmied, markt, dorf, labor, holzfäller
				
				HandBox.setDisable(false);
				for (Node child : HandBox.getChildren()) {
				    ImageView imgViewTest = (ImageView) child;
				    if (imgViewTest.getImage().equals(imgDorf) && !imgViewTest.isDisable()){
				    	imgViewTest.setDisable(true);
				    	imgViewTest.setImage(imgDorfSW);
				    }
				    if (imgViewTest.getImage().equals(imgMarkt) && !imgViewTest.isDisable()){
				    	imgViewTest.setDisable(true);
				    	imgViewTest.setImage(imgMarktSW);
				    }
				    if (imgViewTest.getImage().equals(imgSchmied) && !imgViewTest.isDisable()){
				    	imgViewTest.setDisable(true);
				    	imgViewTest.setImage(imgSchmiedSW);
				    }
				    if (imgViewTest.getImage().equals(imgHolz) && !imgViewTest.isDisable()){
				    	imgViewTest.setDisable(true);
				    	imgViewTest.setImage(imgHolzSW);
				    }
				    if (imgViewTest.getImage().equals(imgLab) && !imgViewTest.isDisable()){
				    	imgViewTest.setDisable(true);
				    	imgViewTest.setImage(imgLabSW);
				    }
				    if (imgViewTest.getImage().equals(imgKupfer)){
				    	imgViewTest.setDisable(false);
				    	imgViewTest.setImage(imgKupfer);
				    }
				 
				}
				
				
				//disable aktionskarten
				//enable kaufkarten
			}
			
			if(iterable_element.toLowerCase().contains("purchasestock"))
			{
				//infobox melden
				EnableGrid();
				HandBox.setDisable(true);
				//disable hand
				//enable vorrat
			}
			
			if(iterable_element.toLowerCase().contains("end"))
			{
				LInfo.setText(iterable_element);
				Hclear();
				HandBox.setDisable(true);
				DisableGrid();
				//disable hand
				//disable vorrat
			}
			
			if(iterable_element.toLowerCase().contains("roundend"))
			{
				//infobox melden
				
				Hclear();
				DisableGrid();
				HandBox.setDisable(true);
				
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
	protected void setBindings(Region child, Region parent, double heightMultiply, double widthMultiply){
		child.maxHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
		child.maxWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
		child.minHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
		child.minWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
	}
	protected void setStageBindings(Region child, Stage stage2, double heightMultiply, double widthMultiply){
		child.maxHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
		child.maxWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
		child.minHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
		child.minWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
	}
	public void endProgram(){
			Stage Exitwindow = new Stage();
			
			Exitwindow.initModality(Modality.APPLICATION_MODAL); 		
			Exitwindow.setTitle("");
			Exitwindow.setMinWidth(250);
			Label label = new Label("Spiel Beenden?");   //CLOSE GAME?
			Button yesButton = new Button("JA");		//YES
			Button noButton = new Button("NEIN");		//NO
			
			yesButton.setOnAction(e -> {
				answer = true;
				Exitwindow.close();
			});
			
			noButton.setOnAction(e -> {
				answer = false;
				Exitwindow.close();
			});
			
			if (answer) {
				//Server Threads alle hier schliessen
				Platform.exit();
			}
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label, yesButton, noButton);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			Exitwindow.setScene(scene);
			Exitwindow.showAndWait();  
		}	
	
	
}