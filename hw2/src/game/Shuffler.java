package game;

import java.util.ArrayList;

public class Shuffler {

	public static ArrayList<Integer> getNRandList(int _n) {
		int rand, num;
		ArrayList<Integer> output = new ArrayList<Integer>();
		for(int i = 0 ; i < _n ; i++)	output.add(i);
		for (int i = 0 ; i < _n ; i++) {
			rand = (int) (Math.random() * _n);
			num = output.get(i);
			output.set( i, output.get(rand) );
			output.set(rand, num);
		}	
		return output;
	}
	
}
