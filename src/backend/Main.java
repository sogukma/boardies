package backend;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gui.MessageHandler;

/**
 * Die Klasse «Main» ist der Server des Spiels. Hier wird der gesamte Verlauf
 * des Spiels gesteuert.
 * 
 * @author Malik
 * 
 *
 */

public class Main implements Runnable {
	public static final int AMOUNT_OF_ROUNDS = 20;
	public static final int INITIAL_DECK_SIZE = 10;
	public static final int INITIAL_HAND_SIZE = 5;
	public static final int INITIAL_AMOUNT_OF_PURCHASES = 1;
	public static final int INITIAL_AMOUNT_OF_ACTIONS = 1;
	public static final String GAME_IP_ADRESS = "localhost";
	public static final int GAME_PORT = 8080;
	public Main() {

	}

	/**
	 * Hier werden die 20 Spielrunden abgearbeitet. Anschliessend folgt die
	 * Auswertung, von welcher beide Spieler informiert werden.
	 */
	public void startGame(Player p1, Player p2, MessageHandler m1MH, MessageHandler m2MH) {

		prepareNewGame(p1, p2);

		int round = 0;
		while (round < AMOUNT_OF_ROUNDS) {
			System.out.println("//////////////////////////////////////");
			System.out.println("main.round;" + round + 1);

			m1MH.send("main.ownpoints; " + p1.getPoints());
			m1MH.send("main.opponentpoints; " + p2.getPoints());
			m1MH.send(("main.round; " + (round + 1)));
			Turn.play(p1, m1MH);

			m2MH.send("main.ownpoints; " + p2.getPoints());
			m2MH.send("main.opponentpoints; " + p1.getPoints());
			m2MH.send(("main.round; " + (round + 1)));
			Turn.play(p2, m2MH);

			round++;
		}

		if (p1.getPoints() > p2.getPoints()) {
			m1MH.send("main.end.win");
			m2MH.send("main.end.lose");
		}
		if (p1.getPoints() < p2.getPoints()) {
			m1MH.send("main.end.lose");
			m2MH.send("main.end.win");
		} else {
			m1MH.send("main.end.draw");
			m2MH.send("main.end.draw");
		}

	}

	private void prepareNewGame(Player p1, Player p2) {

		fillInDeck(p1);
		fillInDeck(p2);

	}

	private void fillInDeck(Player p) {

		MoneyCard copper = new MoneyCard("Copper", 0, 1);
		copper.setPlayer(p);
		EstateCard estate = new EstateCard("Estate", 2, 1);
		estate.setPlayer(p);
		p.addDeck(copper);
		p.addDeck(copper);
		p.addDeck(copper);
		p.addDeck(copper);
		p.addDeck(copper);
		p.addDeck(copper);
		p.addDeck(copper);
		p.addDeck(estate);
		estate.doAction();
		p.addDeck(estate);
		estate.doAction();
		p.addDeck(estate);
		estate.doAction();

	}

	private Player createPlayer(MessageHandler mh) {
		String name = mh.receive();
		return new Player(name);
	}

	/*
	 * Hier werden zunächst zwei Client-Sockets angenommen, für die jeweils ein
	 * «Player»-Objekt erstellt wird.
	 */
	public void run() {

		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(GAME_PORT);
			System.out.println("Server gestartet!");

			ArrayList<MessageHandler> clients = new ArrayList<MessageHandler>();
			ArrayList<Player> players = new ArrayList<Player>();
			int i = 0;
			try {
				while (i < 2) {
					// message Handler individuell fï¿½r beide clients
					Socket socket = serverSocket.accept();
					MessageHandler mh = new MessageHandler(socket);

					clients.add(mh);

					Player p = createPlayer(mh);
					System.out.println(p.getName());
					players.add(p);

					i++;

				}
			} catch (IOException e) {
				e.printStackTrace();

			} finally {
				startGame(players.get(0), players.get(1), clients.get(0), clients.get(1));

			}

		} catch (Exception e) {
			
		}

	}

}
