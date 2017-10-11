
public class ActionCard extends Card {

	private String name;
	private int worth;
	private Player player;
	private int amountAddCard;
	private int amountAddAction;
	private int amountAddPurchase;
	

	public ActionCard(String name, int worth, int amountAddCard, int amountAddAction, int amountAddPurchase) {
		this.name = name;
		this.worth = worth;
		this.amountAddCard = amountAddCard;
		this.amountAddAction = amountAddAction;
		this.amountAddPurchase = amountAddPurchase;
		this.player = null;
			
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}	


	public int getAmountAddCard() {
		return amountAddCard;
	}


	public void setAmountAddCard(int amountAddCard) {
		this.amountAddCard = amountAddCard;
	}


	public int getAmountAddAction() {
		return amountAddAction;
	}


	public void setAmountAddAction(int amountAddAction) {
		this.amountAddAction = amountAddAction;
	}


	public int getAmountAddPurchase() {
		return amountAddPurchase;
	}


	public void setAmountAddPurchase(int amountAddPurchase) {
		this.amountAddPurchase = amountAddPurchase;
	}
	
	@Override
	public void doAction()
	{
		System.out.println("Action!");
		if(player != null)
		{
			player.addHand(player.removeDeck());
			player.setAmountOfActions(player.getAmountOfActions()+ this.getAmountAddAction());
			player.setAmountOfPurchases(player.getAmountOfPurchases() + this.getAmountAddPurchase());
		}
		
	}

}
