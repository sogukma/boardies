package gui;

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
	private int handSize;
	private int additionalMoney;

	public Player(String name) {
		this.name = name;
		this.amountOfActions = Main.INITIAL_AMOUNT_OF_ACTIONS;
		this.amountOfPurchases = Main.INITIAL_AMOUNT_OF_PURCHASES;
		this.deck = new LinkedList<Card>();
		this.hand = new ArrayList<Card>();
		this.handSize = Main.INITIAL_HAND_SIZE;
		this.points = 0;
		this.additionalMoney = 0;

	}

	public int getAdditionalMoney() {
		return additionalMoney;
	}

	public void setAdditionalMoney(int additionalMoney) {
		this.additionalMoney = additionalMoney;
	}

	public void setHandSize(int handSize) {
		this.handSize = handSize;
	}

	public int getHandSize() {
		return this.handSize;
	}

	public void runClient() {

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

	public void addAmountOfActions() {
		this.amountOfActions++;
	}

	public void reduceAmountOfActions() {
		this.amountOfActions--;
	}

	public int getAmountOfPurchases() {
		return amountOfPurchases;
	}

	public void setAmountOfPurchases(int amountOfPurchases) {
		this.amountOfPurchases = amountOfPurchases;
	}

	public void addAmountOfPurchases() {
		this.amountOfPurchases++;
	}

	public void reduceAmountOfPurchases() {
		this.amountOfPurchases--;
	}

	public Queue<Card> getDeck() {
		return deck;
	}

	public void setDeck(Queue<Card> deck) {
		this.deck = deck;
	}

	public void addDeck(Card card) {
		deck.add(card);
	}

	public Card removeDeck() {
		return deck.poll();
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public void addHand(Card card) {

		hand.add(card);

	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
