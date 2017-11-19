package testingarea;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoardView {				//Diesen View als MVC betrachten und anpassen
	
	
	private BoardModel model;		//REGION REIN TUN
	private Stage window;
	
//	String style = getClass().getResource("Dominion.css").toExternalForm();
	
	protected Scene boardScene;
	protected Label pointsL;
	protected TextArea ChatArea;
	protected TextField ChatField;
	protected Label RoundL; 
	protected Label DeckCounter; 
	protected Label infoLabel;
	protected ImageView iVorrat[] = new ImageView[7];
	protected ImageView ImgKupfer, ImgPunkt1; //TODO Noch alle Karten so rein tun
	protected Button TestButton;
	
	public BoardView(){
		
	}
	
	public BoardView(Stage Vwindow, BoardModel model){
		
		this.window=Vwindow;
		this.model=model;
		
//		Stage window = new Stage();
		Vwindow.setTitle("DOMINION");
		Vwindow.setMinWidth(1250);
		Vwindow.setMinHeight(770);
		
		BorderPane MainBoard = new BorderPane();
		MainBoard.setId("MainBoard");
		
		GridPane grid = new GridPane();		//Für Karten zum Kaufen	(imageviews korrekt verteilt)
		//GRIDPANE SPACING NOCH MACHEN
		
		TestButton = new Button ("TEST"); //TEST
		
		
		VBox infoBox = new VBox();			//Für PunkeStand & Chat & RundenAnzahl (label,textare,textfield,label)
		pointsL = new Label("POINTS");//points
		ChatArea = new TextArea();	//ChatField
		
		ChatField = new TextField(); //Chat-Input
		RoundL= new Label("R");		//Rundenzahl
		VBox CenterVBox = new VBox();
		HBox HandBox =  new HBox();			//wachsenden Hand (imageview)
		HandBox.setSpacing(5.0);
		
		DeckCounter = new Label ("#"); // Anzahl Karten im Deck, pos.(left.BOTTOM)
		DeckCounter.setId("DeckCounter");
		
		DeckCounter.setId("DeckCounter");
		
		infoLabel = new Label ("INFO");	// ganz unten für Infos, wen Zug startet
		infoLabel.setId("infoLabel");
		
		CenterVBox.getChildren().addAll(grid,HandBox);
		MainBoard.setCenter(CenterVBox);
		MainBoard.setAlignment(CenterVBox, Pos.CENTER);
		
		
		ChatArea.setMaxWidth(300.0);
		ChatArea.setMaxHeight(800.0);
//		ChatArea.setPrefSize(600.0, 200.0);
//		ChatArea.setMaxSize(500.0, 300.0);
		infoBox.getChildren().addAll(pointsL,ChatArea,ChatField,RoundL);
//		infoBox.setAlignment(Pos.CENTER);
		RoundL.setAlignment(Pos.BOTTOM_RIGHT);
		MainBoard.setRight(infoBox);
		
		
		
		MainBoard.setLeft(DeckCounter);	//Alignment bottom machen und versuchen anschauen
		MainBoard.setAlignment(DeckCounter, Pos.BOTTOM_LEFT);
		
		MainBoard.setBottom(infoLabel);
		MainBoard.setAlignment(infoLabel, Pos.BOTTOM_CENTER);
		
		
		
		//Alle Bilder
		
		
		ImgKupfer = new ImageView("file:///Users/halilcenik/git/boardies/src/gui/Gold_mini.jpg");
		ImgKupfer.setId("image");
		ImgKupfer.setPickOnBounds(true);
		ImgKupfer.setFitHeight(180);
		ImgKupfer.setFitWidth(130);
        iVorrat[0]=ImgKupfer;
//		grid.add(img, 5,5);
        
        ImgPunkt1 = new ImageView("file:///Users/halilcenik/git/boardies/src/gui/Pictures/Punkte01.jpg");
        ImgPunkt1.setId("image");
        ImgPunkt1.setPickOnBounds(true);
        ImgPunkt1.setFitHeight(180);
        ImgPunkt1.setFitWidth(130);
        iVorrat[1]=ImgPunkt1;
        
//        grid.setDisable(true);
//        HandBox.setDisable(true);
//        img.setDisable(true);
        
//		HandBox.getChildren().addAll(img,img2);
        ImgKupfer.setOnMouseClicked(e -> System.out.println("hey"));
		
		Button ok = new Button("ok");
		
		
		grid.add(new Button("0-0"), 0, 0);
		grid.add(new Button("1-1"), 1, 1);
		grid.add(new Button("2-2"), 2, 2);
		grid.add(new Button("3-3"), 3, 3);
		grid.add(new Button("4-4"), 4, 4);
		grid.add(new Button("5-5"), 5, 5);
		grid.add(new Button("6-6"), 6, 6);
	
		
		
//		grid.getChildren().addAll(img);
		
		
		
		boardScene = new Scene(MainBoard);
//		boardScene.getStylesheets().add(getResource("Dominon.css").toExternalForm());
		Vwindow.setScene(boardScene);
		Vwindow.show();
		
	}

	public void start() {
		getWindow().show();
	}
	public void stop() {
		getWindow().hide();
	}

	public Stage getWindow() {
		return this.window;
	}
	
}
