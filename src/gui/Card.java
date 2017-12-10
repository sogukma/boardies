package gui;


public abstract class Card {

	public abstract int getWorth();
	public abstract void setWorth(int worth);
	public abstract String getName();
	public abstract void setName(String name);
	public abstract Player getPlayer();
	public abstract void setPlayer(Player p);
	public abstract void doAction();
	public abstract String toString();
	public abstract Card clone();

}
