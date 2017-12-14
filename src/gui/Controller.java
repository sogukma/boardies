package gui;
									import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
//FRAGEN OB MÖGLICH BUTTONS IN HBOX REINZUGEBEN MIT BUTTONCLICK IN SCENEBUILDER
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import Chat.ChatClient;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.HostServices;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
	
	@FXML
	private Pane LoginPane;
	
	private static final ObservableResourceFactory RESOURCE_FACTORY = new ObservableResourceFactory();

	
	
	private static final String RESOURCE_NAME = "Lecture04_sampleSolution_de";
	private static final String RESOURCE_EN = "Lecture04_sampleSolution_en";
	
    static {
        RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME));
    }

	
	URL url = getClass().getResource("Anleitung.pdf");
	File pdfD = new File(url.getPath());

	URL urlE = getClass().getResource("Manual.pdf");
	File pdfE = new File(urlE.getPath());
	Image imgGold = new Image("/Gold_mini.jpg");
	
	
	//Client c = new Client();
	private BoardModel BM;
//	BoardControllerFXML bcf = new BoardControllerFXML();
	
	private BoardControllerFXML bcf;
	public Controller(BoardModel bm) {
		this.BM = bm;
		this.bcf = null;
		//BoardControllerFXML

		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
//		ImageView img = new ImageView("file:///Users/halilcenik/git/boardies/src/gui/Gold_mini.jpg");
//		img.setId("image");
//		img.setPickOnBounds(true);
//        img.setFitHeight(140);
//        img.setFitWidth(90);
         
		new Thread(BM).start();
		
		BM.PrintStuff();
		
//		Platform.runLater(new Thread(c).start());
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
		
		Bjoin.textProperty().bind(RESOURCE_FACTORY.getStringBinding("options.title"));
//		Bjoin.setText(resources.getString("options.title"));
		RundenZahl.setText(RundenSlider.getValue()+"");
		
		

		ToggleD.selectedProperty().addListener(((observable, oldValue, newValue)->{
			RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, Locale.GERMAN));
			
			
		}));
		
		ToggleE.selectedProperty().addListener(((observable, oldValue, newValue)->{
			RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_EN, Locale.ENGLISH));	
			
			
		}));
		
		RundenSlider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	BM.SaveSlider(newValue.intValue());
		    	System.out.println(newValue.intValue()+"NewValue");
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
//		System.out.println(PlayerName);
		//Open Board
		
		BM.sendName(PlayerName);
		String[] abc = {};
		ChatClient.main(abc);
		
		this.bcf = new BoardControllerFXML(this.BM, RESOURCE_FACTORY.getResources());
		
//		MainGameFX a = new MainGameFX();
//		a.getStage().hide();
		//CODE RAUSGENOMMEN

//		BM.newestMessage.addListener( (o, oldValue, newValue) -> bcf.FillHand());

	}
	
	@FXML
	private void ServerStart(){
		//Überprüfen ob Server schon läuft, sonst starten
		System.out.println("");
	}
	
	@FXML
	private void AnleitungD(){
	
		try {
			Desktop.getDesktop().open(pdfD);
			}
			catch (IOException e) { 
				System.out.println("fail");
			}
		}
	
	
	@FXML 
	private void AnleitungE(){

		try {
		Desktop.getDesktop().open(pdfE);
		}
		catch (IOException e) { 
			System.out.println("fail");
		}
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
