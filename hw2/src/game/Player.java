package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Player {
	
	private String name;
	private int money;
	private int bet;
	private ArrayList<Card> cards;
	
	Player() {
		name = new String();
		money = 1000;
		bet = 0;
		cards = new ArrayList<Card>();
	}
	
	public void setNameByStdin() {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		try {
			name = br.readLine();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void addMoney(int _money) {
		money += _money;
	}
	
//	return false if the player cannot pay the money
	public boolean payMoney(int _money) {
		if(money < _money)	return false;
		money -= _money;
		return true;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setBetByStdin() {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		try {
			bet = Integer.parseInt( br.readLine() );
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getBet() {
		return bet;
	}
	
	public void addCard(Card _card) {
		cards.add(_card);
	}
	
	public void setCard(int _index, Card _card) {
		cards.set(_index, _card);
	}
	
//	return the ArrayList of cards discarded by the player
	public ArrayList<Card> removeCardByStdin() {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		String ans = new String();
		ArrayList<Card> output = new ArrayList<Card>();
		try {
			ans = br.readLine();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		if( ans.contains("none") )	ans = "";
		if( !ans.contains("a") ) {
			output.add( cards.get(0) );
			cards.set(0, null) ;
		}
		if( !ans.contains("b") ) {
			output.add( cards.get(1) );
			cards.set(1, null) ;
		}
		if( !ans.contains("c") ) {
			output.add( cards.get(2) );
			cards.set(2, null) ;
		}
		if( !ans.contains("d") ) {
			output.add( cards.get(3) );
			cards.set(3, null) ;
		}
		if( !ans.contains("e") ) {
			output.add( cards.get(4) );
			cards.set(4, null) ;
		}
		return output;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void removeAllCards() {
		cards.clear();
	}

}
