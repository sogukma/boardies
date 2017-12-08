package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
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
//	private Socket clientSocket;
//	private  ObjectInputStream input; 
//	private  BufferedReader read;
//	private  ObjectOutputStream output;
//	private  PrintWriter write;


		/*
	public BufferedReader getRead() {
		return read;
	}

	public void setRead(BufferedReader read) {
		this.read = read;
	}

	public PrintWriter getWrite() {
		return write;
	}

	public void setWrite(PrintWriter write) {
		this.write = write;
	}
*/
	public Player(String name, int amountOfActions, int amountOfPurchases)
	{
		this.name = name;
		this.amountOfActions = amountOfActions;
		this.amountOfPurchases = amountOfPurchases;
		this.deck = new LinkedList<Card>();
		this.hand = new ArrayList<Card>();
		this.handSize = 5;
		this.points = 0;
		this.additionalMoney = 0;
		/*
		this.clientSocket = null;
		try {
			this.input = new ObjectInputStream(this.clientSocket.getInputStream());
			this.read = new BufferedReader(new InputStreamReader(input));
			this.output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			this.write = new PrintWriter(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 */
	}

	public int getAdditionalMoney() {
		return additionalMoney;
	}

	public void setAdditionalMoney(int additionalMoney) {
		this.additionalMoney = additionalMoney;
	}

	public void setHandSize(int handSize)
	{
		this.handSize = handSize;
	}
	
	public int getHandSize()
	{
		return this.handSize;
	}
	
	public void runClient()
	{
		
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



/*
	public Socket getSocket() {
		return this.clientSocket;
	}


	public void setSocket(Socket accept) {
		this.clientSocket = accept;
		
	}
	*/
	//add hand
	//add deck
	//count points
}
