
public class Card {

	protected String name;
	protected int worth;
	protected Player player;
	public Card(String name, int worth) {
		this.name = name;
		this.worth = worth;
		this.player = null;
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

	public Card()
	{

	}

}
