import java.util.ArrayList;

import foop.Card;
import foop.Hand;
import foop.Player;

public class PlayerB03902015 extends Player {

	private int bet;
	private int lastChip;
	
	public PlayerB03902015(int _nChip) {
		super(_nChip);
		lastChip = 0;
	}

	@Override
	public boolean buy_insurance(Card _myOpen, Card _dealerOpen, ArrayList<Hand> _curTable) {
		if(_myOpen.getValue() == Card.VALUE_LOWER)	return false;
		return true;
	}

	@Override
	public boolean do_double(Hand _myOpen, Card _dealerOpen, ArrayList<Hand> _curTable) {
		int sum = 0;
		for( Card c : _myOpen.getCards() ) {
			int point = c.getValue() - Card.VALUE_LOWER + 1;
			if(point > 10)	point = 10;
			sum += point;
		}
		for( Card c : _myOpen.getCards() )
			if(c.getValue() == Card.VALUE_LOWER && sum <= 11)	sum += 10;
		if(sum > 10 && sum < 16)	return true;
		return false;
	}

	@Override
	public boolean do_split(ArrayList<Card> _myOpen, Card _dealerOpen, ArrayList<Hand> _curTable) {
		if(_myOpen.get(0).getValue() - Card.VALUE_LOWER + 1 > 8)	return false;
		return true;
	}

	@Override
	public boolean do_surrender(Card _myOpen, Card _dealerOpen, ArrayList<Hand> _curTable) {
		return false;
	}

	@Override
	public boolean hit_me(Hand _myOpen, Card _dealerOpen, ArrayList<Hand> _curTable) {
		int sum = 0;
		for( Card c : _myOpen.getCards() ) {
			int point = c.getValue() - Card.VALUE_LOWER + 1;
			if(point > 10)	point = 10;
			sum += point;
		}
		for( Card c : _myOpen.getCards() )
			if(c.getValue() == Card.VALUE_LOWER && sum <= 11)	sum += 10;
		if(sum > 15)	return false;
		return true;
	}

	@Override
	public int make_bet(ArrayList<Hand> arg0, int arg1, int arg2) {
		if( (int)this.get_chips() > lastChip )	bet = (int)(this.get_chips() / 100);
		else	bet *= 2;
		if( bet > (int)(this.get_chips() / 5) )	bet = (int)(this.get_chips() / 5);
		lastChip = (int)this.get_chips();
		return bet;
	}

	@Override
	public String toString() {
		return new String("B03902015: " + this.get_chips() + " Chips");
	}

}
