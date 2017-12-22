package backend;

/**
 * 
 * In dieser Klasse sind die Punktekarten abgebildet. 
 * @author Malik
 */

public class EstateCard extends Card{

	private String name;
	/**
	 * Der Wert der Geldkarte, um ihn zu Karten zu kaufen.
	 */
	private int worth;
	private int points;
	private Player player;
	/**
	 * @param name Name der Punktekarte
	 * @param worth Der Wert der Geldkarte, um ihn zu Karten zu kaufen.
	 * @param points Die Punktzahl, die beim Kauf dieser Karte dem Spieler überreicht wird.
	 */
	public EstateCard(String name, int worth, int points)
	{
		this.name = name;
		this.worth = worth;
		this.points = points;
		this.player = null;
	}
	
	@Override
	public EstateCard clone()
	{
		String name = this.getName();
		int worth = this.getWorth();
		this.points = this.getPoints();
		this.player = this.getPlayer();
		Player player = null;
		EstateCard copy = new EstateCard(name, worth, points);
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
	/**
	 * {@inheritDoc}
	 * 
	 * Hier wird die Punktzahl des Spielers erweitert.
	 * 
	 */
	@Override
	public void doAction()
	{
		System.out.println("Action!");
		if(player != null)
		{
			player.setPoints(player.getPoints()+ this.getPoints());
		}
		
	}

	/**
	 * {@link EstateCard#worth}
	 */
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
