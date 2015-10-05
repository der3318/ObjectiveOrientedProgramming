import java.util.ArrayList;
import java.util.HashMap;

public class Player {

	private int index;
	private HashMap<Integer, Boolean> cardList;
	
	public Player(int _index) {
		cardList = new HashMap<Integer, Boolean>();
		index = _index;
		for(int i = 0 ; i < 54 ; i++)	cardList.put(i, false);
	}
	
	public void addCard(int _index) {
		cardList.put(_index, true);
	}
	
	public void removeCard(int _index) {
		cardList.put(_index, false);
	}

//	drop the cards if you have 2 cards with same number
	public void drop() {
		for(int num = 0 ; num < 13 ; num++) {
			if( cardList.get(num * 4 + 2) && cardList.get(num * 4 + 3) ) {
				cardList.put(num * 4 + 2, false);
				cardList.put(num * 4 + 3, false);
			}
			if( cardList.get(num * 4 + 2) && cardList.get(num * 4 + 4) ) {
				cardList.put(num * 4 + 2, false);
				cardList.put(num * 4 + 4, false);
			}
			if( cardList.get(num * 4 + 3) && cardList.get(num * 4 + 4) ) {
				cardList.put(num * 4 + 3, false);
				cardList.put(num * 4 + 4, false);
			}
			if( cardList.get(num * 4 + 2) && cardList.get(num * 4 + 5) ) {
				cardList.put(num * 4 + 2, false);
				cardList.put(num * 4 + 5, false);
			}
			if( cardList.get(num * 4 + 3) && cardList.get(num * 4 + 5) ) {
				cardList.put(num * 4 + 3, false);
				cardList.put(num * 4 + 5, false);
			}
			if( cardList.get(num * 4 + 4) && cardList.get(num * 4 + 5) ) {
				cardList.put(num * 4 + 4, false);
				cardList.put(num * 4 + 5, false);
			}
		}
	}
	
	public boolean isWinner() {
		for( int key : cardList.keySet() )	if( cardList.get(key) )	return false;
		return true;
	}
	
	public int getIndex() {
		return index;
	}
	
//	return a card randomly
	public int getRandCard() {
		int rand = (int) (Math.random() * 54);
		while( !cardList.get(rand) )	rand = (rand + 1) % 54;
		return rand;
	}
	
//	return an ArrayList containing all the cards the player owns
	public ArrayList<Integer> getCardList() {
		ArrayList<Integer> output = new ArrayList<Integer>();
		for( int key : cardList.keySet() )	if( cardList.get(key) )	output.add(key);
		return output;
	}

}
