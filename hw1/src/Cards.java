import java.util.ArrayList;

public class Cards {

	private ArrayList<String> cardString;
	
	public Cards() {
		cardString = new ArrayList<String>();
		cardString.add("R0");
		cardString.add("B0");
		for(int i = 2 ; i <= 10 ; i++) {
			cardString.add("C" + i);
			cardString.add("D" + i);
			cardString.add("H" + i);
			cardString.add("S" + i);
		}
		cardString.add("CJ");
		cardString.add("DJ");
		cardString.add("HJ");
		cardString.add("SJ");
		cardString.add("CQ");
		cardString.add("DQ");
		cardString.add("HQ");
		cardString.add("SQ");
		cardString.add("CK");
		cardString.add("DK");
		cardString.add("HK");
		cardString.add("SK");
		cardString.add("CA");
		cardString.add("DA");
		cardString.add("HA");
		cardString.add("SA");
	}
	
//	get the specific string of card with the index
	public String getCardString(int _index) {
		return cardString.get(_index);
	}
	
}
