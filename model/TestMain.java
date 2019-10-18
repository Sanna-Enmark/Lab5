
package model;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TestMain {

    private MemoryLogic game;
    private MemoryIO IO;

    public TestMain() throws Exception {
    	game = new MemoryLogic(8, CardType.NUMBER, "Jesser", "Sanna" );
        IO= new MemoryIO();
        if (fileExists()) {
            try {
                game.setHighScore(IO.deSerializeFromFile("HighScore"));
            } catch (Exception ex) {
                System.out.println("An exception was found");
                throw ex;
            }
            System.out.println("Loading successful");
        }
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
    			case 'X':	saveAndQuit(); break;
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
        if(game.checkIfHidden(game.getCard(choice))){
            System.out.println(game.getCard(choice).toString());
        }
        game.chooseCard1(choice);
        System.out.println("Enter the number of the card you would like to check (1-"+game.getGameSize()+"): ");
        choice=Integer.parseInt(scanLine())-1;
        if(game.checkIfHidden(game.getCard(choice))){
            System.out.println(game.getCard(choice).toString());
        }
        game.chooseCard2(choice);
        game.hideRevealedCards();
        game.checkForWinner();
        if(game.getGameState()==GameState.INACTIVE){
            System.out.println(game.getWinner().toString()+ " won!");
        }
       
    }
    
    public void saveAndQuit(){
        saveToFile();
        System.out.println("Bye, bye!");
    }
    
    public void saveToFile() {
        try {
            IO.serializeToFile("HighScore", game.getHighscore());
        } catch (Exception ex) {
            System.out.println("An exception was found");
            
        }
        System.out.println("Saving complete");

    }
    
    private static boolean fileExists() {
        Path filePath = Paths.get("C:\\Users\\senma\\Documents\\NetBeansProjects\\Lab5\\HighScore.txt");
        if (Files.exists(filePath, new LinkOption[0])) {
            return true;
        } else {
            System.out.println("File doesn't exist. A new file will be created");
            return false;
        }
    }

    public void getActivePlayer() {
    	System.out.println(game.getActivePlayer().toString());
    }
    
    public void printMenu() {
    	System.out.println("---Menu---");
        System.out.println("Active player is: " + game.getActivePlayer().toString());
        if(game.getChosenCard() != null) {
            System.out.println(game.getChosenCard().toString());
        }
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
    
    public static void main(String[] args) throws Exception {
    	TestMain menu = new TestMain();
        
    	menu.run();
    }
}