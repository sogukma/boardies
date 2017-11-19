package gui;

import java.util.ArrayList;

public class Stock {
	private ActionCard ac;
	private Dominion dn;
	private Dominion dn2;
	private Dominion dn3;
	private Money mn;
	private ArrayList<Card> stock;

	public Stock()
	{
		this.ac = new ActionCard("Action1", 1, 1, 0, 1);
		this.dn = new Dominion("house", 1, 2);
		this.dn2 = new Dominion("castle", 3, 1);
		this.dn3 = new Dominion("country", 2, 3);
		this.mn = new Money("Gold", 1, 2);
		this.stock = new ArrayList<Card>();
		this.stock.add(ac);
		this.stock.add(dn);
		this.stock.add(dn2);
		this.stock.add(dn3);
		this.stock.add(mn);
		
	}
	
	public ArrayList<Card> getStock()
	{
		return stock;
	}
}
