package game;

public class POOCasino {
	
	private Computer gameComputer;
	private Player gamePlayer;
	
	POOCasino() {
		gameComputer = new Computer();
		gamePlayer = new Player();
	}
	
	public void start() {
		gameComputer.printIntroMsg();
		gameComputer.askPlayerName();
		gamePlayer.setNameByStdin();
		gameComputer.printWelcomeMsg2Player(gamePlayer);
		while(true) {
			gameComputer.printPlayerMoney(gamePlayer);
			gameComputer.askBet();
			gamePlayer.setBetByStdin();
			if(gamePlayer.getBet() == 0)	break;
			gamePlayer.payMoney( gamePlayer.getBet() );
			gameComputer.giveCard2Player(gamePlayer);
			gameComputer.printPlayerCards(gamePlayer);
			gameComputer.askPlayer2KeepCards(gamePlayer);
			gameComputer.printNewPlayerCards(gamePlayer);
			gameComputer.notifyResult2Player(gamePlayer);
			gameComputer.addRound();
			gamePlayer.removeAllCards();
		}
		gameComputer.printByeMsg2Player(gamePlayer);
	}
	
	public static void main(String[] argv) {
		new POOCasino().start();
	}
	
}
