package game;

import java.util.ArrayList;

import foop.Card;

public class Printer {

	public static String[] values = new String[] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

//	show the bets
	public static void showBets(ArrayList<Integer> _bets) {
		for(int i = 0 ; i < _bets.size() ; i++)	System.out.print("Player" + (i + 1) + "'s Bet: " + _bets.get(i) + "\t");
		System.out.println("");
	}

//	show the cards of a cardSet
	public static void showCards(CardSet _cardSet) { 
		for( Card c : _cardSet.getCards() ) {
			if(c.getSuit() == Card.CLUB)	System.out.print("   (CLUB)");
			if(c.getSuit() == Card.DIAMOND)	System.out.print("(DIAMOND)");
			if(c.getSuit() == Card.HEART)	System.out.print("  (HEART)");
			if(c.getSuit() == Card.SPADE)	System.out.print("  (SPADE)");
			System.out.print(values[c.getValue() - Card.VALUE_LOWER]);
			System.out.print("\t");
		}
		System.out.println("");
	}
	
}
