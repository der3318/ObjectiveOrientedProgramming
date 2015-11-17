package game;

import java.util.ArrayList;

/*
 * 	variant two
 * 	total: 51 cards
 * 	52 cards - SA
 */

public class VariantTwo extends OldMaid {

	VariantTwo() {
		super();
	}
	
//	override dealCards to remove SA from the deck
	@Override
	protected void dealCards() {
		int rand, num;
		ArrayList<Integer> numList = new ArrayList<Integer>();
//		ignore index 0, 1 and 53, which is "R0", "B0" and "SA"
		for(int i = 2 ; i < 53 ; i++)	numList.add(i);
//		 randomize the numList
		for(int i = 0 ; i < 51 ; i++) {
			rand = (int) (Math.random() * 51);
			num = numList.get(i);
			numList.set( i, numList.get(rand) );
			numList.set(rand, num);
		}	
//		 distribute to players
		for(int i = 0 ; i < 12 ; i++)	players.get(0).addCard( numList.get(i) );
		for(int i = 12 ; i < 25 ; i++)	players.get(1).addCard( numList.get(i) );
		for(int i = 25 ; i < 38 ; i++)	players.get(2).addCard( numList.get(i) );
		for(int i = 38 ; i < 51 ; i++)	players.get(3).addCard( numList.get(i) );
	}

}
