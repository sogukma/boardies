package backend;

/**
 * In dieser Klasse sind die Geldkarten abgebildet.
 * 
 * @author Malik
 */

public class MoneyCard extends Card {

	private String name;
	/**
	 * Der Wert der Geldkarte, um ihn zu Karten zu kaufen.
	 */
	private int worth;
	/**
	 * Der Wert der Geldkarte, um damit andere Karten zu kaufen.
	 */
	private int realWorth;
	private Player player;

	/**
	 * @param name
	 *            Name der Karte
	 * @param worth
	 *            Der Wert der Geldkarte, um ihn zu Karten zu kaufen.
	 * @param realWorth
	 *            Der Wert der Geldkarte, um damit andere Karten zu kaufen.
	 */
	public MoneyCard(String name, int worth, int realWorth) {
		this.name = name;

		this.worth = worth;
		this.realWorth = realWorth;
		this.player = null;
	}

	@Override
	public MoneyCard clone() {
		String name = this.getName();
		int worth = this.getWorth();
		this.realWorth = this.getRealWorth();
		this.player = null;
		MoneyCard copy = new MoneyCard(name, worth, realWorth);
		return copy;
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * {@link MoneyCard#realWorth}
	 */
	public int getRealWorth() {
		return this.realWorth;
	}

	public void setRealWorth(int realWorth) {
		this.realWorth = realWorth;
	}

	@Override
	public String toString() {
		return "Name: " + this.name + " Worth:" + this.worth;
	}

	@Override
	public void doAction() {

	}

	/**
	 * {@inheritDoc} {@link MoneyCard#worth}
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
