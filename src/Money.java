
public class Money extends Card{

	int realWorth;
	public Money(String name, int worth, int realWorth)
	{
		this.name = name;
		this.worth = worth;
		this.realWorth = realWorth;
		this.player = null;
	}
	
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
	public void doAction()
	{
		System.out.println("Action!");
		if(player != null)
		{
			System.out.println("Action comes here!");
		}
		
	}
}

