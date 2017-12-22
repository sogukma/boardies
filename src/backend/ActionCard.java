
package backend;

/**
 * In dieser Klasse sind die Aktionskarten abgebildet.
 * 
 * @author Malik
 * 
 */

public class ActionCard extends Card {

	private String name;
	/**
	 * Der Wert der Geldkarte, um ihn zu Karten zu kaufen.
	 */
	private int worth;
	private Player player;
	/**
	 * Anzahl der zusätzlichen Karten, die mit der Auswahl dieser Aktionskarte
	 * zusätzlich hinzukommen.
	 */
	private int amountAddCard;
	/**
	 * Anzahl der Aktionen, die mit der Auswahl dieser Aktionskarte zusätzlich
	 * hinzukommen.
	 */
	private int amountAddAction;
	/**
	 * Anzahl der Käufe, die mit der Auswahl dieser Aktionskarte zusätzlich
	 * hinzukommen.
	 */
	private int amountAddPurchase;
	/**
	 * Menge an zusätzlichem Geld, die mit der Auswahl dieser Aktionskarte
	 * hinzukommen.
	 */
	private int amountAddMoney;

	/**
	 * @param name
	 *            Name der Karte
	 * @param worth
	 *            Der Wert der Geldkarte, um ihn zu Karten zu kaufen.
	 * @param amountAddCard
	 *            Anzahl der zusätzlichen Karten, die mit der Auswahl dieser
	 *            Aktionskarte zusätzlich hinzukommen.
	 * @param amountAddAction
	 *            Anzahl der Aktionen, die mit der Auswahl dieser Aktionskarte
	 *            zusätzlich hinzukommen.
	 * @param amountAddPurchase
	 *            Anzahl der Käufe, die mit der Auswahl dieser Aktionskarte
	 *            zusätzlich hinzukommen.
	 * @param amountAddMoney
	 *            Menge an zusätzlichem Geld, die mit der Auswahl dieser
	 *            Aktionskarte hinzukommen.
	 */
	public ActionCard(String name, int worth, int amountAddCard, int amountAddAction, int amountAddPurchase,
			int amountAddMoney) {
		this.name = name;
		this.worth = worth;
		this.amountAddCard = amountAddCard;
		this.amountAddAction = amountAddAction;
		this.amountAddPurchase = amountAddPurchase;
		this.amountAddMoney = amountAddMoney;
		this.player = null;

	}

	/**
	 * {@link ActionCard#amountAddMoney}
	 *
	 */
	public int getAmountAddMoney() {
		return amountAddMoney;
	}

	/**
	 * {@inheritDoc} {@link ActionCard#worth}
	 * 
	 */
	@Override
	public int getWorth() {
		return this.worth;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setWorth(int worth) {
		this.worth = worth;
	}

	public void setAmountAddMoney(int amountAddMoney) {
		this.amountAddMoney = amountAddMoney;
	}

	@Override
	public ActionCard clone() {
		String name = this.getName();
		int worth = this.getWorth();
		int amountAddCard = this.getAmountAddCard();
		int amountAddAction = this.getAmountAddAction();
		int amountAddPurchase = this.getAmountAddPurchase();
		int amountAddMoney = this.getAmountAddMoney();
		Player player = null;
		ActionCard copy = new ActionCard(name, worth, amountAddCard, amountAddAction, amountAddPurchase,
				amountAddMoney);
		return copy;
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * {@link ActionCard#amountAddCard}
	 * 
	 */
	public int getAmountAddCard() {
		return amountAddCard;
	}

	public void setAmountAddCard(int amountAddCard) {
		this.amountAddCard = amountAddCard;
	}

	/**
	 * {@link ActionCard#amountAddAction}
	 * 
	 */
	public int getAmountAddAction() {
		return amountAddAction;
	}

	public void setAmountAddAction(int amountAddAction) {
		this.amountAddAction = amountAddAction;
	}

	/**
	 * {@link ActionCard#amountAddPurchase}
	 * 
	 */
	public int getAmountAddPurchase() {
		return amountAddPurchase;
	}

	public void setAmountAddPurchase(int amountAddPurchase) {
		this.amountAddPurchase = amountAddPurchase;
	}

	@Override
	public String toString() {
		return "Name: " + this.name + " Worth: " + this.worth;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @author Malik Je nach Eingenschaft der Aktionskarte, wird die Anzahl der
	 *         Aktionen und Käufe erhöht und/oder der Geldstand erweitert.
	 */

	@Override
	public void doAction() {
		System.out.println("Action!");
		if (player != null) {
			player.setAmountOfActions(player.getAmountOfActions() + this.getAmountAddAction());
			player.setAmountOfPurchases(player.getAmountOfPurchases() + this.getAmountAddPurchase());
			player.setAdditionalMoney(player.getAdditionalMoney() + this.getAmountAddMoney());
		}

	}

}
