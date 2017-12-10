package gui;


public class Dominion extends Card{

	private String name;
	private int worth;
	private int points;
	private Player player;
	public Dominion(String name, int worth, int points)
	{
		this.name = name;
		this.worth = worth;
		this.points = points;
		this.player = null;
	}
	
	@Override
	public Dominion clone()
	{
		String name = this.getName();
		int worth = this.getWorth();
		this.points = this.getPoints();
		this.player = this.getPlayer();
		Player player = null;
		Dominion copy = new Dominion(name, worth, points);
		return copy;
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
	
	public String toString()
	{
		return "Name: "+ this.name + " Worth:"+ this.worth;
	}
	@Override
	public void doAction()
	{
		System.out.println("Action!");
		if(player != null)
		{
			player.setPoints(player.getPoints()+ this.getPoints());
		}
		
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
