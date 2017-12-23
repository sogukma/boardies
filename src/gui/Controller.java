package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import backend.BoardModel;
import chat.ChatClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Dieser Controller ist der Controller des StarmenÔøΩs (Dominion.fxml). Hier
 * werden die Anzeigeelemente intialisiert und mit ActionListenern versehen.
 * (Cenik Halil) Auch werden hier die Ressoucen fÔøΩr die Sprachauswahl gesetzt
 * und verwaltet. (Van Necati, Sogukoglu Malik) Die Anbindung und Verwaltung der
 * Audiodateien erfolgt ebenfalls hier. (Van Necati)
 * 
 * @author Cenik Halil
 * @author Malik
 * @author Van Necati
 * @version 1.0
 * @since 2017-12-21
 */
public class Controller implements Initializable {

	@FXML
	private Button Blogin, Banleitung, Beinstellungen, Bquit, Bback1, Bback2, Bback3, Bjoin, Bserver, BreadmeD,
			BreadmeE;

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

	private static final String RESOURCE_DE = "Language_de";
	private static final String RESOURCE_EN = "Language_en";

	static {

		RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_DE));
	}

	boolean Play_Audio = true;

	URL urlAudio = getClass().getResource("Medieval_Music.wav");
	File Clap = new File(urlAudio.getPath());

	URL url = getClass().getResource("Anleitung.pdf");
	File pdfD = new File(url.getPath());

	URL urlE = getClass().getResource("Manual.pdf");
	File pdfE = new File(urlE.getPath());

	private BoardModel BM;
	private BoardControllerFXML bcf;

	public Controller(BoardModel bm) {
		this.BM = bm;
		this.bcf = null;

	}

	/*
	 * F¸r die ‹bersetzung ist die ObservableResourceFactory-Klasse bedeutsam.
	 * An diesen wird zun‰chst die Sprachdatei als Resource ¸bergeben. Die
	 * Labels und Buttons bekommen ihre anzuzeigenden Textelemente, indem sie
	 * die Schl¸sselwerte ihrer Textelemente mit ´.bindª direkt an den
	 * ResourceFactory anbinden. Wenn nun die Resource des ResourceFactory
	 * ver‰ndert wird (z.B. auf Englisch gesetzt wird), aktualisiert sich damit
	 * ohne weiteres die Sprache der angezeigten Texte in der GUI. Damit kann im
	 * Startmen¸, solange das Spielfeld noch nicht gestartet ist, die Sprache
	 * jederzeit ver‰ndern.
	 * 
	 * 
	 * @author Van Necati
	 * 
	 * @author Malik
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		

		Blogin.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.go"));
		Banleitung.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.guide"));
		Beinstellungen.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.prop"));
		Bquit.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.quit"));
		Bjoin.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.go"));
		Bback1.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.back1"));
		BreadmeD.setText("ANLEITUNG (D)");
		BreadmeE.setText("MANUAL (E)");
		Bback2.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.back2"));
		SpracheText.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.lang"));
		Bback3.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.back3"));
		TName.setPromptText("Name");
		LMusik.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.music"));
		ToggleMYes.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.yes"));
		ToggleMNo.textProperty().bind(RESOURCE_FACTORY.getStringBinding("opening.no"));

		ToggleGroup group = new ToggleGroup();
		ToggleD.setToggleGroup(group);
		ToggleE.setToggleGroup(group);
		ToggleD.setSelected(true);

		ToggleGroup Mgroup = new ToggleGroup();
		ToggleMYes.setToggleGroup(Mgroup);
		ToggleMNo.setToggleGroup(Mgroup);
		ToggleMYes.setSelected(true);

		ToggleD.selectedProperty().addListener(((observable, oldValue, newValue) -> {
			RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_DE, Locale.GERMAN));

		}));

		ToggleE.selectedProperty().addListener(((observable, oldValue, newValue) -> {
			RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_EN, Locale.ENGLISH));

		}));
	}

	@FXML
	private void quit() {
		Platform.exit();
		Map<Thread, StackTraceElement[]> m = Thread.getAllStackTraces();
		try {
			for (Map.Entry<Thread, StackTraceElement[]> entry : m.entrySet()) {

				entry.getKey().sleep(50);
				entry.getKey().interrupt();

			}
		} catch (Exception ee) {
		}
	}

	@FXML
	private void Login() {
		StartBox.setVisible(false);
		LoginBox.setVisible(true);
	}

	@FXML
	private void Anleitung() {
		StartBox.setVisible(false);
		AnleitungBox.setVisible(true);
	}

	@FXML
	private void Settings() {
		StartBox.setVisible(false);
		SettingsBox.setVisible(true);
	}

	@FXML
	private void Back() {
		LoginBox.setVisible(false);
		SettingsBox.setVisible(false);
		AnleitungBox.setVisible(false);
		StartBox.setVisible(true);
	}

	/**
	 * 
	 * Eines der wichtigsten ActionHandlers um zum Spiel zu gelangen. Erstellt
	 * den Controller f√ºr das Board und √ºbergibt das BoardModel und die
	 * Resourcen-Link mit. Bei erfolgreicher Ausf√ºhrung wird Chat gestartet und
	 * zur Board√úbersicht √ºbergangen.
	 * 
	 * @author Halil Cenik
	 * 
	 */

	@FXML
	private void Join() {
		Thread a = new Thread(BM);
		a.start();
		try {
			a.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String PlayerName = TName.getText();

		if (PlayerName != null && !PlayerName.isEmpty()) {
			BM.sendName(PlayerName);
			PlaySound();
			String[] abc = {};
			ChatClient.main(abc);

			this.bcf = new BoardControllerFXML(this.BM, RESOURCE_FACTORY.getResources());
		} else {
			TName.setPromptText("Name!?!");
		}
	}

	@FXML
	private void AnleitungD() {

		try {
			Desktop.getDesktop().open(pdfD);
		} catch (IOException e) {
			System.out.println("File not Found");
		}
	}

	@FXML
	private void AnleitungE() {

		try {
			Desktop.getDesktop().open(pdfE);
		} catch (IOException e) {
			System.out.println("File not Found");
		}
	}

	@FXML
	private void ChangeDeutsch() {
		System.out.println("Deutsch gew\u00e4hlt");
	}

	@FXML
	private void ChangeEnglish() {
		System.out.println("English chosen");
	}

	@FXML
	private void MusicOn() {
		Play_Audio = true;
	}

	@FXML
	private void MusicOff() {
		Play_Audio = false;
	}

	/**
	 * Das Audio In-Game wird von hier aus gesteuert, Je nach Zustand des
	 * Parameters Play_Audio wird Audio im Hintergrund-Thread laufen.
	 * 
	 * @author Van Necati
	 */

	public void PlaySound() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				if (Play_Audio) {
					try {

						Clip clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(Clap));
						FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
						gainControl.setValue(-15.0f);
						clip.start();
						clip.loop(clip.LOOP_CONTINUOUSLY);

						Thread.sleep(clip.getMicrosecondLength() / 1000);

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Audio Failed");
					}

				} else {
				}
				;

			}
		}).start();
	}
}
