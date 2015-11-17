package game;

import java.util.ArrayList;

/*
 * 	variant one
 * 	total: 54 cards
 * 	52 cards + B0 + S8
 */

public class VariantOne extends OldMaid {

	VariantOne() {
		super();
	}
	
//	override dealCards to add only "B0" and another "S8" into the deck
	@Override
	protected void dealCards() {
		int rand, num;
		ArrayList<Integer> numList = new ArrayList<Integer>();
//		ignore index 0, which is "R0"
		for(int i = 1 ; i < 54 ; i++)	numList.add(i);
//		 add "S8"
		numList.add(29);
//		 randomize the numList
		for(int i = 0 ; i < 54 ; i++) {
			rand = (int) (Math.random() * 54);
			num = numList.get(i);
			numList.set( i, numList.get(rand) );
			numList.set(rand, num);
		}	
//		 distribute to players
		for(int i = 0 ; i < 14 ; i++)	players.get(0).addCard( numList.get(i) );
		for(int i = 14 ; i < 28 ; i++)	players.get(1).addCard( numList.get(i) );
		for(int i = 28 ; i < 41 ; i++)	players.get(2).addCard( numList.get(i) );
		for(int i = 41 ; i < 54 ; i++)	players.get(3).addCard( numList.get(i) );
	}
	
}
