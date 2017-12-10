package gui;


public class ActionCard extends Card {

	private String name;
	private int worth;
	private Player player;
	private int amountAddCard;
	private int amountAddAction;
	private int amountAddPurchase;
	private int amountAddMoney;

	public ActionCard(String name, int worth, int amountAddCard, int amountAddAction, int amountAddPurchase, int amountAddMoney) {
		this.name = name;
		this.worth = worth;
		this.amountAddCard = amountAddCard;
		this.amountAddAction = amountAddAction;
		this.amountAddPurchase = amountAddPurchase;
		this.amountAddMoney = amountAddMoney;
		this.player = null;
		
			
	}
	
	public int getAmountAddMoney() {
		return amountAddMoney;
	}

	@Override
	public int getWorth()
	{
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
	public ActionCard clone()
	{
		String name = this.getName();
		int worth = this.getWorth();
		int amountAddCard = this.getAmountAddCard();
		int amountAddAction = this.getAmountAddAction();
		int amountAddPurchase = this.getAmountAddPurchase();
		int amountAddMoney = this.getAmountAddMoney();
		Player player = null;
		ActionCard copy = new ActionCard(name, worth, amountAddCard, amountAddAction, amountAddPurchase, amountAddMoney);
		return copy;
	}
	@Override
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
	public String toString()
	{
		return "Name: "+this.name + " Worth: "+ this.worth;
	}
	
	
	
	@Override
	public void doAction()
	{
		System.out.println("Action!");
		if(player != null)
		{
			player.setAmountOfActions(player.getAmountOfActions()+ this.getAmountAddAction());
			player.setAmountOfPurchases(player.getAmountOfPurchases() + this.getAmountAddPurchase());
//			player.setHandSize(player.getHandSize()+ this.getAmountAddCard());
			player.setAdditionalMoney(player.getAdditionalMoney()+this.getAmountAddMoney());
		}
		
	}

}
