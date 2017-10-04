import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ActionCard ac = new ActionCard(1, 1, 1);
		Dominion dn = new Dominion("house", 1, 2);
		Money mn = new Money("Gold", 1,2);
		
		ArrayList<Card> list = new ArrayList<Card>();
		list.add(ac);
		list.add(dn);
		list.add(mn);
		Player p1 = new Player("saf", 1, 1, list);

		Player p2 = new Player("gfa", 1, 1, list);
		
		int round = 0;
		while(round < 10)
		{
			if(p1.getAmountOfActions() > 0)
			{
				//auswahl ermöglichen nur Aktionskarte erlauben z.b. if
				p1.getDeck().get(0);
			}
			
			if(p1.getAmountOfPurchases() > 0)
			{
				//auswahl ermöglichen und nur Kaufkarte erlauben z.b. if
				p1.getDeck().get(0);
			}
			
			
		}
		
	}

}
