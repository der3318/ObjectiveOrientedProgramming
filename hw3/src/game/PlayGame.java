package game;

public class PlayGame {

	public static void main(String[] argv) {
//		demo games
		System.out.println("Origin Version");
		new OldMaid().start();
		System.out.println("Variant One");
		new VariantOne().start();
		System.out.println("Variant Two");
		new VariantTwo().start();
	}
	
}
