
package Lab5.model;
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
    			case 'B':	doThat(); break;
    			case 'X':	System.out.println("Bye, bye!"); break;
    			default: 	System.out.println("Unknown command");
    		}
    		
    	} while(choice != 'X');
    }

    public void startNewGame() {
    	System.out.println(game.toString());
        
        
        
    }

    public void doThat() {
    	System.out.println("Doing that...");
    }
    
    public void printMenu() {
    	System.out.println("---Menu---");
    	System.out.println("A Do This");
    	System.out.println("B Do That");
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