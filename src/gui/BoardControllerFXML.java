/**
* Der BoardControllerFXMl ist der Controller des Spielfeldes (MainBoard.fxml).
* Hier werden die Anzeigeelemente intialisiert und mit ActionListenern versehen. (Cenik Halil)
* Auch werden hier ein und ausgehende Nachrichten vom Server abgearbeitet. (Sogukoglu Malik)
*
* @author  Cenik Halil, Sogukoglu Malik
* @version 1.0
* @since   2017-12-21 
*/

package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import backend.BoardModel;
import backend.Main;
import backend.Stock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BoardControllerFXML implements Initializable {

	@FXML
	private ImageView KImgAnwesen, KImgKupfer, KImgHolz, KImgDorf, KImgSchmied, KImgLabor, KImgMarkt, KImgSilber,
			KImgGold, KImgProvinz, KImgHerzog;

	@FXML
	private Label LPointsP1, LPointsP2, RundenCounter, DeckZahl, LRequest, LKonto, LReport;

	@FXML
	private HBox HandBox;

	@FXML
	private ScrollPane SPane;

	@FXML
	private AnchorPane APane, MainPane;

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
	// HIGHLIGHTED
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
	// BLACK-WHITE
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

	// VorratBilder
	Image imgGoldVV = new Image("/GoldVV.jpg");
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
	// HIGHLIGHTED
	Image imgGoldHighVV = new Image("/Gold_highVV.jpg");
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
	// BLACK-WHITE
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

	BoardControllerFXML(BoardModel bm, ResourceBundle resourceBundle) {
		this.BM = bm;

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/MainBoard.fxml")); 
			fxmlLoader.setResources(resourceBundle);

			fxmlLoader.setController(this);
			Parent root1 = fxmlLoader.load();

			Scene BoardScene = new Scene(root1);
			stage.setOnCloseRequest(e -> {
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

		} catch (Exception e) {
			e.printStackTrace();
		}

		BM.newMessage.addListener((o, oldValue, newValue) -> this.updateMessages(newValue));
		BM.newCards.addListener((o, oldValue, newValue) -> this.updateHand(newValue));
	}

	private void updateLabel(String message, String keyValue, Label l) {
		l.setText("");
		for (String i : message.split(";")) {
			if (i.contains(keyValue)) {
				l.setText(l.getText() + " " + resources.getString(i));
			} else {
				l.setText(l.getText() + " " + i);
			}
		}
	}

	private void updateMessages(String message) {

		Platform.runLater(() -> {

			if (message.toLowerCase().contains("request")) {
				updateLabel(message, "request", LRequest);

			}

			if (message.toLowerCase().contains("report")) {
				updateLabel(message, "report", LReport);
			}

			if (message.toLowerCase().contains("deck")) {
				DeckZahl.setText(message);
			}

			if (message.toLowerCase().contains("budget")) {
				updateLabel(message, "budget", LKonto);
			}

			if (message.toLowerCase().contains("ownpoints")) {
				updateLabel(message, "ownpoints", LPointsP1);
			}

			if (message.toLowerCase().contains("opponentpoints")) {
				updateLabel(message, "opponentpoints", LPointsP2);
			}

			if (message.toLowerCase().contains("round")) {
				updateLabel(message, "round", RundenCounter);
				RundenCounter.setText(RundenCounter.getText() + "/" + Main.AMOUNT_OF_ROUNDS);

			}
			if (message.toLowerCase().contains("action")) {
				System.out.println("Actionbearbeitung GUI gestartet");
				HandBox.setDisable(false);
				DisableGrid();
				prepareHandForAction();
				System.out.println("Actionbearbeitung GUI zuende");
			}

			if (message.toLowerCase().contains("purchasehand")) {
				System.out.println("Kaufkartenbearbeitung GUI gestartet");
				DisableGrid();
				LReport.setText("");
				HandBox.setDisable(false);
				prepareHandForPurchase();
				System.out.println("Kaufkartenbearbeitung GUI zuende");

			}

			if (message.toLowerCase().contains("purchasestock")) {

				EnableGrid();
				HandBox.setDisable(true);
			}

			if (message.toLowerCase().contains("end")) {
				LRequest.setText(resources.getString(message));
				Hclear();
				HandBox.setDisable(true);
				DisableGrid();
			}

			if (message.toLowerCase().contains("rndfertig")) {
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
		RundenCounter.setText("1/" + Main.AMOUNT_OF_ROUNDS); 
		DeckZahl.setText("Deck: " + Main.INITIAL_DECK_SIZE);
		LKonto.setText("Budget");

		/**
		 *
		 *Alle Nachkommenden MouseEvents sind für die Markierungen von den Elementen gedacht, über die "gehovert" wird.
		 *@Param: MouseEvents
		 *
		 */
		
		// HIGHLITINGS
		// Silber 
		KImgSilber.setOnMouseEntered(e -> {
			KImgSilber.setFitHeight(165);
			KImgSilber.setFitWidth(130);
			KImgSilber.setImage(imgSilberHighVV);
		});
		KImgSilber.setOnMouseExited(e -> {
			KImgSilber.setFitHeight(145);
			KImgSilber.setFitWidth(110);
			KImgSilber.setImage(imgSilberVV);
		});
		// Gold
		KImgGold.setOnMouseEntered(e -> {
			KImgGold.setFitHeight(165);
			KImgGold.setFitWidth(130);
			KImgGold.setImage(imgGoldHighVV);
		});
		KImgGold.setOnMouseExited(e -> {
			KImgGold.setFitHeight(145);
			KImgGold.setFitWidth(110);
			KImgGold.setImage(imgGoldVV);
		});
		// Herzog
		KImgHerzog.setOnMouseEntered(e -> {
			KImgHerzog.setFitHeight(165);
			KImgHerzog.setFitWidth(130);
			KImgHerzog.setImage(imgHerzogHighVV);
		});
		KImgHerzog.setOnMouseExited(e -> {
			KImgHerzog.setFitHeight(145);
			KImgHerzog.setFitWidth(110);
			KImgHerzog.setImage(imgHerzogVV);
		});
		// Provinz
		KImgProvinz.setOnMouseEntered(e -> {
			KImgProvinz.setFitHeight(165);
			KImgProvinz.setFitWidth(130);
			KImgProvinz.setImage(imgProvinzHighVV);
		});
		KImgProvinz.setOnMouseExited(e -> {
			KImgProvinz.setFitHeight(145);
			KImgProvinz.setFitWidth(110);
			KImgProvinz.setImage(imgProvinzVV);
		});
		// Anwesen
		KImgAnwesen.setOnMouseEntered(e -> {
			KImgAnwesen.setFitHeight(165);
			KImgAnwesen.setFitWidth(130);
			KImgAnwesen.setImage(imgAnwesenHighVV);
		});
		KImgAnwesen.setOnMouseExited(e -> {
			KImgAnwesen.setFitHeight(145);
			KImgAnwesen.setFitWidth(110);
			KImgAnwesen.setImage(imgAnwesenVV);
		});
		// Kupfer
		KImgKupfer.setOnMouseEntered(e -> {
			KImgKupfer.setFitHeight(165);
			KImgKupfer.setFitWidth(130);
			KImgKupfer.setImage(imgKupferHighVV);
		});
		KImgKupfer.setOnMouseExited(e -> {
			KImgKupfer.setFitHeight(145);
			KImgKupfer.setFitWidth(110);
			KImgKupfer.setImage(imgKupferVV);
		});
		// Holz
		KImgHolz.setOnMouseEntered(e -> {
			KImgHolz.setFitHeight(190);
			KImgHolz.setFitWidth(140);
			KImgHolz.setImage(imgHolzHighVV);
		});
		KImgHolz.setOnMouseExited(e -> {
			KImgHolz.setFitHeight(170);
			KImgHolz.setFitWidth(120);
			KImgHolz.setImage(imgHolzVV);
		});
		// Dorf KImgDorf
		KImgDorf.setOnMouseEntered(e -> {
			KImgDorf.setFitHeight(190);
			KImgDorf.setFitWidth(140);
			KImgDorf.setImage(imgDorfHighVV);
		});
		KImgDorf.setOnMouseExited(e -> {
			KImgDorf.setFitHeight(170);
			KImgDorf.setFitWidth(120);
			KImgDorf.setImage(imgDorfVV);
		});
		// Schmied
		KImgSchmied.setOnMouseEntered(e -> {
			KImgSchmied.setFitHeight(190);
			KImgSchmied.setFitWidth(140);
			KImgSchmied.setImage(imgSchmiedHighVV);
		});
		KImgSchmied.setOnMouseExited(e -> {
			KImgSchmied.setFitHeight(170);
			KImgSchmied.setFitWidth(120);
			KImgSchmied.setImage(imgSchmiedVV);
		});
		// Labor
		KImgLabor.setOnMouseEntered(e -> {
			KImgLabor.setFitHeight(190);
			KImgLabor.setFitWidth(140);
			KImgLabor.setImage(imgLabHighVV);
		});
		KImgLabor.setOnMouseExited(e -> {
			KImgLabor.setFitHeight(170);
			KImgLabor.setFitWidth(120);
			KImgLabor.setImage(imgLabVV);
		});
		// Markt
		KImgMarkt.setOnMouseEntered(e -> {
			KImgMarkt.setFitHeight(190);
			KImgMarkt.setFitWidth(140);
			KImgMarkt.setImage(imgMarktHighVV);
		});
		KImgMarkt.setOnMouseExited(e -> {
			KImgMarkt.setFitHeight(170);
			KImgMarkt.setFitWidth(120);
			KImgMarkt.setImage(imgMarktVV);
		});

		KImgKupfer.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.COPPER_ID + "");
		});
		KImgSilber.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.SILVER_ID + "");
		});
		KImgGold.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.GOLD_ID + "");
		});
		KImgAnwesen.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.ESTATE_ID + "");
		});
		KImgHerzog.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.DUCHY_ID + "");
		});
		KImgProvinz.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.PROVINCE_ID + "");
		});
		KImgLabor.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.LABORATORY_ID + "");
		});

		KImgMarkt.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.MARKET_ID + "");
		});

		KImgDorf.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.VALLEY_ID + "");
		});

		KImgSchmied.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.SMITH_ID + "");
		});

		KImgHolz.setOnMouseClicked(e -> {
			BM.getMh().send(Stock.LUMBERJACK_ID + "");
		});

	} // end Initialize

		private Image BlackWhite(Image img){
		if(img==imgGold){return imgGoldSW;}
		if(img==imgSilber){return imgSilberSW;}
		if(img==imgKupfer){return imgKupferSW;}
		if(img==imgDorf){return imgDorfSW;}
		if(img==imgHolz){return imgHolzSW;}
		if(img==imgLab){return imgLabSW;}
		if(img==imgMarkt){return imgMarktSW;}
		if(img==imgSchmied){return imgSchmiedSW;}
		return null;
	}
	
		/**
		 * Für die Erstellung der LandKarten für das Hand, wird diese Methode verwendet
		 * @param id
		 * @param img
		 * @Author Halil Cenik
		 */
		
	private void fillInactiveCard(String id, Image img) {
		ImageView imv = new ImageView(img);
		imv.setId(id);
		imv.setPickOnBounds(true);
		imv.setFitHeight(140);
		imv.setFitWidth(90);
		HandBox.getChildren().add(imv);
	}
	
	
	/**
	 * Für die Erstellung aller anderen Karte im Hand, die eine Funktion besitzen, wird diese Methode verwendet. Anders bei der 
	 * obigen Methode, schickt die Karte ihre ID mit, falls sie geklickt wird.
	 * @param id
	 * @param img
	 * @Author Halil Cenik
	 */
	
	private void fillActiveCard(String id, Image img){
		ImageView imv = new ImageView(img);
		imv.setId(id + "");
		imv.setPickOnBounds(true);
		imv.setFitHeight(140);
		imv.setFitWidth(90);
		imv.setOnMouseClicked(e -> {
			BM.getMh().send(imv.getId());
			imv.setDisable(true);
			imv.setImage(BlackWhite(img));
		});
		HandBox.getChildren().add(imv);
	}

	private void fillHerzog(int id) {
		
		fillInactiveCard(id+"",imgHerzog);
	}

	private void fillProvinz(int id) {
		
		fillInactiveCard(id+"",imgProvinz);
	}

	private void fillAnwesen(int id){

		fillInactiveCard(id+"",imgAnwesen);
	}
	
	
	private void fillKupfer(int id) {
		
		fillActiveCard(id+"",imgKupfer);
		
	}

	private void fillSilber(int id) {
		
		fillActiveCard(id+"",imgSilber);
		
	}

	private void fillGold(int id) {
		
		fillActiveCard(id+"",imgGold);
		
	}

	private void fillDorf(int id) {
		
		fillActiveCard(id+"",imgDorf);
		
	}

	private void fillHolz(int id) {

		fillActiveCard(id+"",imgHolz);
		
	}

	private void fillLab(int id) {

		fillActiveCard(id+"",imgLab);

	}

	private void fillMarkt(int id) {

		fillActiveCard(id+"",imgMarkt);
		
	}
	
	private void fillSchmied(int id){
		
		fillActiveCard(id+"",imgSchmied);
		
	}

	private void updateHand(String cards) {

		Platform.runLater(() -> {

			int id = 0;
			ArrayList<String> splittedMessage = new ArrayList<String>();
			Hclear();
			for (String iterable_element : cards.split(",")) {
				splittedMessage.add(iterable_element);

				if (iterable_element.toLowerCase().contains("estate")) {
					fillAnwesen(id);
				}
				if (iterable_element.toLowerCase().contains("dutchy")) {
					fillHerzog(id);
				}
				if (iterable_element.toLowerCase().contains("province")) {
					fillProvinz(id);
				}
				if (iterable_element.toLowerCase().contains("copper")) {
					fillKupfer(id);
				}
				if (iterable_element.toLowerCase().contains("silver")) {
					fillSilber(id);
				}
				if (iterable_element.toLowerCase().contains("gold")) {
					fillGold(id);
				}
				if (iterable_element.toLowerCase().contains("laboratory")) {
					fillLab(id);
				}
				if (iterable_element.toLowerCase().contains("market")) {
					fillMarkt(id);
				}
				if (iterable_element.toLowerCase().contains("valley")) {
					fillDorf(id);
				}

				if (iterable_element.toLowerCase().contains("smith")) {
					fillSchmied(id);
				}
				if (iterable_element.toLowerCase().contains("lumberjack")) {
					fillHolz(id);
				}

				id++;
			}

			System.out.println("ende");
		});

	}
	
	/**
	 * 
	 * Diese Methode wird verwendet wenn der Spieler in die Kaufphase tritt, Sie sorgt dafür das nur Münzkarten angeklickt werden können
	 * @Author Halil Cenik
	 * 
	 */

	private void prepareHandForPurchase() {
		for (Node child : HandBox.getChildren()) {
			ImageView imgViewTest = (ImageView) child;
			if (imgViewTest.getImage().equals(imgDorf)) {
				imgViewTest.setDisable(true);
				imgViewTest.setImage(imgDorfSW);
			}
			if (imgViewTest.getImage().equals(imgMarkt)) {
				imgViewTest.setDisable(true);
				imgViewTest.setImage(imgMarktSW);
			}
			if (imgViewTest.getImage().equals(imgSchmied)) {
				imgViewTest.setDisable(true);
				imgViewTest.setImage(imgSchmiedSW);
			}
			if (imgViewTest.getImage().equals(imgHolz)) {
				imgViewTest.setDisable(true);
				imgViewTest.setImage(imgHolzSW);
			}
			if (imgViewTest.getImage().equals(imgLab)) {
				imgViewTest.setDisable(true);
				imgViewTest.setImage(imgLabSW);
			}
			if (imgViewTest.getImage().equals(imgKupfer) || imgViewTest.getImage().equals(imgKupferSW)) {
				imgViewTest.setDisable(false);
				imgViewTest.setImage(imgKupfer);
			}
			if (imgViewTest.getImage().equals(imgSilber) || imgViewTest.getImage().equals(imgSilberSW)) {
				imgViewTest.setDisable(false);
				imgViewTest.setImage(imgSilber);
			}
			if (imgViewTest.getImage().equals(imgGold) || imgViewTest.getImage().equals(imgGoldSW)) {
				imgViewTest.setDisable(false);
				imgViewTest.setImage(imgGold);
			}
		}

	}

	/**
	 * 
	 * Diese Methode wird verwendet wenn der Spieler in die Aktionsphase tritt, Sie sorgt dafür das nur Aktionskarten angeklickt werden können
	 * @Author Halil Cenik
	 * 
	 */
	
	private void prepareHandForAction() {
		for (Node child : HandBox.getChildren()) {
			ImageView imgViewTest = (ImageView) child;
			if (imgViewTest.getImage().equals(imgDorf)) {
				imgViewTest.setDisable(false);
				imgViewTest.setImage(imgDorf);
			}
			if (imgViewTest.getImage().equals(imgMarkt)) {
				imgViewTest.setDisable(false);
				imgViewTest.setImage(imgMarkt);
			}
			if (imgViewTest.getImage().equals(imgSchmied)) {
				imgViewTest.setDisable(false);
				imgViewTest.setImage(imgSchmied);
			}
			if (imgViewTest.getImage().equals(imgHolz)) {
				imgViewTest.setDisable(false);
				imgViewTest.setImage(imgHolz);
			}
			if (imgViewTest.getImage().equals(imgLab)) {
				imgViewTest.setDisable(false);
				imgViewTest.setImage(imgLab);
			}
			if (imgViewTest.getImage().equals(imgKupfer) && !imgViewTest.isDisable()) {
				imgViewTest.setDisable(true);
				imgViewTest.setImage(imgKupferSW);
			}
			if (imgViewTest.getImage().equals(imgSilber) && !imgViewTest.isDisable()) {
				imgViewTest.setDisable(true);
				imgViewTest.setImage(imgSilberSW);
			}
			if (imgViewTest.getImage().equals(imgGold) && !imgViewTest.isDisable()) {
				imgViewTest.setDisable(true);
				imgViewTest.setImage(imgGoldSW);
			}
		}

	}

	private void DisableGrid() {
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

	private void EnableGrid() {
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

	private void Hclear() {
		HandBox.getChildren().clear();
	}

	/**
	 * 
	 * Falls versucht wird ein Fenster zu schliessen, wird das CloseRequest abgefangen und durch diese Methode bearbeitet.
	 * Bei einem erfolgreichem Abschluss, werden alle Threads mitgeschlossen. 
	 * @Author: Halil Cenik 
	 * 
	 */
	
	public void endProgram() {
		Stage Exitwindow = new Stage();

		Exitwindow.initModality(Modality.APPLICATION_MODAL);
		Exitwindow.setTitle("");
		Exitwindow.setMinWidth(250);
		Exitwindow.setMinHeight(300);
		Label label = new Label(resources.getString("game.close")); // CLOSE
																	// GAME?
		Button yesButton = new Button(resources.getString("game.yes")); // YES
		Button noButton = new Button(resources.getString("game.no")); // NO

		yesButton.setOnAction(e -> {
			Exitwindow.close();
			Platform.exit();
			Map<Thread, StackTraceElement[]> m = Thread.getAllStackTraces();
			try {
				for (Map.Entry<Thread, StackTraceElement[]> entry : m.entrySet()) {

					entry.getKey().sleep(500);
					entry.getKey().interrupt();

				}
			} catch (Exception ee) {
			}

			// System.exit(1);
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