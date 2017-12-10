package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class TestThread{

	Player p;
	MessageHandler mh;
	
	public TestThread(Player p, MessageHandler mh)
	{
		this.p = p;
		this.mh = mh;
	}
	
//	public TestThread(Player p, MessageHandler mh, Thread vorgaenger)
//	{
//		this.p = p;
//		this.mh = mh;
//	}
	

	public void play() {
				
		mh.send("Deine Punktzahl: "+p.getPoints());
		prepareTurn(p, mh);
		doAction(p, mh);
		doPurchase(p, mh);
		returnCards(p);
		returnCards(p);
		returnCards(p);
		shuffleCards(p);

		mh.send("roundEnd");
		
	}
	
	private void shuffleCards(Player p) {
		long seed = System.nanoTime();
		List tempDeck = new ArrayList(p.getDeck());
		Collections.shuffle(tempDeck, new Random(seed));
		Queue<Card> queue = new LinkedList<>(tempDeck);
		p.setDeck(queue);
	
	
}
	
	
	private void extendHand(Player p1, MessageHandler mh, int additionalCards) {
		// 5 karten nachziehen in die Hand
		p1.setHandSize( p1.getHandSize() + additionalCards);
		
		while (p1.getHand().size() < p1.getHandSize()) {
			p1.addHand(p1.removeDeck());
		}
		boolean sent = false;
		while(!sent)
		{
			sent = mh.send(p1.getHand().toString());
		}
		
	}

	private void prepareTurn(Player p1, MessageHandler mh) {
		// 5 karten nachziehen in die Hand
		if(p1.getHandSize() != 5)
		{
			p1.setHandSize(5);
		}
		while (p1.getHand().size() < p1.getHandSize()) {
			p1.addHand(p1.removeDeck());
		}
		boolean sent = false;
		while(!sent)
		{
			sent = mh.send(p1.getHand().toString());
		}
		
	}

	private void returnCards(Player p1) {
		// TODO auch gekaufte Karten hier rein
		for (int i = 0; i < p1.getHand().size(); i++) {
			p1.addDeck(p1.getHand().remove(i));
		}

	}

	private void doPurchase(Player p1, MessageHandler MH) {
		MH.send("purchaseHand");
		
		int amountOfPurchasesInThisRound = p1.getAmountOfPurchases();
		
		if(p1.getAmountOfPurchases() != 1)
		{
			p1.setAmountOfPurchases(1);
		}
		if(amountOfPurchasesInThisRound > 0)
		{
		int totalworth = 0;
		Stock stock = new Stock();
		int amountInThisRound = hasPurchaseCard(p1);
		totalworth += p.getAdditionalMoney();
		MH.send("Budget: "+totalworth);
		while (amountInThisRound > 0) {
				int index2 = 0;
		

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
			
			if (!isMoneyCard(p1, auswahlZahlungsmittel)) {
				MH.send("Das ist keine MoneyCard!");
				continue;
			}
			else
			{
			Money mn = (Money) p1.getHand().get(auswahlZahlungsmittel);
			totalworth += mn.getRealWorth();
			MH.send("Budget: "+totalworth);
			
			amountInThisRound--;
			}
			//	int auswahlZahlungsmittel = c.scan((p.getName() + " Wï¿½hle eine Kaufkarte, mit der du kaufen willst!"));
		//	int auswahlZahlungsmittel = ScannerInterface
		//			.scan(p.getName() + " Wï¿½hle eine Kaufkarte, mit der du kaufen willst!");

			
		//	int auswahlZumKaufen = c.scan((p.getName() + " Kaufe eine Karte!")); 
				

		}
	
		
		
		while(amountOfPurchasesInThisRound > 0 && totalworth > 0)
		{
			MH.send("purchaseStock");
			int index2 = 0;
			System.out.println(p1.getName()+" der Vorrat:");
			for (Card i : stock.getStock()) {
				System.out.print(index2 + ". ");
				System.out.println(i.getName() + i.getWorth());
				index2++;
			}
			
			MH.send("Du hast noch " + amountOfPurchasesInThisRound + " Kaufaktionen.");

			boolean booleanAuswahlZumKaufen = MH.send(p1.getName() + " Kaufe eine Karte!");
			int auswahlZumKaufen = Integer.parseInt(MH.receive());
	
	
		
				// ausgewï¿½hlte karte in der hand ist gleich oder mehr wert als
				// die zu kaufende karte
				if (totalworth >= stock.getStock().get(auswahlZumKaufen).getWorth() ) {
					
					/*
					Card copy = stock.getStock().get(auswahlZumKaufen).clone();
					copy.setPlayer(p1);
					
					p1.addHand(copy);
					//hier wird von Dominion punktzahl erhï¿½ht
					if(copy instanceof Dominion)
					{
						copy.doAction();
					}
					
						//				p.setAmountOfPurchases(p.getAmountOfPurchases() - 1);
					amountOfPurchasesInThisRound--;
					totalworth -= copy.getWorth();
					MH.send("Budget: "+totalworth);
					MH.send("Du hast die Karte " + copy.getName() + " gekauft.");
					
					*/
					stock.getStock().get(auswahlZumKaufen).setPlayer(p1);
					System.out.println(stock.getStock().get(auswahlZumKaufen).getWorth());
					p1.addHand(stock.getStock().get(auswahlZumKaufen));
					
					if(stock.getStock().get(auswahlZumKaufen) instanceof Dominion)
					{
						stock.getStock().get(auswahlZumKaufen).doAction();
					}
					
					
	//				p.setAmountOfPurchases(p.getAmountOfPurchases() - 1);
					amountOfPurchasesInThisRound--;
					totalworth -= stock.getStock().get(auswahlZumKaufen).getWorth();
					MH.send("Budget: "+totalworth);
					MH.send("Du hast die Karte " + stock.getStock().get(auswahlZumKaufen).getName() + " gekauft.");
					
	
				} else {
					MH.send("du hast kein Geld dazu");
					continue;
	
				}
			}
		}
		
//		p.setAmountOfPurchases(1);
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

	private int hasPurchaseCard(Player p) {
		int amount = 0;
		for (int i = 0; i < p.getHand().size(); i++) {
			// wenn genug geld, um stock karten zu kaufen
			if (isMoneyCard(p, i)) {
				amount++;
			}

		}
		return amount;

	}


	private boolean isMoneyCard(Player p, int i) {
		if (p.getHand().get(i) instanceof Money) {
			return true;

		} else {

			return false;

		}
	}

	private void doAction(Player p1, MessageHandler MH) {
		System.out.println("zum Test Name: " +p1.getName());
		System.out.println("zum Test Ationen:" +p1.getAmountOfActions());
		System.out.println("zum Test Käufe:" +p1.getAmountOfPurchases());
		System.out.println("zum Test Hand:" +p1.getHandSize());

		int amountOfActionsInThisRound = p1.getAmountOfActions();
		if(p1.getAmountOfActions() != 1)
		{
			p1.setAmountOfActions(1);
		}
		p1.setAdditionalMoney(0);
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
				ActionCard ac = (ActionCard) p1.getHand().get(auswahl);
				/*
				 * TODO hand erweitern
				 *  ActionCard ac = p.getHand().get(auswahl).clone();
				 * if(p.getHand().get(auswahl). < 0) {
				 * 
				 * }
				 */
//				p1.getHand().get(auswahl).setPlayer(p);
				
//				p1.getHand().get(auswahl).doAction();
				ac.doAction(); //funktioniert
				extendHand(p1, MH, ac.getAmountAddCard()); //funktioniert
				System.out.println("zum Test Name: " +p1.getName());
				System.out.println("zum Test Ationen:" +p1.getAmountOfActions());
				System.out.println("zum Test Käufe:" +p1.getAmountOfPurchases());
				System.out.println("zum Test Hand:" +p1.getHandSize());
				System.out.println("zum Test Money:" +p1.getAdditionalMoney());
				
				//				p.setAmountOfActions(p.getAmountOfActions() - 1);
				amountOfActionsInThisRound--;
			} else {
				MH.send("Wï¿½hle eine andere Karte aus!");
//				p.setAmountOfActions(p.getAmountOfActions());
			}
		}

//		p.setAmountOfActions(1);

	}

	
	
	
	
	
	

}
