import java.util.ArrayList;
import java.util.Locale;

public class PlayGame {

	private ArrayList<Player> players;
	private Cards cards;
	
//	Initialize the game, including the dealing and the first drop
	public PlayGame() {
//		setup Cards Info
		cards = new Cards();
//		setup players
		players = new ArrayList<Player>();
		for (int index = 0; index < 4; index++)
			players.add( new Player(index) );
//		dealing
		ArrayList<Integer> numList = getRandNum(54);
		for(int i = 0 ; i < 14 ; i++)	players.get(0).addCard( numList.get(i) );
		for(int i = 14 ; i < 28 ; i++)	players.get(1).addCard( numList.get(i) );
		for(int i = 28 ; i < 41 ; i++)	players.get(2).addCard( numList.get(i) );
		for(int i = 41 ; i < 54 ; i++)	players.get(3).addCard( numList.get(i) );
//		print deal message
		System.out.println("Deal cards");
		for(Player player : players) {
			System.out.print( String.format( Locale.getDefault(), "Player%d:", player.getIndex() ) );
			for(int card : player.getCardList() )	System.out.print( " " + cards.getCardString(card) );
			System.out.println();
		}
//		dropping
		for(Player player : players)	player.drop();
//		print drop message
		System.out.println("Drop cards");
		for(Player player : players) {
			System.out.print( String.format( Locale.getDefault(), "Player%d:", player.getIndex() ) );
			for(int card : player.getCardList() )	System.out.print( " " + cards.getCardString(card) );
			System.out.println();
		}
//		print start message
		System.out.println("Game start");
//		check if there's a player having no cards
		Player tmp0 = players.get(0), tmp1 = players.get(1);
		if( tmp0.isWinner() && tmp1.isWinner() )	System.out.println("Player0 and Player1 win");
		else if( tmp0.isWinner() )	System.out.println("Player0 wins");
		else if( tmp1.isWinner() )	System.out.println("Player1 wins");
		if( tmp0.isWinner() )	players.remove(tmp0);
		if( tmp1.isWinner() )	players.remove(tmp1);
		start();
	}

//	get an ArrayList containing "0 to _base" randomly without repeats
	private ArrayList<Integer> getRandNum(int _base) {
		int rand, num;
		ArrayList<Integer> output = new ArrayList<Integer>();
		for(int i = 0 ; i < _base ; i++)	output.add(i);
		for (int i = 0 ; i < _base ; i++) {
			rand = (int) (Math.random() * _base);
			num = output.get(i);
			output.set( i, output.get(rand) );
			output.set(rand, num);
		}	
		return output;
	}

//	game starts
	private void start() {
		int current = 0, flag = 0;
		while(true) {
//			check game over
			if( players.size() < 4 && flag == 0) {
				flag = 1;
				System.out.println("Basic game over\nContinue");
			}
			if( players.size() <= 1 )	break;
//			get a card from another player
			int randCard = players.get( (current + 1) % players.size() ).getRandCard();
//			print draw message
			System.out.println( String.format( "Player%d draws a card from Player%d %s", players.get(current).getIndex(), players.get( (current + 1) % players.size() ).getIndex(), cards.getCardString(randCard) ) );
//			drawing
			players.get(current).addCard(randCard);
			players.get( (current + 1) % players.size() ).removeCard(randCard);
			players.get(current).drop();
//			print draw result
			System.out.print( String.format( Locale.getDefault(), "Player%d:", players.get(current).getIndex() ) );
			for(int card : players.get(current).getCardList() )	System.out.print( " " + cards.getCardString(card) );
			System.out.println();
			System.out.print( String.format( Locale.getDefault(), "Player%d:", players.get( (current + 1) % players.size() ).getIndex() ) );
			for(int card : players.get( (current + 1) % players.size() ).getCardList() )	System.out.print( " " + cards.getCardString(card) );
			System.out.println();
//			check winner
			if( players.get(current).isWinner() && players.get( (current + 1) % players.size() ).isWinner() ) {
				if( players.get(current).getIndex() < players.get( (current + 1) % players.size() ).getIndex() )
					System.out.println( String.format(Locale.getDefault(), "Player%d and Player%d win", players.get(current).getIndex(), players.get( (current + 1) % players.size() ).getIndex() ) );
				else	System.out.println( String.format(Locale.getDefault(), "Player%d and Player%d win", players.get( (current + 1) % players.size() ).getIndex(), players.get(current).getIndex() ) );
				Player dead1 = players.get(current);
				Player dead2 = players.get( (current + 1) % players.size() );
				players.remove(dead1);
				players.remove(dead2);
				current %= players.size();
			}
			else if( players.get(current).isWinner() ) {
				System.out.println( String.format(Locale.getDefault(), "Player%d wins", players.get(current).getIndex() ) );
				players.remove(current);
				current %= players.size();
			}
			else if( players.get( (current + 1) % players.size() ).isWinner() ) {
				System.out.println( String.format( Locale.getDefault(), "Player%d wins", players.get( (current + 1) % players.size() ).getIndex() ) );
				players.remove( (current + 1) % players.size() );
				current = ( (current + 1) % (players.size() + 1) ) % players.size();
			}
			else	current = (current + 1) % players.size();
		}
//		print game over
		System.out.println("Bonus game over");
	}

	public static void main(String[] argv) {
		new PlayGame();
	}

}
