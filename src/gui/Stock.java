package gui;

import java.util.ArrayList;

public class Stock {
	private Money copper;
	private Dominion estate;
	private ActionCard laboratory;	
	private ActionCard market;
	private ActionCard valley;
	private ActionCard smith;
	private ActionCard lumberjack;
	
	private ArrayList<Card> stock;

	public Stock()
	{
	
		this.copper = new Money("Copper", 0, 1);
		this.estate = new Dominion("Estate", 2, 1);
		this.laboratory = new ActionCard("Laboratory", 5, 2, 1, 0);
		this.market = new ActionCard("Market", 5, 1, 1, 1);
		this.valley = new ActionCard("Valley", 3, 1, 2, 0);
		this.smith = new ActionCard("Smith", 4, 3, 0, 0);
		this.lumberjack = new ActionCard("Smith", 3, 0, 0, 1);
		
		this.stock = new ArrayList<Card>();
		
		this.stock.add(copper);
		this.stock.add(estate);
		this.stock.add(laboratory);
		this.stock.add(market);
		this.stock.add(valley);
		this.stock.add(smith);
		this.stock.add(lumberjack);
		
	}
	
	public ArrayList<Card> getStock()
	{
		return stock;
	}
}
