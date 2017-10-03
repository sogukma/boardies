import java.util.ArrayList;

public class Player {

	private String name;
	private int amountOfActions;
	private int amountOfPurchases;
	private ArrayList<Card> deck;
	
	public Player(String name, int amountOfActions, int amountOfPurchases, ArrayList<Card> deck)
	{
		this.name = name;
		this.amountOfActions = amountOfActions;
		this.amountOfPurchases = amountOfPurchases;
		this.deck = deck;
		
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmountOfActions() {
		return amountOfActions;
	}

	public void setAmountOfActions(int amountOfActions) {
		this.amountOfActions = amountOfActions;
	}

	public int getAmountOfPurchases() {
		return amountOfPurchases;
	}

	public void setAmountOfPurchases(int amountOfPurchases) {
		this.amountOfPurchases = amountOfPurchases;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
}
