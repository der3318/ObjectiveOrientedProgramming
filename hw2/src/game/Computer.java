package game;

import java.util.ArrayList;

public class Computer {
	
	private int round;
	private ArrayList<Integer> cardIndexes;
	
	public Computer() {
		round = 1;
	}
	
	public void addRound() {
		round += 1;
	}
	
//	return false if the computer has no cards
	public boolean giveCard2Player(Player _player) {
		cardIndexes = Shuffler.getNRandList(52);
		for(int i = 0 ; i < 5 ; i++) {
			if( cardIndexes.isEmpty() )	return false;
			_player.addCard( new Card( cardIndexes.remove(0) ) );
		}
		return true;
	}
	
	public void printIntroMsg() {
		System.out.println("POOCasino Jacks or better, written by b03902015 Der Chien");
	}

	public void askPlayerName() {
		System.out.print("Please enter your name: ");
	}
	
	public void printWelcomeMsg2Player(Player _player) {
		System.out.println("Welcome, " + _player.getName() + ".");
	}

	public void printPlayerMoney(Player _player) {
		System.out.println("You have " + _player.getMoney() + " P-dollars now.");
	}
	
	public void askBet() {
		System.out.print("Please enter your P-dollar bet for round " + round + " (1-5 or 0 for quitting the game): ");
	}
	
	public void printPlayerCards(Player _player) {
		System.out.print("Your cards are");
		System.out.print( " (a) " + _player.getCards().get(0).getCardString() );
		System.out.print( " (b) " + _player.getCards().get(1).getCardString() );
		System.out.print( " (c) " + _player.getCards().get(2).getCardString() );
		System.out.print( " (d) " + _player.getCards().get(3).getCardString() );
		System.out.println( " (e) " + _player.getCards().get(4).getCardString() );
	}
	
	public void askPlayer2KeepCards(Player _player) {
		System.out.print("Which cards do you want to keep? ");
		ArrayList<Card> removedCard = _player.removeCardByStdin();
		System.out.print("Okay. I will discard");
		if( removedCard.isEmpty() )	System.out.print(" None");
		else {
			if(_player.getCards().get(0) == null) {
				System.out.print( " (a) " + removedCard.remove(0).getCardString() );
				_player.setCard(0, new Card( cardIndexes.remove(0) ) );
			}
			if(_player.getCards().get(1) == null) {
				System.out.print( " (b) " + removedCard.remove(0).getCardString() );
				_player.setCard(1, new Card( cardIndexes.remove(0) ) );
			}
			if(_player.getCards().get(2) == null) {
				System.out.print( " (c) " + removedCard.remove(0).getCardString() );
				_player.setCard(2, new Card( cardIndexes.remove(0) ) );
			}
			if(_player.getCards().get(3) == null) {
				System.out.print( " (d) " + removedCard.remove(0).getCardString() );
				_player.setCard(3, new Card( cardIndexes.remove(0) ) );
			}
			if(_player.getCards().get(4) == null) {
				System.out.print( " (e) " + removedCard.remove(0).getCardString() );
				_player.setCard(4, new Card( cardIndexes.remove(0) ) );
			}
		}
		System.out.println(".");
	}
	
	public void printNewPlayerCards(Player _player) {
		System.out.print("Your new cards are");
		for( Card c : _player.getCards() )	System.out.print( " " + c.getCardString() );
		System.out.println(".");
	}
	
	public void notifyResult2Player(Player _player) {
		int rate = Card.getRate( _player.getCards() );
		if(rate == 250	&& _player.getBet() == 5)	System.out.println("You get a royal flush hand. The payoff is 4000.");
		else if(rate == 250)	System.out.println("You get a royal flush hand. The payoff is " + _player.getBet() * rate + ".");
		else if(rate == 50)	System.out.println("You get a straight flush hand. The payoff is " + _player.getBet() * rate + ".");
		else if(rate == 25)	System.out.println("You get a four of a kind hand. The payoff is " + _player.getBet() * rate + ".");
		else if(rate == 9)	System.out.println("You get a full House hand. The payoff is " + _player.getBet() * rate + ".");
		else if(rate == 6)	System.out.println("You get a flush hand. The payoff is " + _player.getBet() * rate + ".");
		else if(rate == 4)	System.out.println("You get a straight hand. The payoff is " + _player.getBet() * rate + ".");
		else if(rate == 3)	System.out.println("You get a three of a kind hand. The payoff is " + _player.getBet() * rate + ".");
		else if(rate == 2)	System.out.println("You get a two pair hand. The payoff is " + _player.getBet() * rate + ".");
		else if(rate == 1)	System.out.println("You get a Jacks or better hand. The payoff is " + _player.getBet() * rate + ".");
		else	System.out.println("You get nothing. The payoff is 0.");
		_player.addMoney( rate * _player.getBet() );
		if(rate * _player.getBet() == 1250)	_player.addMoney(2750);
	}
	
	public void printByeMsg2Player(Player _player) {
		System.out.println("Good bye, " + _player.getName() + ". " + "You played for " + (round - 1) + " round" + ( (round > 2) ? "s" : "" ) + " and have " + _player.getMoney() + " P-dollars now.");
	}
	
}
