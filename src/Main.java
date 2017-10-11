import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		// neuer Spieler über zusatzfunktion holen
		Player p1 = new Player("saf", 1, 1);

		Player p2 = new Player("gfa", 1, 1);
					
		ActionCard ac = new ActionCard("Action1", 1, 1, 1, 1);
		Dominion dn = new Dominion("house", 1, 2);
		Dominion dn2 = new Dominion("castle", 3, 1);
		Dominion dn3 = new Dominion("country", 2, 3);
		Money mn = new Money("Gold", 1,2);
		
		ArrayList<Card> stock = new ArrayList<Card>();
		stock.add(ac);
		stock.add(dn);
		stock.add(dn2);
		stock.add(dn3);
		stock.add(mn);
		
		Random rand = new Random();
		
		//Nachziehstapel für spieler 1 wird gefüllt mit 10 karten
		/*
		for(int i = 0; i < 10; i++)
		{
			int randIndex = rand.nextInt(stock.size());
			
			p1.addDeck(stock.get(randIndex));
			
		}
		*/
		p1.addDeck(mn);
		//p1.
		
		
		//5 karten auf die hand
		p1.addHand(p1.removeDeck());
		p1.addHand(p1.removeDeck());
		p1.addHand(p1.removeDeck());
		p1.addHand(p1.removeDeck());
		p1.addHand(p1.removeDeck());
		
		//Nachziehstapel für spieler 2 wird gefüllt mit 10 karten
		for(int i = 0; i < 10; i++)
		{
			int randIndex = rand.nextInt(stock.size());
			
			p2.addDeck(stock.get(randIndex));
			
		}
		//5 karten auf die hand
		p2.addHand(p2.removeDeck());
		p2.addHand(p2.removeDeck());
		p2.addHand(p2.removeDeck());
		p2.addHand(p2.removeDeck());
		p2.addHand(p2.removeDeck());
		
	
		int round = 0;
		while(round < 10)
		{
			System.out.println("//////////////////////////////////////");
			System.out.println("Round "+ round);
			
			if(p1.getAmountOfActions() > 0)
			{
					
				//TODO auswahl ermöglichen nur Aktionskarte erlauben z.b. if
				
				//5 karten nachziehen in die Hand
				System.out.println("Player 1 Hand: ");
				while(p1.getHand().size() < 5)
				{
					p1.addHand(p1.removeDeck());
				}
				//Karten anzeigen
				int index2 = 0;
				for(Card hand : p1.getHand())
				{
					System.out.print(index2+ ". ");
					System.out.println(hand.getName() + hand.getWorth());
					index2++;
				}
				//eine Karte auswählen
				System.out.println("Bitte wähle eine Karte aus!");
				Scanner scan = new Scanner(System.in);
				int auswahl = scan.nextInt();
				//TODO ausgewählte karte clonen und dann in nachziehstapel bringen
				
				//Card c = new Card(p1.getHand().get(auswahl));
				p1.getHand().get(auswahl).setPlayer(p1);
				p1.getHand().get(auswahl).doAction();
					
			
			
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			if(p1.getAmountOfPurchases() > 0)
			{
				//TODO auswahl ermöglichen und nur Kaufkarte erlauben z.b. if
				p1.getDeck().peek();
			}
			//Karten anzeigen
			int index2 = 0;
			for(Card hand : p1.getHand())
			{
				System.out.print(index2+ ". ");
				System.out.println(hand.getName() + hand.getWorth());
				index2++;
			}
			
			System.out.println("Bitte wähle eine Karte aus!");
			Scanner scan = new Scanner(System.in);
			int auswahl = scan.nextInt();
			
			//TODO aktion wenn karte ausgewählt, je nach kartentyp
			p1.getHand().get(auswahl);
			
			//karten zurücklegen
			for(int i = 0; i < p1.getHand().size(); i++)
			{
				p1.addDeck(p1.getHand().remove(i));
			}
			
			
			//TODO Deck/Nachziehstapel neu ordnen random
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			if(p2.getAmountOfActions() > 0)
			{
				//TODO auswahl ermöglichen nur Aktionskarte erlauben z.b. if
				p2.getDeck().peek();
			}
			
			if(p2.getAmountOfPurchases() > 0)
			{
				//auswahl ermöglichen und nur Kaufkarte erlauben z.b. if
				p2.getDeck().peek();
			}
			
			//TODO karten zurücklegen
			//TODO Deck/Nachziehstapel neu ordnen random
			round++;
		}
		
	}

}
