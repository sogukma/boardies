package gui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main implements Runnable {

	private Player p1;
	private Player p2;
	//private Client client1;
	//private Client client2;
	private MessageHandler m1MH;
	private MessageHandler m2MH;
	
	Main() {
		this.p1 = null;
		this.p2 = null;
	//	this.client1 = client1;
	//	this.client2 = client2;
		this.m1MH = null;
		this.m2MH = null;
	}

	public void run2(Player p1, Player p2, MessageHandler m1MH, MessageHandler m2MH) {
		new Thread() {
		    @Override
		    public void run() {
		
		
		prepareNewGame(p1, p2);

		int round = 0;
		  TestThread t1= new TestThread(p1, m1MH);
		  TestThread t2= new TestThread(p2, m2MH);
		while (round < 10) {
			System.out.println("//////////////////////////////////////");
			System.out.println("Round " + round + 1);

			m1MH.send("YourPoints: "+p1.getPoints());
			m1MH.send("OpponentPoints: "+ p2.getPoints());
			m1MH.send(("Round: " + (round + 1)));
			t1.play();

			m2MH.send("YourPoints: "+p2.getPoints());
			m2MH.send("OpponentPoints: "+ p1.getPoints());
			m2MH.send(("Round: " + (round + 1)));
			t2.play();
		
			
//	Thread t1 = new TestThread(new Runnable() {
//			        @Override
//			        public void run() {
//
//			        }
//			    });
//
//			   TestThread t2= new TestThread(p2, m2MH, t1);
//			   t1.start();
//			    t2.start();
			
				
				        
				  
			
			
		
			
			/*
			m1MH.send("Deine Punktzahl: "+p1.getPoints());
			prepareTurnPlayerOne(p1, m1MH);
			doActionPlayerOne(p1, m1MH);
			doPurchasePlayerOne(p1, m1MH);
			returnCardsPlayerOne(p1);
			// TODO Deck/Nachziehstapel neu ordnen random

			m2MH.send("Deine Punktzahl: "+p2.getPoints());
			prepareTurnPlayerTwo(p2, m2MH);
			doActionPlayerTwo(p2, m2MH);
			doPurchasePlayerTwo(p2, m2MH);
			returnCardsPlayerTwo(p2);
			// TODO Deck/Nachziehstapel neu ordnen random
			
			*/
			
			round++;
		}
		
		
			if(p1.getPoints() > p2.getPoints())
				{
					m1MH.send("End: du hast gewinnen!");
					m2MH.send("End: du hast verloren!");
				}
			else
				{
					m1MH.send("End: du hast verloren!");
					m2MH.send("End: du hast gewonnen!");
				}
				
		    }
				}.start();
	}

	private void prepareTurnPlayerOne(Player p1, MessageHandler mh) {
		// 5 karten nachziehen in die Hand
		while (p1.getHand().size() < p1.getHandSize()) {
			p1.addHand(p1.removeDeck());
		}
		mh.send(p1.getHand().toString());
		

	}

	private void returnCardsPlayerOne(Player p1) {
		// TODO auch gekaufte Karten hier rein
		for (int i = 0; i < p1.getHand().size(); i++) {
			p1.addDeck(p1.getHand().remove(i));
		}

	}

	private void doPurchasePlayerOne(Player p1, MessageHandler MH) {
		
		int amountOfPurchasesInThisRound = p1.getAmountOfPurchases();
		while (hasPurchaseCard(p1) && amountOfPurchasesInThisRound > 0) {
			MH.send("Du hast noch " + p1.getAmountOfPurchases() + " Kaufaktionen.");

			int index2 = 0;
			Stock stock = new Stock();

			// Karten anzeigen
			System.out.println(p1.getName()+" deine Hand:");
			index2 = 0;
			for (int i = 0; i < p1.getHand().size(); i++) {
				System.out.print(index2 + ". ");
				System.out.println(p1.getHand().get(i));
				index2++;
			}

			boolean booleanZahlungsmittel = MH.send(p1.getName() + " Wï¿½hle eine Kaufkarte, mit der du kaufen willst!");
			int auswahlZahlungsmittel = Integer.parseInt(MH.receive());
			//	int auswahlZahlungsmittel = c.scan((p.getName() + " Wï¿½hle eine Kaufkarte, mit der du kaufen willst!"));
		//	int auswahlZahlungsmittel = ScannerInterface
		//			.scan(p.getName() + " Wï¿½hle eine Kaufkarte, mit der du kaufen willst!");
			index2 = 0;
			System.out.println(p1.getName()+" der Vorrat:");
			for (Card i : stock.getStock()) {
				System.out.print(index2 + ". ");
				System.out.println(i.getName() + i.getWorth());
				index2++;
			}
			
		//	int auswahlZumKaufen = c.scan((p.getName() + " Kaufe eine Karte!")); 
			boolean booleanAuswahlZumKaufen = MH.send(p1.getName() + " Kaufe eine Karte!");
			int auswahlZumKaufen = Integer.parseInt(MH.receive());
	
			if (isMoneyCard(p1, auswahlZahlungsmittel)) {
				// ausgewï¿½hlte karte in der hand ist gleich oder mehr wert als
				// die zu kaufende karte
				if (stock.getStock().get(auswahlZumKaufen).getWorth() <= p1.getHand().get(auswahlZahlungsmittel)
						.getRealWorth()) {
					Card copy = stock.getStock().get(auswahlZumKaufen).clone();
					copy.setPlayer(p1);
					
					p1.addHand(copy);
					//hier wird von Dominion punktzahl erhï¿½ht
					if(copy instanceof Dominion)
					{
						copy.doAction();
						
					}
//					p.setAmountOfPurchases(p.getAmountOfPurchases() - 1);
					amountOfPurchasesInThisRound--;
					MH.send("Du hast die Karte " + copy.getName() + " gekauft.");
					

				} else {
					MH.send("du hast kein Geld dazu");
					break;

				}
			} else {
				MH.send("Das ist keine MoneyCard!");
			}
		}
//		p.setAmountOfPurchases(1);
	}



	private boolean isMoneyCard(Player p, int i) {
		if (p.getHand().get(i) instanceof Money) {
			return true;

		} else {

			return false;

		}
	}

	private void doActionPlayerOne(Player p1, MessageHandler MH) {
		System.out.println("zum Test Name: " +p1.getName());
		System.out.println("zum Test Ationen:" +p1.getAmountOfActions());
		System.out.println("zum Test Käufe:" +p1.getAmountOfPurchases());
		System.out.println("zum Test Hand:" +p1.getHandSize());

		int amountOfActionsInThisRound = p1.getAmountOfActions();
		// while has action -> karten checken
		while (hasActionCard(p1) && amountOfActionsInThisRound > 0) {
			// Karten anzeigen
			int index2 = 0;
			System.out.println(p1.getName() +" wähle eine Aktionskarte aus!");
			for (Card hand : p1.getHand()) {
				System.out.print(index2 + ". ");
				System.out.println(hand.getName() + hand.getWorth());
				index2++;
			}
			// eine Karte auswï¿½hlen
			MH.send("Sie haben fï¿½r diese Runde noch " + amountOfActionsInThisRound + " Aktionen zur Verfï¿½gung.");

		//	int auswahl = ScannerInterface.scan(p.getName() + " Bitte wï¿½hle eine Aktionskarte aus!");

		
			boolean booleanAuswahl = MH.send(p1.getName() + " Bitte wï¿½hle eine Aktionskarte aus!");
			int auswahl = Integer.parseInt(MH.receive());
			
			
			if (isActionCard(p1, auswahl)) {
				/*
				 * TODO hand erweitern
				 *  ActionCard ac = p.getHand().get(auswahl).clone();
				 * if(p.getHand().get(auswahl). < 0) {
				 * 
				 * }
				 */
				p1.getHand().get(auswahl).doAction();
				System.out.println("zum Test Name: " +p1.getName());
				System.out.println("zum Test Ationen:" +p1.getAmountOfActions());
				System.out.println("zum Test Käufe:" +p1.getAmountOfPurchases());
				System.out.println("zum Test Hand:" +p1.getHandSize());
				
				
				//				p.setAmountOfActions(p.getAmountOfActions() - 1);
				amountOfActionsInThisRound--;
			} else {
				MH.send("Wï¿½hle eine andere Karte aus!");
//				p.setAmountOfActions(p.getAmountOfActions());
			}
		}

//		p.setAmountOfActions(1);

	}

	
	
	
	
	
	
	private void prepareTurnPlayerTwo(Player p, MessageHandler mh) {
		// 5 karten nachziehen in die Hand
		while (p.getHand().size() < p.getHandSize()) {
			p.addHand(p.removeDeck());
		}
		mh.send(p.getHand().toString());
		

	}

	private void returnCardsPlayerTwo(Player p) {
		// TODO auch gekaufte Karten hier rein
		for (int i = 0; i < p.getHand().size(); i++) {
			p.addDeck(p.getHand().remove(i));
		}

	}

	private void doPurchasePlayerTwo(Player p, MessageHandler MH) {
		
		int amountOfPurchasesInThisRound = p.getAmountOfPurchases();
		while (hasPurchaseCard(p) && amountOfPurchasesInThisRound > 0) {
			MH.send("Du hast noch " + p.getAmountOfPurchases() + " Kaufaktionen.");

			int index2 = 0;
			Stock stock = new Stock();

			// Karten anzeigen
			System.out.println(p.getName()+" deine Hand:");
			index2 = 0;
			for (int i = 0; i < p.getHand().size(); i++) {
				System.out.print(index2 + ". ");
				System.out.println(p.getHand().get(i));
				index2++;
			}

			boolean booleanZahlungsmittel = MH.send(p.getName() + " Wï¿½hle eine Kaufkarte, mit der du kaufen willst!");
			int auswahlZahlungsmittel = Integer.parseInt(MH.receive());
			//	int auswahlZahlungsmittel = c.scan((p.getName() + " Wï¿½hle eine Kaufkarte, mit der du kaufen willst!"));
		//	int auswahlZahlungsmittel = ScannerInterface
		//			.scan(p.getName() + " Wï¿½hle eine Kaufkarte, mit der du kaufen willst!");
			index2 = 0;
			System.out.println(p.getName()+" der Vorrat:");
			for (Card i : stock.getStock()) {
				System.out.print(index2 + ". ");
				System.out.println(i.getName() + i.getWorth());
				index2++;
			}
			
		//	int auswahlZumKaufen = c.scan((p.getName() + " Kaufe eine Karte!")); 
			boolean booleanAuswahlZumKaufen = MH.send(p.getName() + " Kaufe eine Karte!");
			int auswahlZumKaufen = Integer.parseInt(MH.receive());
	
			if (isMoneyCard(p, auswahlZahlungsmittel)) {
				// ausgewï¿½hlte karte in der hand ist gleich oder mehr wert als
				// die zu kaufende karte
				if (stock.getStock().get(auswahlZumKaufen).getWorth() <= p.getHand().get(auswahlZahlungsmittel)
						.getRealWorth()) {
					Card copy = stock.getStock().get(auswahlZumKaufen).clone();
					copy.setPlayer(p);
					
					p.addHand(copy);
					//hier wird von Dominion punktzahl erhï¿½ht
					if(copy instanceof Dominion)
					{
						copy.doAction();
						
					}
//					p.setAmountOfPurchases(p.getAmountOfPurchases() - 1);
					amountOfPurchasesInThisRound--;
					MH.send("Du hast die Karte " + copy.getName() + " gekauft.");
					

				} else {
					MH.send("du hast kein Geld dazu");
					break;

				}
			} else {
				MH.send("Das ist keine MoneyCard!");
			}
		}
//		p.setAmountOfPurchases(1);
	}



	

	private void doActionPlayerTwo(Player p, MessageHandler MH) {
		System.out.println("zum Test Name: " +p.getName());
		System.out.println("zum Test Ationen:" +p.getAmountOfActions());
		System.out.println("zum Test Käufe:" +p.getAmountOfPurchases());
		System.out.println("zum Test Hand:" +p.getHandSize());

		int amountOfActionsInThisRound = p.getAmountOfActions();
		// while has action -> karten checken
		while (hasActionCard(p) && amountOfActionsInThisRound > 0) {
			// Karten anzeigen
			int index2 = 0;
			System.out.println(p.getName() +" wähle eine Aktionskarte aus!");
			for (Card hand : p.getHand()) {
				System.out.print(index2 + ". ");
				System.out.println(hand.getName() + hand.getWorth());
				index2++;
			}
			// eine Karte auswï¿½hlen
			MH.send("Sie haben fï¿½r diese Runde noch " + amountOfActionsInThisRound + " Aktionen zur Verfï¿½gung.");

		//	int auswahl = ScannerInterface.scan(p.getName() + " Bitte wï¿½hle eine Aktionskarte aus!");

		
			boolean booleanAuswahl = MH.send(p.getName() + " Bitte wï¿½hle eine Aktionskarte aus!");
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
				System.out.println("zum Test Name: " +p.getName());
				System.out.println("zum Test Ationen:" +p.getAmountOfActions());
				System.out.println("zum Test Käufe:" +p.getAmountOfPurchases());
				System.out.println("zum Test Hand:" +p.getHandSize());
				
				
				//				p.setAmountOfActions(p.getAmountOfActions() - 1);
				amountOfActionsInThisRound--;
			} else {
				MH.send("Wï¿½hle eine andere Karte aus!");
//				p.setAmountOfActions(p.getAmountOfActions());
			}
		}

//		p.setAmountOfActions(1);

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

		
		fillInDeck(p1);
		fillInDeck(p2);

		fillInHand(p1);
		fillInHand(p2);
		
	}

	private void fillInHand(Player p) {
		Money copper = new Money("Copper", 0, 1);
		copper.setPlayer(p);
		Dominion estate = new Dominion("Estate", 2, 1);
		estate.setPlayer(p);
		p.addHand(copper);
		p.addHand(copper);
		p.addHand(copper);
		p.addHand(estate);
		estate.doAction();
		p.addHand(estate);
		estate.doAction();
		
	}

	private void fillInDeck(Player p) {
		ArrayList<Card> stock = new Stock().getStock();
		Random rand = new Random();

		// Nachziehstapel fï¿½r spieler 1 wird gefï¿½llt mit 10 karten

		for (int i = 0; i < 10; i++) {
			int randIndex = rand.nextInt(stock.size());
			stock.get(randIndex).setPlayer(p);
			p.addDeck(stock.get(randIndex));

		}

		// TODO nur bestimmte karten am anfang
		// p1.addDeck(mn);

		// 5 karten auf die hand
//		p.addHand(p.removeDeck());
//		p.addHand(p.removeDeck());
//		p.addHand(p.removeDeck());
//		p.addHand(p.removeDeck());
//		p.addHand(p.removeDeck());
	}

	public static Player createPlayer(MessageHandler mh) {
		mh.send("Player Name eingeben");
		String name = mh.receive();
		return new Player(name, 1, 1);
	}

	public void run(){
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
					//message Handler individuell fï¿½r beide clients
					Socket socket = serverSocket.accept();
					MessageHandler mh = new MessageHandler(socket);
//					ServerInputHandler serverInput = new ServerInputHandler(mh); 
					clients.add(mh);
					
//					Thread t1 = new Thread(serverInput);
//					executor.execute(t1);
					
					Player p = createPlayer(mh);
					System.out.println(p.getName());
					players.add(p);
					
//					 t1 . start(); 		
					
					  i++;

					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			finally{
				run2(players.get(0), players.get(1), clients.get(0), clients.get(1));
			
			}

		} catch (Exception e) {
			System.out.println(e);
		} 

		System.out.println("---------END");

                	}             
            	

	    	}
	    
	
	
