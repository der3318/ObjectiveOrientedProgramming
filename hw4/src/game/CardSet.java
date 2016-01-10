package game;

import java.util.ArrayList;

import foop.Card;

public class CardSet {

//	an arrayList of cards
	private ArrayList<Card> cards;
	
	public CardSet() {
		cards = new ArrayList<Card>();
	}

//	add a specific card to the cardSet
	public void addCard(Card _card) {
		cards.add(_card);
	}
	
//	clear the cardSet
	public void clear() {
		cards.clear();
	}

//	get the specific card by Index
	public Card getCard(int _index) {
		if( _index >= cards.size() )	return null;
		return cards.get(_index);
	}
	
//	get the cards
	public ArrayList<Card> getCards() {
		return cards;
	}
	
//	get the sum of the cards' values
	public int getValue() {
		int sum = 0;
		for(Card c : cards) {
			int point = c.getValue() - Card.VALUE_LOWER + 1;
			if(point > 10)	point = 10;
			sum += point;
		}
		for(Card c : cards)
			if(c.getValue() == Card.VALUE_LOWER && sum <= 11)	sum += 10;
		return sum;
	}
	
}
