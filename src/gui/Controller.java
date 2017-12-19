package gui;
									import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
//FRAGEN OB MÖGLICH BUTTONS IN HBOX REINZUGEBEN MIT BUTTONCLICK IN SCENEBUILDER
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;


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
//import sun.audio.AudioData;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//import sun.audio.ContinuousAudioDataStream;
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
	private ToggleButton ToggleD, ToggleE, ToggleMYes, ToggleMNo;
	
	@FXML
	private HBox StartBox, SettingsBox, AnleitungBox, LoginBox;
	
	@FXML 
	private TextField TName;
	
	@FXML 
	private Label SpracheText, LMusik;
	
	@FXML
	private Pane LoginPane;
	
	private static final ObservableResourceFactory RESOURCE_FACTORY = new ObservableResourceFactory();
	
	
	boolean Play_Audio=true;
	//auskommentiert
	URL urlAudio = getClass().getResource("Medieval_Music.wav");
	File Clap = new File(urlAudio.getPath());
	
	private static final String RESOURCE_NAME = "Language_de";
	private static final String RESOURCE_EN = "Language_en";
	
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
//		PlaySound();
		Blogin.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.go"));
		Banleitung.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.guide"));
		Beinstellungen.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.prop"));
		Bquit.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.quit"));
		Bjoin.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.go"));
		Bback1.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.back1"));
		BreadmeD.setText("ANLEITUNG (D)");	//Burda birsey yapma
		BreadmeE.setText("MANUAL (E)");		//Burda birsey yapma
		Bback2.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.back2"));
		SpracheText.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.lang"));
		Bback3.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.back3"));
		TName.setPromptText("Name");     //Burda birsey yapma
		LMusik.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.music"));
		ToggleMYes.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.yes"));
		ToggleMNo.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.no"));
		
		
//		Blogin.setText("opening.login");
//		Banleitung.setText("opening.guide");
//		Beinstellungen.setText("opening.prop");
//		Bquit.setText("opening.quit");
//		//Bjoin.setText("start.go");
//		Bback1.setText("Zurück!");
//		BreadmeD.setText("ANLEITUNG (D)");	//Burda birsey yapma
//		BreadmeE.setText("MANUAL (E)");		//Burda birsey yapma
//		Bback2.setText("Zurück!");
//		SpracheText.setText("Sprache");
//		Bback3.setText("Zurück!");
//		TName.setPromptText("Name");     //Burda birsey yapma
		
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
		 
		 ToggleGroup Mgroup = new ToggleGroup();
		 ToggleMYes.setToggleGroup(Mgroup);
		 ToggleMNo.setToggleGroup(Mgroup);
		 ToggleMYes.setSelected(true);
		
		Blogin.setId("button");
		Beinstellungen.setId("button");
		Bquit.setId("button");
		
//		Bjoin.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.go"));
//		Bjoin.setText(resources.getString("options.title"));
//		RundenZahl.setText(RundenSlider.getValue()+"");
		
		

		ToggleD.selectedProperty().addListener(((observable, oldValue, newValue)->{
			RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, Locale.GERMAN));
			
			
		}));
		
		ToggleE.selectedProperty().addListener(((observable, oldValue, newValue)->{
			RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_EN, Locale.ENGLISH));	
			
			
		}));
		
//		RundenSlider.valueProperty().addListener(new ChangeListener<Number>() {
//		    @Override
//		    public void changed(ObservableValue<? extends Number> observable,
//		            Number oldValue, Number newValue) {
//		    	BM.SaveSlider(newValue.intValue());
//		    	System.out.println(newValue.intValue()+"NewValue");
//		        RundenZahl.setText(newValue.intValue()+"");
//		    }
//		});
		

	}
	
//	ImageView img = new ImageView("file:///Users/halilcenik/git/boardies/src/gui/Gold_mini.jpg");
//    img.setPickOnBounds(true);
	
	
	
	
	@FXML
	private void quit(){
		Platform.exit();
		Map<Thread, StackTraceElement[]> m = Thread.getAllStackTraces();
		try{
		for(Map.Entry<Thread, StackTraceElement[]> entry : m.entrySet()){
			
			entry.getKey().sleep(500);
			entry.getKey().interrupt();	
			
		}}catch(InterruptedException ee){
			Thread.currentThread().interrupt();
		}catch(Exception eee){}
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

		if(PlayerName != null && !PlayerName.isEmpty()){
		BM.sendName(PlayerName);
		PlaySound();
		BM.SaveName(PlayerName);
		String[] abc = {};
		ChatClient.main(abc);
		
		this.bcf = new BoardControllerFXML(this.BM, RESOURCE_FACTORY.getResources());
		}else{TName.setPromptText("Name!?!");} //Don't forget your Name
//		MainGameFX a = new MainGameFX();
//		a.getStage().hide();
		//CODE RAUSGENOMMEN

//		BM.newestMessage.addListener( (o, oldValue, newValue) -> bcf.FillHand());

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
		System.out.println("Deutsch gew\u00e4hlt");
	}
	
	@FXML 
	private void ChangeEnglish(){
		//Alle SprachEinstellungen Englisch machen
		System.out.println("English chosen");
	}
	@FXML
	private void MusicOn(){
		Play_Audio=true;
		System.out.println("Audio true");
	}
	@FXML
	private void MusicOff(){
		Play_Audio=false;
		System.out.println("Audio false");
	}
	
	
	public void PlaySound(){
		
		new Thread(new Runnable(){
				
			@Override
			public void run(){
				
				if(Play_Audio){
			try{
				
				
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(Clap));
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-15.0f);
				clip.start();
				clip.loop(clip.LOOP_CONTINUOUSLY);
				
				
				Thread.sleep(clip.getMicrosecondLength()/1000);
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Audio Failed");
		}
		
	}		else{};
		
	}
			}).start();}
	
	
	
//	public void PlaySound(){
//		AudioPlayer MGP = AudioPlayer.player;
//		AudioStream BGM;
//		AudioData MD;
//		ContinuousAudioDataStream loop = null;
//		try{
//			BGM = new AudioStream(new FileInputStream(Clap));
//			MD = BGM.getData();
//			loop = new ContinuousAudioDataStream(MD);
//		}catch(IOException e){}
//		MGP.start(loop);
//	}
//	public static Label getRundenZahl() {
//		return RundenZahl;
//	}

//	public static void setRundenZahl(Label rundenZahl) {
//		RundenZahl = rundenZahl;
//	}
	
	
	
}
