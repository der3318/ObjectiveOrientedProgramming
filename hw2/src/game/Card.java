package game;

import java.util.ArrayList;
import java.util.Collections;

public class Card {

	private static ArrayList<String> cardString;
	private int index;

	public Card(int _index) {
		index = _index;
		if(cardString == null) {
			cardString = new ArrayList<String>();
			for(int i = 2; i <= 10; i++) {
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
	}

//	return the rate of the cards result
	public static int getRate(ArrayList<Card> _cards) {
		ArrayList<Integer> cardIndexes = new ArrayList<Integer>();
		for(Card c : _cards)	cardIndexes.add( c.getIndex() );
		Collections.sort(cardIndexes);
		if(isSameSuit(cardIndexes) && isSequence(cardIndexes) && cardIndexes.get(0) / 4 == 8)	return 250;
		if( isSameSuit(cardIndexes) && isSequence(cardIndexes) )	return 50;
		for(int i = 0 ; i < 5 ; i++) {
			ArrayList<Integer> subIndexes = new ArrayList<Integer>();
			for(int j = 0 ; j < 5 ; j++)	if(i != j)	subIndexes.add( cardIndexes.get(j) );
			if( isSameNum(subIndexes) )	return 25;
		}
		for(int i = 1 ; i <= 2 ; i++) {
			ArrayList<Integer> subIndexes1 = new ArrayList<Integer>();
			ArrayList<Integer> subIndexes2 = new ArrayList<Integer>();
			for(int j = 0 ; j < 5 ; j++) {
				if(j <= i)	subIndexes1.add( cardIndexes.get(j) );
				else	subIndexes2.add( cardIndexes.get(j) );
			}
			if( isSameNum(subIndexes1) && isSameNum(subIndexes2) )	return 9;
		}
		if( isSameSuit(cardIndexes) )	return 6;
		if( isSequence(cardIndexes) )	return 4;
		for(int i = 0 ; i < 3 ; i++) {
			ArrayList<Integer> subIndexes = new ArrayList<Integer>();
			for(int j = i ; j < i + 3 ; j++)	subIndexes.add( cardIndexes.get(j) );
			if( isSameNum(subIndexes) )	return 3;
		}
		for(int i = 0 ; i < 5 ; i++) {
			ArrayList<Integer> subIndexes = new ArrayList<Integer>();
			int j = 0;
			for(j = 0 ; j < 5 ; j++) {
				if(i == j)	continue;
				subIndexes.add( cardIndexes.get(j) );
				if(subIndexes.size() == 2) {
					if( isSameNum(subIndexes) )	subIndexes.clear();
					else	break;
				}
			}
			if(j == 5)	return 2;
		}
		for(int i = 9 ; i < 13 ; i++) {
			int count = 0;
			for(Integer j : cardIndexes)	if( i == (j / 4) )	count++;
			if(count > 1)	return 1; 
		}
		return 0;
		
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getCardString() {
		return cardString.get(index);
	}
	
	// get the specific string of card with the index
	public static String getCardStringByIndex(int _index) {
		return cardString.get(_index);
	}
	
	private static boolean isSameSuit(ArrayList<Integer> _indexes) {
		for(int i = 1 ; i < _indexes.size() ; i++)	if(_indexes.get(i) % 4 != _indexes.get(0) % 4)	return false;
		return true;
	}
	
	private static boolean isSameNum(ArrayList<Integer> _indexes) {
		for(int i = 1 ; i < _indexes.size() ; i++)	if(_indexes.get(i) / 4 != _indexes.get(0) / 4)	return false;
		return true;
	}
	
	private static boolean isSequence(ArrayList<Integer> _indexes) {
		int i = 0;
		for(i = 1 ; i < _indexes.size() ; i++)	if( _indexes.get(i) - 4 != _indexes.get(i - 1) )	break;
		if( i >= _indexes.size() )	return true;
		if(_indexes.get(0) / 4 != 0 || _indexes.get(_indexes.size() - 1) / 4 != 12)	return false;
		for(i = i + 1 ; i < _indexes.size() ; i++)		if( _indexes.get(i) - 4 != _indexes.get(i - 1) )	return false;
		return true;
	}

}
