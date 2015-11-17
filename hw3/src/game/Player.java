package game;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

//	 the index of player
	private int index;
//	 an hashMap representing the cards of a player, where (key, value) = (cardIndex, the number of the specific cards) 
	private HashMap<Integer, Integer> cardList;
	
	public Player(int _index) {
		cardList = new HashMap<Integer, Integer>();
		index = _index;
//		 initialize the number of each cards to 0
		for(int i = 0 ; i < 54 ; i++)	cardList.put(i, 0);
	}
	
	public void addCard(int _index) {
		cardList.put(_index, cardList.get(_index) + 1);
	}
	
	public void removeCard(int _index) {
		cardList.put(_index, cardList.get(_index) - 1);
	}

//	drop the cards if you have 2 cards with same number
	public void drop() {
		for(int num = 0 ; num < 13 ; num++)
//			 if CardNumber = 0, then index = 2, 3, 4 and 5
			for(int second = 2 ; second <= 5 ; second++) {
				for(int first = 2 ; first < second ; first++)
					if(cardList.get(num * 4 + first) > 0 && cardList.get(num * 4 + second) > 0) {
						removeCard(num * 4 + first);
						removeCard(num * 4 + second);
					}
				if(cardList.get(num * 4 + second) >= 2)	cardList.put(num * 4 + second, cardList.get(num * 4 + second) - 2);
			}
	}
	
	public boolean isWinner() {
//		 if the number of any cards > 0, the player is not a winner
		for( int key : cardList.keySet() )	if(cardList.get(key) > 0)	return false;
		return true;
	}
	
	public int getIndex() {
		return index;
	}
	
//	return a card randomly
	public int getRandCard() {
		int rand = (int) (Math.random() * 54);
		while(cardList.get(rand) == 0)	rand = (rand + 1) % 54;
		return rand;
	}
	
//	return an ArrayList containing all the cards the player owns
	public ArrayList<Integer> getCardList() {
		ArrayList<Integer> output = new ArrayList<Integer>();
		for( int key : cardList.keySet() )
			for(int i = 0 ; i < cardList.get(key) ; i++)	output.add(key);
		return output;
	}

}
