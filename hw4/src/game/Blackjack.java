package game;

import java.util.ArrayList;

import foop.Card;
import foop.Hand;
import foop.Player;
import foop.Player.BrokeException;
import foop.Player.NegativeException;

public class Blackjack {

//	an ArrayList of players
	private ArrayList<Player> players;
//	number of rounds to play
	private int nRound;
//	the cards of each player
	private ArrayList<CardSet> cardSets, splitSets;
//	bet for each players
	private ArrayList<Integer> bets, splitBets;
//	insurance and surrender for each players
	private ArrayList<Boolean> insur, surr, split, mainDouble, splitDouble;
//	card deck used in the game
	private CardDeck cardDeck;
	
//	initialize the game
	public Blackjack(int _nRound, Player[] _players) {
		players = new ArrayList<Player>();
		bets = new ArrayList<Integer>();
		splitBets = new ArrayList<Integer>();
		insur = new ArrayList<Boolean>();
		surr = new ArrayList<Boolean>();
		split = new ArrayList<Boolean>();
		mainDouble = new ArrayList<Boolean>();
		splitDouble = new ArrayList<Boolean>();
		cardSets = new ArrayList<CardSet>();
		splitSets = new ArrayList<CardSet>();
		cardDeck = new CardDeck();
//		players' bets, insurances, surrenders and cardSets
		for(Player p : _players) {
			players.add(p);
			bets.add(0);
			splitBets.add(0);
			insur.add(false);
			surr.add(false);
			split.add(false);
			mainDouble.add(false);
			splitDouble.add(false);
			cardSets.add( new CardSet() );
			splitSets.add( new CardSet() );
		}
//		dealer's cardSet
		cardSets.add( new CardSet() );
		nRound = _nRound;
	}

//	game start
	public void start() {
		int curRound = 1;
		while(curRound <= nRound) {
			System.out.println("<System> Round " + curRound + " Start!");
//			make bet
			ArrayList<Hand> lastTable = new ArrayList<Hand>();
			for(CardSet cs : cardSets)	lastTable.add( new Hand( cs.getCards() ) );
			for(CardSet ss : splitSets)
				if(ss.getValue() > 0)	lastTable.add( new Hand( ss.getCards() ) );
			for(int i = 0 ; i < players.size() ; i++) {
				bets.set( i, players.get(i).make_bet(lastTable, players.size(), i) );
				try {
					players.get(i).decrease_chips( bets.get(i) );
				} catch(NegativeException | BrokeException e) {
					System.out.println("Exception Event on Player");
					e.printStackTrace();
				}
			}
			Printer.showBets(bets);
//			clean the status
			for(int i = 0 ; i < players.size() ; i++) {
				splitBets.set(i, 0);
				insur.set(i, false);
				surr.set(i, false);
				split.set(i, false);
				mainDouble.set(i, false);
				splitDouble.set(i, false);
				splitSets.get(i).clear();
			}
//			clean the cardSets and give out a new open card
			for(CardSet cs : cardSets) {
				cs.clear();
				cs.addCard( cardDeck.getRandCard() );
			}
			System.out.println("<System> Face-Up Cards Assigned");
			System.out.print("Dealer's  Hand: ");
			Printer.showCards( cardSets.get( players.size() ) );
			for(int i = 0 ; i < players.size() ; i++) {
				System.out.print("Player" + (i + 1) + "'s Hand: ");
				Printer.showCards( cardSets.get(i) );
			}
//			check if the dealer's face-up card is ACE
			Card dealerOpen = cardSets.get( players.size() ).getCard(0);
			if(dealerOpen.getValue() == Card.VALUE_LOWER)
				for(int i = 0 ; i < players.size() ; i++) {
					ArrayList<Hand> curTable = getCurTable(i);
					insur.set( i, players.get(i).buy_insurance(cardSets.get(i).getCard(0), dealerOpen, curTable) );
					if(insur.get(i) == true)
						try {
							System.out.println("<System> Player" + (i + 1) + " Buys An Insurance");
							players.get(i).decrease_chips(bets.get(i) / 2);
						} catch(NegativeException | BrokeException e) {
							System.out.println("Exception Event on Player");
							e.printStackTrace();
						}
				}
//			give out another face-down card
			for(CardSet cs : cardSets)	cs.addCard( cardDeck.getRandCard() );
//			check if the dealer has Blackjack
			if( cardSets.get( players.size() ).getValue() != 21)
				for(int i = 0 ; i < players.size() ; i++) {
					ArrayList<Hand> curTable = new ArrayList<Hand>();
					for(int j = 0 ; j < players.size(); j++)
						if(i != j) {
							ArrayList<Card> tmp = new ArrayList<Card>();
							tmp.add( cardSets.get(j).getCard(0) );
							curTable.add( new Hand(tmp) );
						}
					surr.set( i, players.get(i).do_surrender(cardSets.get(i).getCard(0), dealerOpen, curTable) );
					if(surr.get(i) == true)	System.out.println("<System> Player" + (i + 1) + " Surrenders");
				}
//			for those who did not surrender
			for(int i = 0 ; i < players.size() ; i++) {
				if(surr.get(i) == true)	continue;
				ArrayList<Hand> curTable = getCurTable(i);
				if( cardSets.get(i).getCard(0).getValue() == cardSets.get(i).getCard(1).getValue() )
					split.set( i, players.get(i).do_split(cardSets.get(i).getCards(), dealerOpen, curTable) );
			}
//			for those who split
			for(int i = 0 ; i < players.size() ; i++) {
				if(split.get(i) == false)	continue;
				System.out.println("<System> Player" + (i + 1) + " Splits");
				splitBets.set( i, bets.get(i) );
				try {
					players.get(i).decrease_chips( splitBets.get(i) );
				} catch(NegativeException | BrokeException e) {
					System.out.println("Exception Event on Player");
					e.printStackTrace();
				}
				Card tmp1 = cardSets.get(i).getCard(0), tmp2 = cardSets.get(i).getCard(1);
				cardSets.get(i).clear();
				cardSets.get(i).addCard(tmp1);
				splitSets.get(i).addCard(tmp2);
			}
//			ask if the players want to double down
			for(int i = 0 ; i < players.size(); i++) {
				if(surr.get(i) == true)	continue;
				ArrayList<Hand> curTable = getCurTable(i);
				if(split.get(i) == true)	curTable.add( new Hand( splitSets.get(i).getCards() ) );
				if( players.get(i).do_double(new Hand( cardSets.get(i).getCards() ), dealerOpen, curTable) ) {
					mainDouble.set(i, true);
					cardSets.get(i).addCard( cardDeck.getRandCard() );
					try {
						System.out.println("<System> Player" + (i + 1) + " Doubles Down");
						players.get(i).decrease_chips( bets.get(i) );
					} catch(NegativeException | BrokeException e) {
						System.out.println("Exception Event on Player");
						e.printStackTrace();
					}
					bets.set(i, bets.get(i) * 2);
				}
				if(split.get(i) == false)	continue;
				curTable = getCurTable(i);
				curTable.add( new Hand( cardSets.get(i).getCards() ) );
				if( players.get(i).do_double(new Hand( splitSets.get(i).getCards() ), dealerOpen, curTable) ) {
					splitDouble.set(i, false);
					splitSets.get(i).addCard( cardDeck.getRandCard() );
					try {
						System.out.println("<System> Player" + (i + 1) + " Doubles Down on Newly-Splited Hand");
						players.get(i).decrease_chips( splitBets.get(i) );
					} catch(NegativeException | BrokeException e) {
						System.out.println("Exception Event on Player");
						e.printStackTrace();
					}
					splitBets.set(i, splitBets.get(i) * 2);
				}
			}
//			show status
			System.out.println("<System> Current Hands");
			for(int i = 0 ; i < players.size() ; i++) {
				if(surr.get(i) == true)	continue;
				if(insur.get(i) == true)	System.out.println("Player" + (i + 1) + " has Insurance");
				System.out.println( "Player" + (i + 1) + "'s Bet: " + bets.get(i) );
				System.out.print("Player" + (i + 1) + "'s Hand: ");
				Printer.showCards( cardSets.get(i) );
				if(split.get(i) == false)	continue;
				System.out.println( "Player" + (i + 1) + "'s Bet on Newly-Splited Hand: " + splitBets.get(i) );
				System.out.print("Player" + (i + 1) + "'s Newly-Splited Hand: ");
				Printer.showCards( splitSets.get(i) );
			}
//			ask if they want to hit
			for(int i = 0 ; i < players.size() ; i++) {
				if(surr.get(i) == true)	continue;
				ArrayList<Hand> curTable = getCurTable(i);
				if(split.get(i) == true)	curTable.add( new Hand( splitSets.get(i).getCards() ) );
				while(true) {
					if(cardSets.get(i).getValue() > 21 || mainDouble.get(i) == true)	break;
					if( players.get(i).hit_me(new Hand( cardSets.get(i).getCards() ), dealerOpen, curTable) )	cardSets.get(i).addCard( cardDeck.getRandCard() );
					else	break;
				}
				if(split.get(i) == false)	continue;
				curTable = getCurTable(i);
				curTable.add( new Hand( cardSets.get(i).getCards() ) );
				while(true) {
					if(splitSets.get(i).getValue() > 21 || splitDouble.get(i) == true)	break;
					if( players.get(i).hit_me(new Hand( splitSets.get(i).getCards() ), dealerOpen, curTable) )	splitSets.get(i).addCard( cardDeck.getRandCard() );
					else	break;
				}
			}
//			dealer's turn
			CardSet dealerSet = cardSets.get( players.size() );
			while(dealerSet.getValue() < 17)	dealerSet.addCard( cardDeck.getRandCard() );
//			show result status
			System.out.println("<System> Result Hands");
			System.out.print("Dealer's  Hand: ");
			Printer.showCards( cardSets.get( players.size() ) );
			System.out.println( "Dealer's Total Value: " + cardSets.get( players.size() ).getValue() );
			for(int i = 0 ; i < players.size() ; i++) {
				if(surr.get(i) == true)	continue;
				System.out.print("Player" + (i + 1) + "'s Hand: ");
				Printer.showCards( cardSets.get(i) );
				System.out.println( "Player" + (i + 1) + "'s Total Value: " + cardSets.get(i).getValue() );
				if(split.get(i) == false)	continue;
				System.out.print("Player" + (i + 1) + "'s Newly-Splited Hand: ");
				Printer.showCards( splitSets.get(i) );
				System.out.println( "Player" + (i + 1) + "'s Total Value of Splited Hand: " + cardSets.get(i).getValue() );
			}
//			check result
			for(int i = 0 ; i < players.size() ; i++) {
				int delta = 0;
				if(surr.get(i) == true)	delta += bets.get(i) / 2;
				else if(cardSets.get(i).getValue() > 21)	delta += 0;
				else if(cardSets.get(i).getValue() == 21) {
					if(dealerSet.getValue() != 21)	delta += (bets.get(i) / 2 + bets.get(i) * 2);
					else	delta += bets.get(i);
				}
				else if(dealerSet.getValue() > 21)	delta += (bets.get(i) * 2);
				else if(dealerSet.getValue() == 21) {
					if(insur.get(i) == true)	delta += bets.get(i);
					else	delta += 0;
				}
				else {
					if( dealerSet.getValue() > cardSets.get(i).getValue() )	delta += 0;
					else if( dealerSet.getValue() == cardSets.get(i).getValue() )	delta += bets.get(i);
					else if( dealerSet.getValue() < cardSets.get(i).getValue() )	delta += (bets.get(i) * 2);
				}
				if(split.get(i) == true) {
					if(splitSets.get(i).getValue() > 21)	delta += 0;
					else if(splitSets.get(i).getValue() == 21) {
						if(dealerSet.getValue() == 21)	delta += (splitBets.get(i) / 2 + splitBets.get(i) * 2);
						else	delta += splitBets.get(i);
					}
					else if(dealerSet.getValue() > 21)	delta += (splitBets.get(i) * 2);
					else if(dealerSet.getValue() == 21) {
						if(insur.get(i) == true)	delta += splitBets.get(i);
						else	delta += 0;
					}
					else {
						if( dealerSet.getValue() > splitSets.get(i).getValue() )	delta += 0;
						else if( dealerSet.getValue() == splitSets.get(i).getValue() )	delta += splitBets.get(i);
						else if( dealerSet.getValue() < splitSets.get(i).getValue() )	delta += (splitBets.get(i) * 2);
					}
				}
				try {
					players.get(i).increase_chips(delta);
				} catch(NegativeException e) {
					System.out.println("Exception Event on Player");
					e.printStackTrace();
				}
			}
//			show chips
			for(Player p : players) System.out.println(p);
//			next round
			curRound++;
		}
	}

//	get the current table(Hands of other players except _player)
	private ArrayList<Hand> getCurTable(int _player) {
		ArrayList<Hand> curTable = new ArrayList<Hand>();
		for(int i = 0 ; i < players.size(); i++)
			if(i != _player) {
				curTable.add( new Hand( cardSets.get(i).getCards() ) );
				if(splitSets.get(i).getValue() > 0)	curTable.add( new Hand( splitSets.get(i).getCards() ) );
			}
		return curTable;
	}
	
}
