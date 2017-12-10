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
		prepareTurn(p, mh);
		doAction(p, mh);
		doPurchase(p, mh);
		returnCardsToDeck(p);
		returnCardsToDeck(p);
		returnCardsToDeck(p);
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

	private void extendHand(Player p, MessageHandler mh, int additionalCards) {
		// 5 karten nachziehen in die Hand
		p.setHandSize(p.getHandSize() + additionalCards);

		while (p.getHand().size() < p.getHandSize()) {
			p.addHand(p.removeDeck());
		}
		boolean sent = false;
		while (!sent) {
			sent = mh.send(p.getHand().toString());
		}

	}

	private void prepareTurn(Player p, MessageHandler mh) {
		// 5 karten nachziehen in die Hand

		p.setHandSize(5);

		while (p.getHand().size() < p.getHandSize()) {
			p.addHand(p.removeDeck());
		}
		boolean sent = false;
		while (!sent) {
			sent = mh.send(p.getHand().toString());
		}

	}

	private void returnCardsToDeck(Player p) {
		// TODO auch gekaufte Karten hier rein
		for (int i = 0; i < p.getHand().size(); i++) {
			p.addDeck(p.getHand().remove(i));
		}

	}

	private void doPurchase(Player p, MessageHandler MH) {
		MH.send("purchaseHand");

		int amountOfPurchasesInThisRound = p.getAmountOfPurchases();

		p.setAmountOfPurchases(1);

		if (amountOfPurchasesInThisRound > 0) {
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

				MH.send("Info: " + p.getName() + " Wï¿½hle eine Kaufkarte, mit der du kaufen willst!");
				int auswahlZahlungsmittel = Integer.parseInt(MH.receive());
				
				if (!isMoneyCard(p.getHand().get(auswahlZahlungsmittel))) {
					MH.send("Info: Das ist keine MoneyCard!");
					continue;
				} else {
					MoneyCard mn = (MoneyCard) p.getHand().get(auswahlZahlungsmittel);
					totalworth += mn.getRealWorth();
					MH.send("Budget: " + totalworth);

					amountOfPurchaseCardsInThisRound--;
				}

			}

			while (amountOfPurchasesInThisRound > 0 && totalworth > 0) {
				MH.send("purchaseStock");
				int index2 = 0;
				System.out.println(p.getName() + " der Vorrat:");
				for (Card i : stock.getStock()) {
					System.out.print(index2 + ". ");
					System.out.println(i.getName() + i.getWorth());
					index2++;
				}

				MH.send("Info: Du hast noch " + amountOfPurchasesInThisRound + " Kaufaktionen.");

				MH.send("Info: " + p.getName() + " Kaufe eine Karte!");
				int auswahlZumKaufen = Integer.parseInt(MH.receive());

				// ausgewï¿½hlte karte in der hand ist gleich oder mehr wert als
				// die zu kaufende karte
				if (totalworth >= stock.getStock().get(auswahlZumKaufen).getWorth()) {

					/*
					 * Card copy =
					 * stock.getStock().get(auswahlZumKaufen).clone();
					 * copy.setPlayer(p);
					 * 
					 * p.addHand(copy); //hier wird von Dominion punktzahl
					 * erhï¿½ht if(copy instanceof Dominion) { copy.doAction();
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
					MH.send("Points: " + p.getPoints());
					MH.send("Info: Du hast die Karte " + stock.getStock().get(auswahlZumKaufen).getName()
							+ " gekauft.");

				} else {
					MH.send("Info: Du hast kein Geld dazu");
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

	private boolean hasActionCardInHand(Player p) {
		for (int i = 0; i < p.getHand().size(); i++) {
			if (isActionCard(p.getHand().get(i))) {
				return true;

			}

		}
		return false;

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
		System.out.println("zum Test Name: " + p.getName());
		System.out.println("zum Test Ationen:" + p.getAmountOfActions());
		System.out.println("zum Test Käufe:" + p.getAmountOfPurchases());
		System.out.println("zum Test Hand:" + p.getHandSize());

		int amountOfActionsInThisRound = p.getAmountOfActions();

		p.setAmountOfActions(1);

		p.setAdditionalMoney(0);
		// while has action -> karten checken
		while (hasActionCardInHand(p) && amountOfActionsInThisRound > 0) {
			// Karten anzeigen
			int index2 = 0;
			System.out.println(p.getName() + " wähle eine Aktionskarte aus!");
			for (Card hand : p.getHand()) {
				System.out.print(index2 + ". ");
				System.out.println(hand.getName() + hand.getWorth());
				index2++;
			}
			// eine Karte auswï¿½hlen
			MH.send("Info: Sie haben fï¿½r diese Runde noch " + amountOfActionsInThisRound
					+ " Aktionen zur Verfï¿½gung.");

			boolean booleanAuswahl = MH.send("Info: " + p.getName() + " Bitte wï¿½hle eine Aktionskarte aus!");
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
				extendHand(p, MH, ac.getAmountAddCard()); // funktioniert
				System.out.println("zum Test Name: " + p.getName());
				System.out.println("zum Test Ationen:" + p.getAmountOfActions());
				System.out.println("zum Test Käufe:" + p.getAmountOfPurchases());
				System.out.println("zum Test Hand:" + p.getHandSize());
				System.out.println("zum Test Money:" + p.getAdditionalMoney());
				// p.setAmountOfActions(p.getAmountOfActions() - 1);
				amountOfActionsInThisRound--;
			} else {
				MH.send("Info: Wï¿½hle eine andere Karte aus!");
			}
		}

	}

}
