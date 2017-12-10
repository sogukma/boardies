package gui;


public abstract class Card {

	public abstract int getWorth();
	public abstract void setWorth(int worth);
	public abstract String getName();
	public abstract void setName(String name);
	public abstract Player getPlayer();
	public abstract void setPlayer(Player p);
	public abstract void doAction();
	public abstract String toString();
	public abstract Card clone();
//	public abstract int getRealWorth();
//	public abstract void setRealWorth(int realWorth);
	
	/*
	protected String name;
	protected int worth;
	protected Player player;
	*/
	
//	public Card(String name, int worth) {
//		this.name = name;
//		this.worth = worth;
//		this.player = null;
//	}
//	
//	public Card(Card another)
//	{
//		this.name = another.getName();
//		this.worth = another.getWorth();
//		this.player = another.getPlayer();
//	}
	
	
	
	
	
	
	
	
	
	/*
	public Card()
	{

	}
	
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWorth() {
		return worth;
	}

	public void setWorth(int worth) {
		this.worth = worth;
	}
	


	public void doAction()
	{}

	
	public String toString()
	{
		return "Name: "+this.getName() + "Worth: "+this.worth;
	}

	public void setRealWorth(int realWorth) {
		// TODO Auto-generated method stub
		
	}

	public int getRealWorth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Card clone() {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
