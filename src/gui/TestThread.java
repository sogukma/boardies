package gui;

public class TestThread implements Runnable {

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
	
	@Override
	public void run() {
				
		mh.send("Deine Punktzahl: "+p.getPoints());
		prepareTurn(p, mh);
		doAction(p, mh);
		doPurchase(p, mh);
		returnCards(p);
	}
	
	private void prepareTurn(Player p1, MessageHandler mh) {
		// 5 karten nachziehen in die Hand
		while (p1.getHand().size() < p1.getHandSize()) {
			p1.addHand(p1.removeDeck());
		}
		mh.send(p1.getHand().toString());
		

	}

	private void returnCards(Player p1) {
		// TODO auch gekaufte Karten hier rein
		for (int i = 0; i < p1.getHand().size(); i++) {
			p1.addDeck(p1.getHand().remove(i));
		}

	}

	private void doPurchase(Player p1, MessageHandler MH) {
		
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
				p1.getHand().get(auswahl).setPlayer(p);
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

	
	
	
	
	
	

}
