package game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import foop.Player;

public class POOCasino {
	
	public static void main(String[] args) {
		Player[] players = new Player[4];
		if(args.length < 6) {
			System.out.println("Wrong Args Format");
			System.out.println("Usage: java POOCasino nRound nChip Player1 Player2 Player3 Player4");
			return;
		}
		System.out.println("Loading Player: " + args[2] + " " + args[3] + " " + args[4] + " " + args[5] );
		try {
			Class<?> cls1 = Class.forName( args[2] );
			Constructor<?> cons1 = cls1.getConstructor(int.class);
			players[0] = (Player)cons1.newInstance( Integer.parseInt(args[1]) );
			Class<?> cls2 = Class.forName( args[3] );
			Constructor<?> cons2 = cls2.getConstructor(int.class);
			players[1] = (Player)cons2.newInstance( Integer.parseInt(args[1]) );
			Class<?> cls3 = Class.forName( args[4] );
			Constructor<?> cons3 = cls3.getConstructor(int.class);
			players[2] = (Player)cons3.newInstance( Integer.parseInt(args[1]) );
			Class<?> cls4 = Class.forName( args[5] );
			Constructor<?> cons4 = cls4.getConstructor(int.class);
			players[3] = (Player)cons4.newInstance( Integer.parseInt(args[1]) );
		} catch(InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
			System.out.println("Fialed to Load Class from Args");
			e.printStackTrace();
			return;
		}
		new Blackjack(Integer.parseInt(args[0]), players).start();
	}
	
}
