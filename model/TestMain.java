
package model;
import java.util.Scanner;

public class TestMain {

    private MemoryLogic game;

    public TestMain() {
    	game = new MemoryLogic(8, CardType.NUMBER, "Jesser", "Sanna" );
    }
    
    // main loop
    public void run() {
    	char choice = ' ';
    	String answer;
    	
    	do {
    		printMenu();
    		answer = scanLine();
    		answer = answer.toUpperCase();
    		choice = answer.charAt(0); // first character
    		
    		switch(choice) {
    			case 'A':	startNewGame(); break;
    			case 'B':	getActivePlayer(); break;
                        case 'C':       chooseCards(); break;
    			case 'X':	System.out.println("Bye, bye!"); break;
    			default: 	System.out.println("Unknown command");
    		}
    		
    	} while(choice != 'X');
    }

    public void startNewGame() {
        System.out.println(game.toString());
    }
    
    public void chooseCards() {
        System.out.println("Enter the number of the card you would like to check (1-"+game.getGameSize()+"): ");
        int choice=Integer.parseInt(scanLine())-1;
        game.chooseCard(choice);
        System.out.println(game.getChosenCard().toString());
    }

    public void getActivePlayer() {
    	System.out.println(game.getActivePlayer().toString());
    }
    
    public void printMenu() {
    	System.out.println("---Menu---");
        System.out.println("Active player is: " + game.getActivePlayer().toString());
    	System.out.println("A Display all game info");
    	System.out.println("B Display active player");
        System.out.println("C Choose a card");
    	System.out.println("X Exit");
    	System.out.println("----------");
    }
    
    private String scanLine() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
    
    public static void main(String[] args) {
    	TestMain menu = new TestMain();
    	menu.run();
    }
}