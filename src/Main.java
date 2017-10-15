import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	private Player p1;
	private Player p2;

	Main(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public void run() throws IOException {
	
		prepareNewGame(p1, p2);

		int round = 0;
		while (round < 10) {
			System.out.println("//////////////////////////////////////");
			System.out.println("Round " + round);

			prepareTurn(p1);
			doAction(p1);
			doPurchase(p1);
			returnCards(p1);
			// TODO Deck/Nachziehstapel neu ordnen random

			prepareTurn(p2);
			doAction(p2);
			doPurchase(p2);
			returnCards(p2);
			// TODO Deck/Nachziehstapel neu ordnen random
			round++;
		}

	}

	private void prepareTurn(Player p) {
		// 5 karten nachziehen in die Hand
		while (p.getHand().size() < 5) {
			p.addHand(p.removeDeck());
		}
		
	}

	private void returnCards(Player p) {
		// TODO auch gekaufte Karten hier rein
		for (int i = 0; i < p.getHand().size(); i++) {
			p.addDeck(p.getHand().remove(i));
		}

	}

	private void doPurchase(Player p) {
		while (hasPurchaseCard(p) && p.getAmountOfPurchases() > 0) {
			System.out.println("Du hast noch "+p.getAmountOfPurchases()+" Kaufaktionen.");

			int index2 = 0;
			Stock stock = new Stock();
			
			
			// Karten anzeigen
			System.out.println("Deine Hand:");
			index2 = 0;
			for (int i = 0; i < p.getHand().size();i++) {
				System.out.print(index2 + ". ");
				System.out.println(p.getHand().get(i));
				index2++;
			}
				
			int auswahlZahlungsmittel = ScannerInterface.scan(p.getName()+" Wähle eine Kaufkarte, mit der du kaufen willst!");
			index2 = 0;
			System.out.println("Der Vorrat:");
			for (Card i: stock.getStock()) {
				System.out.print(index2 + ". ");
				System.out.println(i.getName() + i.getWorth());
				index2++;
			}
			System.out.println(p.getName()+" Kaufe eine Karte!");
	
			int auswahlZumKaufen = ScannerInterface.scan(p.getName()+" Kaufe eine Karte!");
			
			if(isMoneyCard(p, auswahlZahlungsmittel))
			{
				//ausgewählte karte in der hand ist gleich oder mehr wert als die zu kaufende karte
				if(stock.getStock().get(auswahlZumKaufen).getWorth() <= p.getHand().get(auswahlZahlungsmittel).getRealWorth())
				{
					Card copy = stock.getStock().get(auswahlZumKaufen).clone();
					copy.setPlayer(p);
					p.addHand(copy);
					p.setAmountOfPurchases(p.getAmountOfPurchases()-1);
					System.out.println("Du hast die Karte " + copy.getName()+ " gekauft.");
					
				}
				else
				{
					System.out.println("du hast kein Geld dazu");
					break;
					
				}
			}
			else
			{
				System.out.println("Das ist keine MoneyCard!");
			}
		}
		p.setAmountOfPurchases(1);
	}

	
	private boolean isMoneyCard(Player p, int i) {
		if(p.getHand().get(i) instanceof Money)
		{
			return true;
			
		}
		else
		{
		
		return false; 
		
		}
	}

	private void doAction(Player p) {
		
			//while has action -> karten checken
		while (hasActionCard(p) && p.getAmountOfActions() > 0) {
			// Karten anzeigen
			int index2 = 0;
			for (Card hand : p.getHand()) {
				System.out.print(index2 + ". ");
				System.out.println(hand.getName() + hand.getWorth());
				index2++;
			}
			// eine Karte auswählen
			System.out.println("Sie haben für diese Runde noch "+p.getAmountOfActions()+ " Aktionen zur Verfügung.");

			int auswahl = ScannerInterface.scan(p.getName()+ " Bitte wähle eine Aktionskarte aus!");
			
			if(isActionCard(p, auswahl))
			{
				/* TODO hand erweitern
				ActionCard ac = p.getHand().get(auswahl).clone();
				if(p.getHand().get(auswahl). < 0)
				{
					
				}
				*/
				p.getHand().get(auswahl).doAction();				
				p.setAmountOfActions(p.getAmountOfActions()-1);
			}
			else
			{
				System.out.println("Wähle eine andere Karte aus!");
				p.setAmountOfActions(p.getAmountOfActions());
			}
		}
		
		p.setAmountOfActions(1);

	}


	private boolean isActionCard(Player p, int i) {
		if(p.getHand().get(i) instanceof ActionCard)
		{
			return true;
			
		}
		else
		{
		return false; 
		}
	}

	private boolean hasActionCard(Player p) {
		for(int i = 0; i < p.getHand().size(); i++)
		{
			if(isActionCard(p, i))
			{
				return true;
				
			}
			
		}
		return false;
		
	}
	

	private boolean hasPurchaseCard(Player p) {
		for(int i = 0; i < p.getHand().size(); i++)
		{
			//wenn genug geld, um stock karten zu kaufen
			if(isMoneyCard(p, i))
			{
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

	public static Player createPlayer() {
		String name = "";
		name = ScannerInterface.scanString("Player Name");
		return new Player(name, 1, 1);
	}

	public static void main(String[] args) throws IOException {
		 Player p1 = createPlayer();
		 Player p2 = createPlayer();
		//TODO erstelle Server
		/*
		  ServerSocket server = new ServerSocket(8080);
		  
		  Socket client1 = server.accept();	
		  Player p1 = createPlayer();
		  ObjectInputStream input1 = new  ObjectInputStream(client1.getInputStream()); 
		  ObjectOutputStream output1 = new ObjectOutputStream(client1.getOutputStream());
		  PrintWriter writeTo1 = new PrintWriter(output1);
		  BufferedReader readFrom1 = new BufferedReader(new InputStreamReader(input1));
		 
		  
		  Socket client2 = server.accept();	
		  Player p2 = createPlayer();
		  ObjectInputStream input2 = new  ObjectInputStream(client2.getInputStream()); 
		  ObjectOutputStream output2 = new ObjectOutputStream(client2.getOutputStream());
		  PrintWriter writeTo2 = new PrintWriter(output2);
		  BufferedReader readFrom2 = new BufferedReader(new InputStreamReader(input2));
		 
		  //String s = readFrom2.readLine();
		  
		//TODO warte auf zwei clients, um diese in player umzuwandeln
		*/
		  //Starte Spiel
		new Main(p1, p2).run();
		
		//schliesse reader/writer
		/*writeTo1.close();
		readFrom1.close();		
		writeTo2.close();
		readFrom2.close();
	*/}

}
