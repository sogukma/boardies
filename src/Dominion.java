
public class Dominion extends Card{

	private int points;
	public Dominion(String name, int worth, int points)
	{
		this.name = name;
		this.worth = worth;
		this.points = points;
		this.player = null;
	}
	
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
}
