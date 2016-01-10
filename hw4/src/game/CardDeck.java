package game;

import java.util.ArrayList;
import java.util.Random;

import foop.Card;

public class CardDeck {
	
	private ArrayList<Card> cards;
	byte[] suits = new byte[] {Card.CLUB, Card.DIAMOND, Card.HEART, Card.SPADE}; 
	
//	initialize the card Deck with 52 cards
	public CardDeck() {
		cards = new ArrayList<Card>();
		for(byte s : suits)
			for(byte v = Card.VALUE_LOWER ; v <= Card.VALUE_UPPER ; v++)	cards.add( new Card(s, v) );	
	}
	
//	get an random card and then remove it from the deck
	public Card getRandCard() {
		if( cards.isEmpty() ) {
			for(byte s : suits)
				for(byte v = Card.VALUE_LOWER ; v <= Card.VALUE_UPPER ; v++)	cards.add( new Card(s, v) );
		}
		int rand = new Random().nextInt( cards.size() );
		return cards.remove(rand);
	}
	
//	get the size of the existing card deck
	public int size() {
		return cards.size();
	}
	
//	check if the deck is empty
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
}
