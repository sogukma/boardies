package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import gui.MessageHandler;

/**
 * Der detaillierte Runden-Prozess wird hier bearbeitet.
 * 
 * @author Malik
 */
public class Turn {

	Player p;
	MessageHandler mh;

	public static void play(Player p, MessageHandler mh) {

		prepareTurn(p, mh);
		p.setAdditionalMoney(0);
		doAction(p, mh);
		doPurchase(p, mh);
		returnCardsToDeck(p, mh);
		mh.send("rndfertig");

	}

	private static void shuffleCards(Player p) {
		long seed = System.nanoTime();
		List tempDeck = new ArrayList(p.getDeck());
		Collections.shuffle(tempDeck, new Random(seed));
		Queue<Card> queue = new LinkedList<>(tempDeck);
		p.setDeck(queue);

	}

	private static void extendHand(Player p, MessageHandler mh, int additionalCards) {
		// 5 karten nachziehen in die Hand
		p.setHandSize(p.getHand().size() + additionalCards);

		while (p.getHand().size() < p.getHandSize()) {
			p.addHand(p.removeDeck());
		}
		mh.send("Deck: " + p.getDeck().size());
		boolean sent = false;
		while (!sent) {
			sent = mh.send(p.getHand().toString());
		}

	}

	/**
	 * Eine Runde wird zun�chst mit der Funktion �prepareTurn� vorbereitet.
	 * Dabei wird zun�chst des Spielers Deck mit der Hilfsfunktion
	 * �shuffleCards� gemischt. Danach werden 5 Karten aus diesem Deck in die
	 * Hand gezogen.
	 * 
	 * 
	 */
	private static void prepareTurn(Player p, MessageHandler mh) {
		// 5 karten nachziehen in die Hand

		shuffleCards(p);
		p.setHandSize(Main.INITIAL_HAND_SIZE);

		while (p.getHand().size() < p.getHandSize()) {
			p.addHand(p.removeDeck());
		}
		mh.send("Deck: " + p.getDeck().size());
		boolean sent = false;
		while (!sent) {
			sent = mh.send(p.getHand().toString());
		}

	}

	/**
	 * Anschliessend werden alle Karten in der Hand, einschliesslich der neu
	 * gekauften Karten, in den Deck �berf�hrt.
	 * 
	 */
	private static void returnCardsToDeck(Player p, MessageHandler mh) {

		int i = p.getHand().size() - 1;
		while (!p.getHand().isEmpty()) {
			p.addDeck(p.getHand().remove(i));
			i--;
		}
		mh.send("Deck: " + p.getDeck().size());

	}

	/**
	 * Verf�gt der Spieler �ber das Recht einen Kauf durchzuf�hren und hat er
	 * eine oder mehrere Kaufkarten, w�hlt er alle Kaufkarten in seiner Hand aus
	 * und kauft so viele Karten, als sein Geld ausreicht und das Recht auf
	 * K�ufe besteht. Kauft der Spieler eine Punktekarte, erh�ht sich seine
	 * Punktzahl.
	 * 
	 */
	private static void doPurchase(Player p, MessageHandler MH) {
		System.out.println("Kaufphase ist gestartet");
		if (p.getAmountOfPurchases() > 0 && getAmountOfPurchaseCardsInHand(p) > 0) {

			MH.send("purchaseHand");

			int amountOfPurchasesInThisRound = p.getAmountOfPurchases();
			p.setAmountOfPurchases(Main.INITIAL_AMOUNT_OF_PURCHASES);
			int amountOfPurchaseCardsInThisRound = getAmountOfPurchaseCardsInHand(p);
			int totalworth = 0;

			Stock stock = new Stock();

			totalworth += p.getAdditionalMoney();
			MH.send("budget;" + totalworth);

			while (amountOfPurchaseCardsInThisRound > 0) {
				int index2 = 0;
				int auswahlZahlungsmittel = -1;
				// Karten anzeigen log
				System.out.println(p.getName() + " deine Hand:");
				for (int i = 0; i < p.getHand().size(); i++) {
					System.out.print(index2 + ". ");
					System.out.println(p.getHand().get(i));
					index2++;
				}

				MH.send(p.getName() + ";" + "request.choosemoneycard");
				auswahlZahlungsmittel = Integer.parseInt(MH.receive());

				if (!isMoneyCard(p.getHand().get(auswahlZahlungsmittel))) {
					continue;
				} else {
					MoneyCard mn = (MoneyCard) p.getHand().get(auswahlZahlungsmittel);
					totalworth += mn.getRealWorth();
					MH.send("budget;" + totalworth);

					amountOfPurchaseCardsInThisRound--;
				}

			}

			while (amountOfPurchasesInThisRound > 0 && totalworth > 0) {
				MH.send("purchaseStock");
				int auswahlZumKaufen = -1;
				int index2 = 0;
				System.out.println(p.getName() + " der Vorrat:");
				for (Card i : stock.getStock()) {
					System.out.print(index2 + ". ");
					System.out.println(i.getName() + i.getWorth());
					index2++;
				}

				MH.send("report.amountofpurchases1;" + amountOfPurchasesInThisRound + ";report.amountofpurchases2");

				MH.send(p.getName() + ";request.buycard");
				auswahlZumKaufen = Integer.parseInt(MH.receive());

				// ausgew�hlte karte in der hand ist gleich oder mehr wert als
				// die zu kaufende karte
				if (totalworth >= stock.getStock().get(auswahlZumKaufen).getWorth()) {

					stock.getStock().get(auswahlZumKaufen).setPlayer(p);
					p.addHand(stock.getStock().get(auswahlZumKaufen));

					if (stock.getStock().get(auswahlZumKaufen) instanceof EstateCard) {
						stock.getStock().get(auswahlZumKaufen).doAction();
					}

					amountOfPurchasesInThisRound--;
					totalworth -= stock.getStock().get(auswahlZumKaufen).getWorth();
					MH.send("budget;" + totalworth);
					MH.send("main.ownpoints; " + p.getPoints());

				} else {
					continue;

				}
			}
		}

	}

	private static boolean isActionCard(Card card) {
		if (card instanceof ActionCard) {
			return true;

		} else {
			return false;
		}
	}

	private static int getAmountOfActionCardsInHand(Player p) {
		int amount = 0;
		for (int i = 0; i < p.getHand().size(); i++) {
			if (isActionCard(p.getHand().get(i))) {
				amount++;

			}

		}
		return amount;

	}

	private static int getAmountOfPurchaseCardsInHand(Player p) {
		int amount = 0;
		for (int i = 0; i < p.getHand().size(); i++) {
			// wenn genug geld, um stock karten zu kaufen
			if (isMoneyCard(p.getHand().get(i))) {
				amount++;
			}

		}
		return amount;

	}

	private static boolean isMoneyCard(Card card) {
		if (card instanceof MoneyCard) {
			return true;

		} else {

			return false;

		}
	}

	/**
	 * Verf�gt der Spieler �ber das Recht eine Aktion durchzuf�hren und hat er
	 * eine oder mehrere Aktionskarten, kann er eine Aktionskarte aus seiner
	 * Hand ausw�hlen, dessen Aktion sich sofort auf das Spiel auswirkt. Dieser
	 * Vorgang wiederholt sich solange, bis der Spieler kein Recht mehr auf eine
	 * Aktion hat oder alle seine Aktionskarten aufgebracht sind. Diese
	 * �doAction�-Methode ist nicht zu verwechseln mit denen in den
	 * Kartenklassen.
	 * 
	 * 
	 * 
	 */
	private static void doAction(Player p, MessageHandler MH) {

		if (p.getAmountOfActions() > 0 && getAmountOfActionCardsInHand(p) > 0) {
			System.out.println("zum Test Name: " + p.getName());
			System.out.println("zum Test Ationen:" + p.getAmountOfActions());
			System.out.println("zum Test K�ufe:" + p.getAmountOfPurchases());
			System.out.println("zum Test Hand:" + p.getHandSize());

			MH.send("action");
			int amountOfActionCardsInHandInThisRound = getAmountOfActionCardsInHand(p);
			// while has action -> karten checken
			while (getAmountOfActionCardsInHand(p) > 0 && p.getAmountOfActions() > 0) {
				// Karten anzeigen
				int index2 = 0;
				System.out.println(p.getName() + " w�hle eine Aktionskarte aus!");
				for (Card hand : p.getHand()) {
					System.out.print(index2 + ". ");
					System.out.println(hand.getName() + hand.getWorth());
					index2++;
				}
				// eine Karte ausw�hlen
				MH.send("report.action1;" + p.getAmountOfActions() + ";report.action2");

				MH.send(p.getName() + ";request.action");
				int auswahl = Integer.parseInt(MH.receive());

				if (isActionCard(p.getHand().get(auswahl))) {
					ActionCard ac = (ActionCard) p.getHand().get(auswahl);

					ac.doAction(); // funktioniert
					p.addDeck(p.getHand().remove(auswahl));
					MH.send("Deck: " + p.getDeck().size());
					extendHand(p, MH, ac.getAmountAddCard()); // funktioniert

					MH.send("action");
					System.out.println("zum Test Name: " + p.getName());
					System.out.println("zum Test Ationen:" + p.getAmountOfActions());
					System.out.println("zum Test K�ufe:" + p.getAmountOfPurchases());
					System.out.println("zum Test Hand:" + p.getHandSize());
					System.out.println("zum Test Money:" + p.getAdditionalMoney());
					p.setAmountOfActions(p.getAmountOfActions() - 1);
					// amountOfActionCardsInHandInThisRound--;
				} else {
				}
			}
			p.setAmountOfActions(Main.INITIAL_AMOUNT_OF_ACTIONS);
		}
		System.out.println("aktionsphase ist beendet");
	}
}
