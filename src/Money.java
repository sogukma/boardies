
public class Money extends Card{

	int realWorth;
	public Money(String name, int worth, int realWorth)
	{
		this.name = name;
		this.worth = worth; //der wert um diese karte zu kaufen
		this.realWorth = realWorth; //der wert um andere Karten zu kaufen
		this.player = null;
	}
	
	@Override
	public Money clone()
	{
		String name = this.getName();
		int worth = this.getWorth();
		this.realWorth = this.getRealWorth();
		this.player = null;
		Money copy = new Money(name, worth, realWorth);
		return copy;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	@Override
	public int getRealWorth()
	{
		return this.realWorth;
	}
	
	@Override
	public void setRealWorth(int realWorth)
	{
		this.realWorth = realWorth;
	}
	public String toString()
	{
		return "Name: "+this.name + " Worth:"+this.worth;
	}
	
	@Override
	public void doAction()
	{
		System.out.println("Action!");
		if(player != null)
		{
			this.player.setPoints(this.player.getPoints() + this.getRealWorth());
		}
		
	}
}

