
public class Card {

	protected String name;
	protected int worth;
	public Card(String name, int worth) {
		this.name = name;
		this.worth = worth;
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
