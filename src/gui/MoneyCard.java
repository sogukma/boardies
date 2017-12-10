package gui;


public class MoneyCard extends Card{

	private String name;
	private int worth;
	private int realWorth;
	private Player player;
	
	public MoneyCard(String name, int worth, int realWorth)
	{
		this.name = name;
		this.worth = worth; //der wert um diese karte zu kaufen
		this.realWorth = realWorth; //der wert um andere Karten zu kaufen
		this.player = null;
	}
	
	@Override
	public MoneyCard clone()
	{
		String name = this.getName();
		int worth = this.getWorth();
		this.realWorth = this.getRealWorth();
		this.player = null;
		MoneyCard copy = new MoneyCard(name, worth, realWorth);
		return copy;
	}
	@Override
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public int getRealWorth()
	{
		return this.realWorth;
	}
	
	
	public void setRealWorth(int realWorth)
	{
		this.realWorth = realWorth;
	}
	@Override
	public String toString()
	{
		return "Name: "+this.name + " Worth:"+this.worth;
	}
	
	@Override
	public void doAction()
	{
		System.out.println("Action!");
		
	}

	@Override
	public int getWorth() {
		return this.worth;
	}

	@Override
	public void setWorth(int worth) {
		this.worth = worth;
		
	}

	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public Player getPlayer() {
	
		return this.player;
	}
}

