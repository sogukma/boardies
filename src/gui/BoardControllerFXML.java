package gui;
									//FRAGEN OB MÖGLICH BUTTONS IN HBOX REINZUGEBEN MIT BUTTONCLICK IN SCENEBUILDER
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
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

	
	@FXML
	private ImageView KImgAnwesen, KImgKupfer, KImgHolz, KImgDorf, KImgSchmied, KImgLabor, KImgMarkt, KImgSilber, KImgGold, KImgProvinz, KImgHerzog;
	
	@FXML
	private Label LPointsP1, LPointsP2, RundenCounter, DeckZahl, LRequest, LKonto, LReport;
	
	
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
	
	Image imgGold = new Image("/Gold.jpg");
	Image imgKupfer = new Image("/Kupfer.jpg");
	Image imgSilber = new Image("/Silber.jpg");
	Image imgAnwesen = new Image("/Punkte01.jpg"); 
	Image imgHerzog = new Image("/Punkte03.jpg");
	Image imgProvinz = new Image("/Punkte06.jpg");
	Image imgDorf = new Image("/Dorf.jpg");
	Image imgHolz = new Image("/Holz.jpg");
	Image imgLab = new Image("/Labor.jpg");
	Image imgMarkt = new Image("/Markt.jpg");
	Image imgSchmied = new Image("/Schmiede.jpg");
	//HIGHLIGHTED
	Image imgGoldHigh = new Image("/Gold_high.jpg");
	Image imgSilberHigh = new Image("/Silber_high.jpg");
	Image imgHerzogHigh = new Image("/Punkte03_high.jpg");
	Image imgProvinzHigh = new Image("/Punkte06_high.jpg");
	Image imgKupferHigh = new Image("/Kupfer_high.jpg");
	Image imgAnwesenHigh = new Image("/Punkte01_high.jpg"); 
	Image imgDorfHigh = new Image("/Dorf_high.jpg");
	Image imgHolzHigh = new Image("/Holz_high.jpg");
	Image imgLabHigh = new Image("/Labor_high.jpg");
	Image imgMarktHigh = new Image("/Markt_high.jpg");
	Image imgSchmiedHigh = new Image("/Schmiede_high.jpg");
	//BLACK-WHITE
	Image imgGoldSW = new Image("/Gold_sw.jpg");
	Image imgSilberSW = new Image("/Silber_sw.jpg");
	Image imgHerzogSW = new Image("/Punkte03_sw.jpg");
	Image imgProvinzSW = new Image("/Punkte06_sw.jpg");
	Image imgKupferSW = new Image("/Kupfer_sw.jpg");
	Image imgAnwesenSW = new Image("/Punkte01_sw.jpg"); 
	Image imgDorfSW = new Image("/Dorf_sw.jpg");
	Image imgHolzSW = new Image("/Holz_sw.jpg");
	Image imgLabSW = new Image("/Labor_sw.jpg");
	Image imgMarktSW = new Image("/Markt_sw.jpg");
	Image imgSchmiedSW = new Image("/Schmiede_sw.jpg");
	
	//VorratBilder
	Image imgGoldVV= new Image("/GoldVV.jpg");
	Image imgSilberVV = new Image("/SilberVV.jpg");
	Image imgHerzogVV = new Image("/Punkte03VV.jpg");
	Image imgProvinzVV = new Image("/Punkte06VV.jpg");
	Image imgKupferVV = new Image("/KupferVV.jpg");
	Image imgAnwesenVV = new Image("/Punkte01VV.jpg"); 
	Image imgDorfVV = new Image("/DorfVV.jpg");
	Image imgHolzVV = new Image("/HolzVV.jpg");
	Image imgLabVV = new Image("/LaborVV.jpg");
	Image imgMarktVV = new Image("/MarktVV.jpg");
	Image imgSchmiedVV = new Image("/SchmiedeVV.jpg");
	//HIGHLIGHTED
	Image imgGoldHighVV= new Image("/Gold_highVV.jpg");
	Image imgSilberHighVV = new Image("/Silber_highVV.jpg");
	Image imgHerzogHighVV = new Image("/Punkte03_highVV.jpg");
	Image imgProvinzHighVV = new Image("/Punkte06_highVV.jpg");
	Image imgKupferHighVV = new Image("/Kupfer_highVV.jpg");
	Image imgAnwesenHighVV = new Image("/Punkte01_highVV.jpg"); 
	Image imgDorfHighVV = new Image("/Dorf_highVV.jpg");
	Image imgHolzHighVV = new Image("/Holz_highVV.jpg");
	Image imgLabHighVV = new Image("/Labor_highVV.jpg");
	Image imgMarktHighVV = new Image("/Markt_highVV.jpg");
	Image imgSchmiedHighVV = new Image("/Schmiede_highVV.jpg");
	//BLACK-WHITE
	Image imgGoldSWVV = new Image("/Gold_swVV.jpg");
	Image imgSilberSWVV = new Image("/Silber_swVV.jpg");
	Image imgHerzogSWVV = new Image("/Punkte03_swVV.jpg");
	Image imgProvinzSWVV = new Image("/Punkte06_swVV.jpg");
	Image imgKupferSWVV = new Image("/Kupfer_swVV.jpg");
	Image imgAnwesenSWVV = new Image("/Punkte01_swVV.jpg"); 
	Image imgDorfSWVV = new Image("/Dorf_swVV.jpg");
	Image imgHolzSWVV = new Image("/Holz_swVV.jpg");
	Image imgLabSWVV = new Image("/Labor_swVV.jpg");
	Image imgMarktSWVV = new Image("/Markt_swVV.jpg");
	Image imgSchmiedSWVV = new Image("/Schmiede_swVV.jpg");
	
	private BoardModel BM;
	Stage stage = new Stage();
	
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
		                stage.setTitle(resources.getString("opening.title"));
		                stage.setFullScreen(false);
		                stage.setResizable(false);
		                BoardScene.getStylesheets().add(getClass().getResource("Dominion.css").toExternalForm());
		                stage.show();
		                
		            
		   
//		                BoardControllerFXML.setRundenZahl(RundenZahl);			//Geht nicht weil label static muss, DARF NICHT
		                
		               
		                
		        } catch(Exception e) {
		           e.printStackTrace();
		        }	
		  
			BM.newMessage.addListener( (o, oldValue, newValue) -> this.updateMessages(newValue));
			BM.newCards.addListener( (o, oldValue, newValue) -> this.updateHand(newValue));
	}
	
	
	private void updateLabel(String message, String keyValue, Label l)
	{
		l.setText("");
		for (String i : message.split(";")) {
			if(i.contains(keyValue))
			{
				l.setText(l.getText()+" "+resources.getString(i));
			}
			else
			{
				l.setText(l.getText()+" "+i);
			}
		}
	}

	private void updateMessages(String message) {
		
		Platform.runLater(
				()->{
		
					if(message.toLowerCase().contains("request"))
					{
						updateLabel(message, "request", LRequest);

					}
					
					if(message.toLowerCase().contains("report"))
					{
						updateLabel(message, "report", LReport);
					}
					
					
					if(message.toLowerCase().contains("deck"))
					{	
						DeckZahl.setText(message);
					}
					
					if(message.toLowerCase().contains("budget"))
					{
						updateLabel(message, "budget", LKonto);
					}
					
					
					if(message.toLowerCase().contains("ownpoints"))
					{
						updateLabel(message, "ownpoints", LPointsP1);						
					}
					
					if(message.toLowerCase().contains("opponentpoints"))
					{
						updateLabel(message, "opponentpoints", LPointsP2);
					}
					
					
					if(message.toLowerCase().contains("round"))
					{
						updateLabel(message, "round", RundenCounter);						
						RundenCounter.setText(RundenCounter.getText()+"/"+Main.AMOUNT_OF_ROUNDS);

		}
					if(message.toLowerCase().contains("action"))
					{
						System.out.println("Actionbearbeitung GUI gestartet");
						HandBox.setDisable(false);
						DisableGrid();
						prepareHandForAction();
						System.out.println("Actionbearbeitung GUI zuende");
					}
					
					if(message.toLowerCase().contains("purchasehand"))
					{	
						System.out.println("Kaufkartenbearbeitung GUI gestartet");
						DisableGrid();
						LReport.setText("");
						HandBox.setDisable(false);
						prepareHandForPurchase();
						System.out.println("Kaufkartenbearbeitung GUI zuende");

					}
					
					if(message.toLowerCase().contains("purchasestock"))
					{
						
						EnableGrid();
						HandBox.setDisable(true);
					}
					
					if(message.toLowerCase().contains("end"))
					{
						LRequest.setText(resources.getString(message));
						Hclear();
						HandBox.setDisable(true);
						DisableGrid();
					}
					
					if(message.toLowerCase().contains("rndfertig"))
					{
						LRequest.setText(resources.getString("request.roundend"));
						Hclear();
						LKonto.setText("Budget: 0");
						LReport.setText("");
						DisableGrid();
						HandBox.setDisable(true);
						
					}
		
	});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
//		BSkip.setText(resources.getString("options.title"));
		//Bjoin.textProperty().bind(RESOURCE_FACTORY.getStringBinding("options.title"));
		this.resources = resources;
		MainPane.setId("MainPane");
		
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
		KImgHerzog.setImage(imgHerzogVV);
		KImgProvinz.setImage(imgProvinzVV);
		KImgGold.setImage(imgGoldVV);
		KImgSilber.setImage(imgSilberVV);
		
		LRequest.setText(resources.getString("request.roundend"));
		LPointsP1.setText(resources.getString("main.ownpoints"));
		LPointsP2.setText(resources.getString("main.opponentpoints"));
		RundenCounter.setText("1/"+Main.AMOUNT_OF_ROUNDS); //Nichts machen
		DeckZahl.setText("Deck: "+Main.INITIAL_DECK_SIZE);
		LKonto.setText("Budget");
//		LReport.setText("Reportings here"); //bitte übersetzen *Mit Malik anschauen
		
        //HIGHLITINGS
		//Silber
		KImgSilber.setOnMouseEntered(e->{
			KImgSilber.setFitHeight(165);
			KImgSilber.setFitWidth(130);
			KImgSilber.setImage(imgSilberHighVV);
		});
		KImgSilber.setOnMouseExited(e->{
			KImgSilber.setFitHeight(145);
			KImgSilber.setFitWidth(110);
			KImgSilber.setImage(imgSilberVV);
		});
		//Gold
		KImgGold.setOnMouseEntered(e->{
			KImgGold.setFitHeight(165);
			KImgGold.setFitWidth(130);
			KImgGold.setImage(imgGoldHighVV);
		});
		KImgGold.setOnMouseExited(e->{
			KImgGold.setFitHeight(145);
			KImgGold.setFitWidth(110);
			KImgGold.setImage(imgGoldVV);
		});
		//Herzog
		KImgHerzog.setOnMouseEntered(e->{
			KImgHerzog.setFitHeight(165);
			KImgHerzog.setFitWidth(130);
			KImgHerzog.setImage(imgHerzogHighVV);
		});
		KImgHerzog.setOnMouseExited(e->{
			KImgHerzog.setFitHeight(145);
			KImgHerzog.setFitWidth(110);
			KImgHerzog.setImage(imgHerzogVV);
		});
		//Provinz
		KImgProvinz.setOnMouseEntered(e->{
			KImgProvinz.setFitHeight(165);
			KImgProvinz.setFitWidth(130);
			KImgProvinz.setImage(imgProvinzHighVV);
		});
		KImgProvinz.setOnMouseExited(e->{
			KImgProvinz.setFitHeight(145);
			KImgProvinz.setFitWidth(110);
			KImgProvinz.setImage(imgProvinzVV);
		});
        //Anwesen
        KImgAnwesen.setOnMouseEntered(e->{
        	KImgAnwesen.setFitHeight(165);
    		KImgAnwesen.setFitWidth(130);
    		KImgAnwesen.setImage(imgAnwesenHighVV);
        });
        KImgAnwesen.setOnMouseExited(e->{
        	KImgAnwesen.setFitHeight(145);
        	KImgAnwesen.setFitWidth(110);
        	KImgAnwesen.setImage(imgAnwesenVV);
        });
        //Kupfer
        KImgKupfer.setOnMouseEntered(e->{
        	KImgKupfer.setFitHeight(165);
        	KImgKupfer.setFitWidth(130);
        	KImgKupfer.setImage(imgKupferHighVV);
        });
        KImgKupfer.setOnMouseExited(e->{
        	KImgKupfer.setFitHeight(145);
        	KImgKupfer.setFitWidth(110);
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
        	e->{BM.getMh().send(Stock.COPPER_ID+"");
        });
        KImgSilber.setOnMouseClicked(
            	e->{BM.getMh().send(Stock.SILVER_ID+"");
        });
        KImgGold.setOnMouseClicked(
            	e->{BM.getMh().send(Stock.GOLD_ID+"");
            });
        KImgAnwesen.setOnMouseClicked(
            	e->{BM.getMh().send(Stock.ESTATE_ID+"");
            });
        KImgHerzog.setOnMouseClicked(
            	e->{BM.getMh().send(Stock.DUCHY_ID+"");
            });
        KImgProvinz.setOnMouseClicked(
            	e->{BM.getMh().send(Stock.PROVINCE_ID+"");
            });
        KImgLabor.setOnMouseClicked(
            	e->{BM.getMh().send(Stock.LABORATORY_ID+"");
            });
        
        KImgMarkt.setOnMouseClicked(
            	e->{BM.getMh().send(Stock.MARKET_ID+"");
            });
        
        KImgDorf.setOnMouseClicked(
            	e->{BM.getMh().send(Stock.VALLEY_ID+"");
            });

        KImgSchmied.setOnMouseClicked(
            	e->{BM.getMh().send(Stock.SMITH_ID+"");
            });
        
        KImgHolz.setOnMouseClicked(
            	e->{BM.getMh().send(Stock.LUMBERJACK_ID+"");
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
        
	} //end Initialize
	
		
	
	private void fillKupfer(int id)
	{
			        ImageView imgVKupfer = new ImageView(imgKupfer);  
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
						
	}
	private void fillSilber(int id)
	{
			        ImageView imgVSilber = new ImageView(imgSilber);  
			        imgVSilber.setId(id+"");
			        imgVSilber.setPickOnBounds(true);
			        imgVSilber.setFitHeight(140);
			        imgVSilber.setFitWidth(90);
			        imgVSilber.setOnMouseClicked(e->{	
			        BM.getMh().send(imgVSilber.getId());
			        imgVSilber.setDisable(true);
			        imgVSilber.setImage(imgSilberSW);
			        });
			        
			        HandBox.getChildren().add(imgVSilber);
						
	}
	private void fillGold(int id)
	{
			        ImageView imgVGold = new ImageView(imgGold);  
			        imgVGold.setId(id+"");
			        imgVGold.setPickOnBounds(true);
			        imgVGold.setFitHeight(140);
			        imgVGold.setFitWidth(90);
			        imgVGold.setOnMouseClicked(e->{	
			        BM.getMh().send(imgVGold.getId());
			        imgVGold.setDisable(true);
			        imgVGold.setImage(imgGoldSW);
			        });
			        
			        HandBox.getChildren().add(imgVGold);
						
	}
	
	private void fillHerzog(int id)
	{

						ImageView imgVHerzog = new ImageView(imgHerzog);  
						imgVHerzog.setId(id+"");
						imgVHerzog.setPickOnBounds(true);
						imgVHerzog.setFitHeight(140);
						imgVHerzog.setFitWidth(90);
				        HandBox.getChildren().add(imgVHerzog);

	}
	private void fillProvinz(int id)
	{

						ImageView imgVProvinz = new ImageView(imgProvinz);  
						imgVProvinz.setId(id+"");
						imgVProvinz.setPickOnBounds(true);
						imgVProvinz.setFitHeight(140);
						imgVProvinz.setFitWidth(90);
				        HandBox.getChildren().add(imgVProvinz);

	}
	private void fillAnwesen(int id)
	{

						ImageView imgVAnwesen = new ImageView(imgAnwesen);  
				        imgVAnwesen.setId(id+"");
				        imgVAnwesen.setPickOnBounds(true);
				        imgVAnwesen.setFitHeight(140);
				        imgVAnwesen.setFitWidth(90);
				        HandBox.getChildren().add(imgVAnwesen);

	}
	
	private void fillDorf(int id)
	{
			        ImageView imgVDorf = new ImageView(imgDorf); 
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

	}
	
	private void fillHolz(int id)
	{

			        ImageView imgVHolz = new ImageView(imgHolz);  
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

	}
	
	private void fillLab(int id)
	{

			        ImageView imgVLab = new ImageView(imgLab); 
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

	}
	
	private void fillMarkt(int id)
	{

			           
			        ImageView imgVMarkt = new ImageView(imgMarkt);  
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
			        

	}
	
	
	private void fillSchmied(int id)
	{

				    ImageView imgVSchmied = new ImageView(imgSchmied);  
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
			        

	}
	
	private void updateHand(String cards){

		Platform.runLater(
				()->{
				
					
		int id = 0;
	ArrayList<String> splittedMessage = new ArrayList<String>();
		Hclear();
		for (String iterable_element : cards.split(",")) {
			splittedMessage.add(iterable_element);

						
			if(iterable_element.toLowerCase().contains("estate"))
				{
					fillAnwesen(id);
				}
			if(iterable_element.toLowerCase().contains("dutchy"))
			{
				fillHerzog(id);			
			}
			if(iterable_element.toLowerCase().contains("province"))
			{
				fillProvinz(id);			
			}
			if(iterable_element.toLowerCase().contains("copper"))
				{
				fillKupfer(id);			
				}
			if(iterable_element.toLowerCase().contains("silver"))
			{
				fillSilber(id);			
			}
			if(iterable_element.toLowerCase().contains("gold"))
			{
				fillGold(id);			
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
		
		System.out.println("ende");
				});
		
	}
	
	private void prepareHandForPurchase() {
		for (Node child : HandBox.getChildren()) {
		    ImageView imgViewTest = (ImageView) child;
		    if (imgViewTest.getImage().equals(imgDorf)){
		    	imgViewTest.setDisable(true);
		    	imgViewTest.setImage(imgDorfSW);
		    }
		    if (imgViewTest.getImage().equals(imgMarkt)){
		    	imgViewTest.setDisable(true);
		    	imgViewTest.setImage(imgMarktSW);
		    }
		    if (imgViewTest.getImage().equals(imgSchmied)){
		    	imgViewTest.setDisable(true);
		    	imgViewTest.setImage(imgSchmiedSW);
		    }
		    if (imgViewTest.getImage().equals(imgHolz)){
		    	imgViewTest.setDisable(true);
		    	imgViewTest.setImage(imgHolzSW);
		    }
		    if (imgViewTest.getImage().equals(imgLab)){
		    	imgViewTest.setDisable(true);
		    	imgViewTest.setImage(imgLabSW);
		    }
		    if (imgViewTest.getImage().equals(imgKupfer) || imgViewTest.getImage().equals(imgKupferSW)){
		    	imgViewTest.setDisable(false);
		    	imgViewTest.setImage(imgKupfer);
		    }
		    if (imgViewTest.getImage().equals(imgSilber) || imgViewTest.getImage().equals(imgSilberSW)){
		    	imgViewTest.setDisable(false);
		    	imgViewTest.setImage(imgSilber);
		    }
		    if (imgViewTest.getImage().equals(imgGold) || imgViewTest.getImage().equals(imgGoldSW)){
		    	imgViewTest.setDisable(false);
		    	imgViewTest.setImage(imgGold);
		    }
		}
		
	}

	private void prepareHandForAction() {
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
		    if (imgViewTest.getImage().equals(imgSilber) && !imgViewTest.isDisable()){
		    	imgViewTest.setDisable(true);
		    	imgViewTest.setImage(imgSilberSW);
		    }
		    if (imgViewTest.getImage().equals(imgGold) && !imgViewTest.isDisable()){
		    	imgViewTest.setDisable(true);
		    	imgViewTest.setImage(imgGoldSW);
		    }
	}
		
	}

	public void DisableGrid(){
		GPane.setDisable(true);
		KImgAnwesen.setImage(imgAnwesenSWVV);
		KImgKupfer.setImage(imgKupferSWVV);
		KImgHolz.setImage(imgHolzSWVV);
		KImgDorf.setImage(imgDorfSWVV);
		KImgSchmied.setImage(imgSchmiedSWVV);
		KImgLabor.setImage(imgLabSWVV);
		KImgMarkt.setImage(imgMarktSWVV);
		KImgHerzog.setImage(imgHerzogSWVV);
		KImgProvinz.setImage(imgProvinzSWVV);
		KImgGold.setImage(imgGoldSWVV);
		KImgSilber.setImage(imgSilberSWVV);
	}
	public void EnableGrid(){
		GPane.setDisable(false);
		KImgAnwesen.setImage(imgAnwesenVV);
		KImgKupfer.setImage(imgKupferVV);
		KImgHolz.setImage(imgHolzVV);
		KImgDorf.setImage(imgDorfVV);
		KImgSchmied.setImage(imgSchmiedVV);
		KImgLabor.setImage(imgLabVV);
		KImgMarkt.setImage(imgMarktVV);
		KImgHerzog.setImage(imgHerzogVV);
		KImgProvinz.setImage(imgProvinzVV);
		KImgGold.setImage(imgGoldVV);
		KImgSilber.setImage(imgSilberVV);
	}
	public void Hclear(){
		HandBox.getChildren().clear();
	}
//	protected void setBindings(Region child, Region parent, double heightMultiply, double widthMultiply){
//		child.maxHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
//		child.maxWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
//		child.minHeightProperty().bind(parent.heightProperty().multiply(heightMultiply));
//		child.minWidthProperty().bind(parent.widthProperty().multiply(widthMultiply));
//	}
//	protected void setStageBindings(Region child, Stage stage2, double heightMultiply, double widthMultiply){
//		child.maxHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
//		child.maxWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
//		child.minHeightProperty().bind(stage2.heightProperty().multiply(heightMultiply));
//		child.minWidthProperty().bind(stage2.widthProperty().multiply(widthMultiply));
//	}
	public void endProgram(){
			Stage Exitwindow = new Stage();
			
			Exitwindow.initModality(Modality.APPLICATION_MODAL); 		
			Exitwindow.setTitle("");
			Exitwindow.setMinWidth(250);
			Exitwindow.setMinHeight(300);
						Label label = new Label(resources.getString("game.close"));   //CLOSE GAME?
						Button yesButton = new Button(resources.getString("game.yes"));		//YES
						Button noButton = new Button(resources.getString("game.no"));		//NO
					
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

//				System.exit(1);
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