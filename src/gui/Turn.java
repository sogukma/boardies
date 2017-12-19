package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Turn {

	Player p;
	MessageHandler mh;
	
	public Turn(Player p, MessageHandler mh) {
		this.p = p;
		this.mh = mh;
	}

	public void play() {

		// mh.send("Deine Punktzahl: "+p.getPoints());
//		try{Thread.sleep(1000);}catch(Exception e){}
		prepareTurn(p, mh);
		p.setAdditionalMoney(0);
		doAction(p, mh);
		doPurchase(p, mh);
		returnCardsToDeck(p, mh);
		
//		try{Thread.sleep(1000);}catch(Exception e){}
		mh.send("info.rundend");

	}

	private void shuffleCards(Player p) {
		long seed = System.nanoTime();
		List tempDeck = new ArrayList(p.getDeck());
		Collections.shuffle(tempDeck, new Random(seed));
		Queue<Card> queue = new LinkedList<>(tempDeck);
		p.setDeck(queue);

	}

	private void extendHand(Player p, MessageHandler mh, int additionalCards) {
		// 5 karten nachziehen in die Hand
		p.setHandSize(p.getHand().size() + additionalCards);

		while (p.getHand().size() < p.getHandSize()) {
			p.addHand(p.removeDeck());
		}
		mh.send("Deck: "+p.getDeck().size());
		boolean sent = false;
		while (!sent) {
			sent = mh.send(p.getHand().toString());
		}

	}

	private void prepareTurn(Player p, MessageHandler mh) {
		// 5 karten nachziehen in die Hand

		shuffleCards(p);
		p.setHandSize(5);

		
		while (p.getHand().size() < p.getHandSize()) {
			p.addHand(p.removeDeck());
		}
		mh.send("Deck: "+p.getDeck().size());
		boolean sent = false;
		while (!sent) {
			sent = mh.send(p.getHand().toString());
		}
		

	}

	private void returnCardsToDeck(Player p, MessageHandler mh) {

		int i = p.getHand().size()-1;
		while(!p.getHand().isEmpty())
		{
			p.addDeck(p.getHand().remove(i));
			i--;
		}
		mh.send("Deck: "+p.getDeck().size());
		
	}

	private void doPurchase(Player p, MessageHandler MH) {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if(p.getAmountOfPurchases() > 0 && getAmountOfPurchaseCardsInHand(p) > 0) 
		{
		//TODO amountofpurchases in this round muss �berpr�ft werden f�r ganze runde
		MH.send("info.purchasehand");

		int amountOfPurchasesInThisRound = p.getAmountOfPurchases();

		p.setAmountOfPurchases(1);

	
			int totalworth = 0;
			Stock stock = new Stock();
			int amountOfPurchaseCardsInThisRound = getAmountOfPurchaseCardsInHand(p);
			totalworth += p.getAdditionalMoney();
			MH.send("Budget: " + totalworth);
			
			while (amountOfPurchaseCardsInThisRound > 0) {
				int index2 = 0;

				// Karten anzeigen
				System.out.println(p.getName() + " deine Hand:");
				index2 = 0;
				for (int i = 0; i < p.getHand().size(); i++) {
					System.out.print(index2 + ". ");
					System.out.println(p.getHand().get(i));
					index2++;
				}

				MH.send(p.getName()+ ";" + "info.choosecard1");
				int auswahlZahlungsmittel = Integer.parseInt(MH.receive());
				
				if (!isMoneyCard(p.getHand().get(auswahlZahlungsmittel))) {
					MH.send("info.nomoneycard");
					continue;
				} else {
					MoneyCard mn = (MoneyCard) p.getHand().get(auswahlZahlungsmittel);
					totalworth += mn.getRealWorth();
					MH.send("info.budget2;:" + totalworth);

					amountOfPurchaseCardsInThisRound--;
				}

			}
			
			while (amountOfPurchasesInThisRound > 0 && totalworth > 0) {
				MH.send("info.purchasestock");
				int index2 = 0;
				System.out.println(p.getName() + " der Vorrat:");
				for (Card i : stock.getStock()) {
					System.out.print(index2 + ". ");
					System.out.println(i.getName() + i.getWorth());
					index2++;
				}

				MH.send("info.amountOfPurchases1;" + amountOfPurchasesInThisRound + ";info.amountOfPurchases2");

				MH.send(p.getName() + ";info.buycard2");
				int auswahlZumKaufen = Integer.parseInt(MH.receive());

				// ausgew�hlte karte in der hand ist gleich oder mehr wert als
				// die zu kaufende karte
				if (totalworth >= stock.getStock().get(auswahlZumKaufen).getWorth()) {

					/*
					 * Card copy =
					 * stock.getStock().get(auswahlZumKaufen).clone();
					 * copy.setPlayer(p);
					 * 
					 * p.addHand(copy); //hier wird von Dominion punktzahl
					 * erh�ht if(copy instanceof Dominion) { copy.doAction();
					 * }
					 * 
					 * amountOfPurchasesInThisRound--; totalworth -=
					 * copy.getWorth(); MH.send("Budget: "+totalworth);
					 * MH.send("Du hast die Karte " + copy.getName() +
					 * " gekauft.");
					 * 
					 */
					stock.getStock().get(auswahlZumKaufen).setPlayer(p);
					p.addHand(stock.getStock().get(auswahlZumKaufen));

					if (stock.getStock().get(auswahlZumKaufen) instanceof EstateCard) {
						stock.getStock().get(auswahlZumKaufen).doAction();
					}

					amountOfPurchasesInThisRound--;
					totalworth -= stock.getStock().get(auswahlZumKaufen).getWorth();
					MH.send("Budget: " + totalworth);
					MH.send("info.point1;: " + p.getPoints());
					MH.send("info.buycard3;" + stock.getStock().get(auswahlZumKaufen).getName()
							+ ";info.buycard4");

				} else {
					MH.send("info.nomoney");
					continue;

				}
			}
		}

	}

	private boolean isActionCard(Card card) {
		if (card instanceof ActionCard) {
			return true;

		} else {
			return false;
		}
	}

	private int getAmountOfActionCardsInHand(Player p) {
		int amount = 0;
		for (int i = 0; i < p.getHand().size(); i++) {
			if (isActionCard(p.getHand().get(i))) {
				amount++;

			}

		}
		return amount;

	}

	private int getAmountOfPurchaseCardsInHand(Player p) {
		int amount = 0;
		for (int i = 0; i < p.getHand().size(); i++) {
			// wenn genug geld, um stock karten zu kaufen
			if (isMoneyCard(p.getHand().get(i))) {
				amount++;
			}

		}
		return amount;

	}

	private boolean isMoneyCard(Card card) {
		if (card instanceof MoneyCard) {
			return true;

		} else {

			return false;

		}
	}

	private void doAction(Player p, MessageHandler MH) {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if(p.getAmountOfActions() > 0 && getAmountOfActionCardsInHand(p) > 0)
		{
		System.out.println("zum Test Name: " + p.getName());
		System.out.println("zum Test Ationen:" + p.getAmountOfActions());
		System.out.println("zum Test K�ufe:" + p.getAmountOfPurchases());
		System.out.println("zum Test Hand:" + p.getHandSize());

		MH.send("action");
//		 int amountOfActionsInThisRound = p.getAmountOfActions();

//		p.setAmountOfActions(1);

		int amountOfActionCardsInHandInThisRound = getAmountOfActionCardsInHand(p);
		// while has action -> karten checken
		while (amountOfActionCardsInHandInThisRound > 0 && p.getAmountOfActions() > 0) {
			// Karten anzeigen
			int index2 = 0;
			System.out.println(p.getName() + " w�hle eine Aktionskarte aus!");
			for (Card hand : p.getHand()) {
				System.out.print(index2 + ". ");
				System.out.println(hand.getName() + hand.getWorth());
				index2++;
			}
			// eine Karte ausw�hlen
			MH.send("info.action1;" + p.getAmountOfActions()
					+ ";info.action2");

			boolean booleanAuswahl = MH.send(p.getName() + ";info.action4");
			int auswahl = Integer.parseInt(MH.receive());
			
			if (isActionCard(p.getHand().get(auswahl))) {
				ActionCard ac = (ActionCard) p.getHand().get(auswahl);
				/*
				 * TODO hand erweitern ActionCard ac =
				 * p.getHand().get(auswahl).clone();
				 * if(p.getHand().get(auswahl). < 0) {
				 * 
				 * }
				 */
				// p.getHand().get(auswahl).setPlayer(p);

				// p.getHand().get(auswahl).doAction();
				ac.doAction(); // funktioniert
				p.addDeck(p.getHand().remove(auswahl));
				MH.send("Deck: "+p.getDeck().size());
//				MH.send("handextended");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				extendHand(p, MH, ac.getAmountAddCard()); // funktioniert
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				MH.send("info.action5");
				System.out.println("zum Test Name: " + p.getName());
				System.out.println("zum Test Ationen:" + p.getAmountOfActions());
				System.out.println("zum Test K�ufe:" + p.getAmountOfPurchases());
				System.out.println("zum Test Hand:" + p.getHandSize());
				System.out.println("zum Test Money:" + p.getAdditionalMoney());
				 p.setAmountOfActions(p.getAmountOfActions() - 1);
				 amountOfActionCardsInHandInThisRound--;
//				amountOfActionsInThisRound--;
			} else {
				MH.send("info.choosecard3");
			}
		}
		p.setAmountOfActions(1);
	}
	}
}
