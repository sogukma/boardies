package backend;

import java.util.ArrayList;
import java.util.Scanner;

import gui.MessageHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

/**
 * Die Klasse «BoardModel» ist der Model der Client-Seite. Darin wird der
 * Client-Socket erstellt, was die Netzwerkverbindung gewährleistet. Weiter
 * werden hier eingehende Nachrichten bezüglich Spielinformationen und
 * anzuzeigende Handkarten entgegengenommen und für die weitere Bearbeitung in
 * der View, zu StringProperty-Observables hinzugefügt. Für Spielinformationen
 * wird der Observable «newMessage» und für Handkarten wird «newCards»
 * aktualisieret.
 * 
 * @author Malik
 *
 */
public class BoardModel implements Runnable {

	private MessageHandler mh;

	public SimpleStringProperty newMessage = new SimpleStringProperty();
	public SimpleStringProperty newCards = new SimpleStringProperty();

	public BoardModel() {
		this.mh = null;
	}

	public MessageHandler getMh() {
		return mh;
	}

	public void setMh(MessageHandler mh) {
		this.mh = mh;
	}

	public void sendName(String PlayerName) {
		mh.send(PlayerName.toString());
	}



	@Override
	public void run() {
		mh = new MessageHandler("localhost", 8080);

		while (true) {
			String response = mh.receive();
			System.out.println(response);

			String responseLowerCase = response.toLowerCase();

			// mit worth wird Handkarten-String identifiziert
			if (responseLowerCase.contains("worth")) {
				newCards.set("");
				newCards.set(response);

			} else {
				newMessage.set(response);
				newMessage.set("");
			}

		}

	}
}
