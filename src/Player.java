import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Player {

	private String name;
	private int amountOfActions;
	private int amountOfPurchases;
	private int points;
	private Queue<Card> deck;
	private ArrayList<Card> hand;
	
	public Player(String name, int amountOfActions, int amountOfPurchases)
	{
		this.name = name;
		this.amountOfActions = amountOfActions;
		this.amountOfPurchases = amountOfPurchases;
		this.deck = new LinkedList<Card>();
		this.hand = new ArrayList<Card>();
		this.points = 0;
		
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
	
	public void addAmountOfActions()
	{
		this.amountOfActions++;
	}

	public void reduceAmountOfActions()
	{
		this.amountOfActions--;
	}

	
	public int getAmountOfPurchases() {
		return amountOfPurchases;
	}

	public void setAmountOfPurchases(int amountOfPurchases) {
		this.amountOfPurchases = amountOfPurchases;
	}
	
	public void addAmountOfPurchases()
	{
		this.amountOfPurchases++;
	}

	public void reduceAmountOfPurchases()
	{
		this.amountOfPurchases--;
	}

	public Queue<Card> getDeck() {
		return deck;
	}

	public void setDeck(Queue<Card> deck) {
		this.deck = deck;
	}
	
	public void addDeck(Card card)
	{
		deck.add(card);
	}
	
	public Card removeDeck()
	{
		return deck.poll();
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public void addHand(Card card)
	{
		
		hand.add(card);
		
	}
	
	public int getPoints()
	{
		return this.points;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	/*
	public String toString()
	{
		return "Hand: "+ this.hand.toString();
	}*/
	
	//add hand
	//add deck
	//count points
}
