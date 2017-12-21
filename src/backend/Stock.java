package backend;

import java.util.ArrayList;

public class Stock {
	private MoneyCard copper;
	private MoneyCard silver;
	private MoneyCard gold;
	private EstateCard estate;
	private EstateCard duchy;
	private EstateCard province;
	private ActionCard laboratory;	
	private ActionCard market;
	private ActionCard valley;
	private ActionCard smith;
	private ActionCard lumberjack;
	
	private ArrayList<Card> stock;

	public static final int COPPER_ID = 0;
	public static final int SILVER_ID = 1;
	public static final int GOLD_ID = 2;
	public static final int ESTATE_ID = 3;
	public static final int DUCHY_ID = 4;
	public static final int PROVINCE_ID = 5;
	public static final int LABORATORY_ID = 6;
	public static final int MARKET_ID = 7;
	public static final int VALLEY_ID = 8;
	public static final int SMITH_ID = 9;
	public static final int LUMBERJACK_ID = 10;
	
	public Stock()
	{
	
		
		this.copper = new MoneyCard("Copper", 0, 1);
		this.silver = new MoneyCard("Silver", 3, 2);
		this.gold = new MoneyCard("Gold", 6, 3);
		this.estate = new EstateCard("Estate", 2, 1);
		this.duchy = new EstateCard("Duchy", 5, 3);
		this.province = new EstateCard("Province", 8, 6);
		this.laboratory = new ActionCard("Laboratory", 5, 2, 1, 0, 0);
		this.market = new ActionCard("Market", 5, 1, 1, 1, 1);
		this.valley = new ActionCard("Valley", 3, 1, 2, 0, 0);
		this.smith = new ActionCard("Smith", 4, 3, 0, 0, 0);
		this.lumberjack = new ActionCard("Lumberjack", 3, 0, 0, 1, 2);
		
		this.stock = new ArrayList<Card>();
		
		this.stock.add(copper);
		this.stock.add(silver);
		this.stock.add(gold);
		this.stock.add(estate);
		this.stock.add(duchy);
		this.stock.add(province);
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
