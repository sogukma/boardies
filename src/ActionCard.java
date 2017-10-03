
public class ActionCard extends Card {

	private int amountAddCard;
	private int amountAddAction;
	private int amountAddPurchase;
	

	public ActionCard(int amountAddCard, int amountAddAction, int amountAddPurchase) {
		this.amountAddCard = amountAddCard;
		this.amountAddAction = amountAddAction;
		this.amountAddPurchase = amountAddPurchase;
			
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

}
