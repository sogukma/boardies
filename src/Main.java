import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Chat.Server.ClientHandler;

public class Main implements Runnable {

	private Player p1;
	private Player p2;
	//private Client client1;
	//private Client client2;
	private MessageHandler m1MH;
	private MessageHandler m2MH;

	Main(Player p1, Player p2, MessageHandler m1MH, MessageHandler m2MH) {
		this.p1 = p1;
		this.p2 = p2;
	//	this.client1 = client1;
	//	this.client2 = client2;
		this.m1MH = m1MH;
		this.m2MH = m2MH;
	}

	public void run() {

		prepareNewGame(p1, p2);

		int round = 0;
		while (round < 10) {
			System.out.println("//////////////////////////////////////");
			System.out.println("Round " + round + 1);

			m1MH.send("Deine Punktzahl: "+p1.getPoints());
			prepareTurn(p1, m1MH);
			doAction(p1, m1MH);
			doPurchase(p1, m1MH);
			returnCards(p1);
			// TODO Deck/Nachziehstapel neu ordnen random

			m2MH.send("Deine Punktzahl: "+p2.getPoints());
			prepareTurn(p2, m2MH);
			doAction(p2, m2MH);
			doPurchase(p2, m2MH);
			returnCards(p2);
			// TODO Deck/Nachziehstapel neu ordnen random
			round++;
		}

	}

	private void prepareTurn(Player p, MessageHandler mh) {
		// 5 karten nachziehen in die Hand
		while (p.getHand().size() < p.getHandSize()) {
			p.addHand(p.removeDeck());
		}
		mh.send(p.getHand().toString());
		

	}

	private void returnCards(Player p) {
		// TODO auch gekaufte Karten hier rein
		for (int i = 0; i < p.getHand().size(); i++) {
			p.addDeck(p.getHand().remove(i));
		}

	}

	private void doPurchase(Player p, MessageHandler MH) {
		while (hasPurchaseCard(p) && p.getAmountOfPurchases() > 0) {
			MH.send("Du hast noch " + p.getAmountOfPurchases() + " Kaufaktionen.");

			int index2 = 0;
			Stock stock = new Stock();

			// Karten anzeigen
			System.out.println("Deine Hand:");
			index2 = 0;
			for (int i = 0; i < p.getHand().size(); i++) {
				System.out.print(index2 + ". ");
				System.out.println(p.getHand().get(i));
				index2++;
			}

			boolean booleanZahlungsmittel = MH.send(p.getName() + " Wähle eine Kaufkarte, mit der du kaufen willst!");
			int auswahlZahlungsmittel = Integer.parseInt(MH.receive());
			//	int auswahlZahlungsmittel = c.scan((p.getName() + " Wähle eine Kaufkarte, mit der du kaufen willst!"));
		//	int auswahlZahlungsmittel = ScannerInterface
		//			.scan(p.getName() + " Wähle eine Kaufkarte, mit der du kaufen willst!");
			index2 = 0;
			System.out.println("Der Vorrat:");
			for (Card i : stock.getStock()) {
				System.out.print(index2 + ". ");
				System.out.println(i.getName() + i.getWorth());
				index2++;
			}
			
		//	int auswahlZumKaufen = c.scan((p.getName() + " Kaufe eine Karte!")); 
			boolean booleanAuswahlZumKaufen = MH.send(p.getName() + " Kaufe eine Karte!");
			int auswahlZumKaufen = Integer.parseInt(MH.receive());
	
			if (isMoneyCard(p, auswahlZahlungsmittel)) {
				// ausgewählte karte in der hand ist gleich oder mehr wert als
				// die zu kaufende karte
				if (stock.getStock().get(auswahlZumKaufen).getWorth() <= p.getHand().get(auswahlZahlungsmittel)
						.getRealWorth()) {
					Card copy = stock.getStock().get(auswahlZumKaufen).clone();
					copy.setPlayer(p);
					
					p.addHand(copy);
					//hier wird von Dominion punktzahl erhöht
					if(copy instanceof Dominion)
					{
						copy.doAction();
						
					}
					p.setAmountOfPurchases(p.getAmountOfPurchases() - 1);
					MH.send("Du hast die Karte " + copy.getName() + " gekauft.");
					

				} else {
					MH.send("du hast kein Geld dazu");
					break;

				}
			} else {
				MH.send("Das ist keine MoneyCard!");
			}
		}
		p.setAmountOfPurchases(1);
	}



	private boolean isMoneyCard(Player p, int i) {
		if (p.getHand().get(i) instanceof Money) {
			return true;

		} else {

			return false;

		}
	}

	private void doAction(Player p, MessageHandler MH) {

		// while has action -> karten checken
		while (hasActionCard(p) && p.getAmountOfActions() > 0) {
			// Karten anzeigen
			int index2 = 0;
			for (Card hand : p.getHand()) {
				System.out.print(index2 + ". ");
				System.out.println(hand.getName() + hand.getWorth());
				index2++;
			}
			// eine Karte auswählen
			MH.send("Sie haben für diese Runde noch " + p.getAmountOfActions() + " Aktionen zur Verfügung.");

		//	int auswahl = ScannerInterface.scan(p.getName() + " Bitte wähle eine Aktionskarte aus!");

		
			boolean booleanAuswahl = MH.send(p.getName() + " Bitte wähle eine Aktionskarte aus!");
			int auswahl = Integer.parseInt(MH.receive());
			
			if (isActionCard(p, auswahl)) {
				/*
				 * TODO hand erweitern
				 *  ActionCard ac = p.getHand().get(auswahl).clone();
				 * if(p.getHand().get(auswahl). < 0) {
				 * 
				 * }
				 */
				p.getHand().get(auswahl).doAction();
				p.setAmountOfActions(p.getAmountOfActions() - 1);
			} else {
				MH.send("Wähle eine andere Karte aus!");
				p.setAmountOfActions(p.getAmountOfActions());
			}
		}

		p.setAmountOfActions(1);

	}

	
	private boolean isActionCard(Player p, int i) {
		if (p.getHand().get(i) instanceof ActionCard) {
			return true;

		} else {
			return false;
		}
	}

	private boolean hasActionCard(Player p) {
		for (int i = 0; i < p.getHand().size(); i++) {
			if (isActionCard(p, i)) {
				return true;

			}

		}
		return false;

	}

	private boolean hasPurchaseCard(Player p) {
		for (int i = 0; i < p.getHand().size(); i++) {
			// wenn genug geld, um stock karten zu kaufen
			if (isMoneyCard(p, i)) {
				return true;

			}

		}
		return false;

	}

	private void prepareNewGame(Player p1, Player p2) {

		Stock stock = new Stock();
		fillInDeck(p1, stock.getStock());
		fillInDeck(p2, stock.getStock());

	}

	private void fillInDeck(Player p, ArrayList<Card> stock) {
		Random rand = new Random();

		// Nachziehstapel für spieler 1 wird gefüllt mit 10 karten

		for (int i = 0; i < 10; i++) {
			int randIndex = rand.nextInt(stock.size());
			stock.get(randIndex).setPlayer(p);
			p.addDeck(stock.get(randIndex));

		}

		// TODO nur bestimmte karten am anfang
		// p1.addDeck(mn);

		// 5 karten auf die hand
		p.addHand(p.removeDeck());
		p.addHand(p.removeDeck());
		p.addHand(p.removeDeck());
		p.addHand(p.removeDeck());
		p.addHand(p.removeDeck());
	}

	public static Player createPlayer(MessageHandler mh) {
		mh.send("Player Name eingeben");
		String name = mh.receive();
		return new Player(name, 1, 1);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("---------START");

		// TODO erstelle Server
//		ExecutorService executor = Executors.newFixedThreadPool(2);
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(8080);
			System.out.println("Server gestartet!");
			
			
			ArrayList<MessageHandler> clients = new ArrayList<MessageHandler>();
			ArrayList<Player> players = new ArrayList<Player>();
			int i = 0;
			try {
				while(i < 2)
				{
					//message Handler individuell für beide clients
					Socket socket = serverSocket.accept();
					MessageHandler mh = new MessageHandler(socket);
//					ServerInputHandler serverInput = new ServerInputHandler(mh); 
					clients.add(mh);
					
//					Thread t1 = new Thread(serverInput);
//					executor.execute(t1);
					
					Player p = createPlayer(mh);
					players.add(p);
					
//					 t1 . start(); 		
					
					  i++;

					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			finally{
				new Main(players.get(0), players.get(1), clients.get(0), clients.get(1)).run();
			}

		} catch (Exception e) {
			System.out.println(e);
		} 

		System.out.println("---------END");

	}

}
