package view;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import model.CardType;
import model.MemoryIO;
import model.MemoryLogic;
import model.Player;

/**
 *
 * @author Jesser
 */
public class MemoryController {

    private  MemoryLogic model;
    private  MemoryView view;
    private MemoryIO IO;
    private TimeUnit timeUnit;
    
    

    public MemoryController(MemoryLogic model, MemoryView view)  {
        this.model = model;
        this.view = view;
        this.IO=new MemoryIO();        
        loadFromFile();
    }
    
    private void loadFromFile(){
        if (fileExists()) {
            try {
                model.setHighScore(IO.deSerializeFromFile("HighScore"));
            } catch (Exception ex) {
                System.out.println("An exception was found");
            }
            System.out.println("Loading successful");
        }
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

    void handleCardSelectionEvent(ActionEvent event) throws InterruptedException {
        MemoryButton temp = (MemoryButton) event.getSource();
        System.out.println("Card index " + temp.getIndex() + " " + "Card value " + temp.getValue());
        if(model.getChosenCard()==null){
            model.chooseCard1(temp.getIndex());
            updateAllButtons();
            this.view.updateFromModel();
        }
        else{
            model.chooseCard2(temp.getIndex());
            updateAllButtons();          
            this.view.updateFromModel();
            //timeUnit.SECONDS.sleep(1);  
            
        }
        model.hideRevealedCards();
        //updateAllButtons();
        this.view.updateFromModel();
    }
    
    private void updateAllButtons(){
        for(int i=0; i<view.getTheButtons().length;i++){
            view.getTheButtons()[i].setState(model.getCard(i).getState());
            view.getTheButtons()[i].updateButtonView();
            this.view.displayCard(view.getTheButtons()[i]);
        }
    }
    
    
    
    void handeNewGameEvent(){
        int gamesize=1;
        while(gamesize%2!=0||gamesize>10 ){
         TextInputDialog size=new TextInputDialog("GameSize:");
        size.setHeaderText("Enter the number of cards you would like to play with (exclusively 8 or 10):");
        size.showAndWait();
        gamesize=Integer.parseInt(size.getEditor().getText());   
        }        
        TextInputDialog p1=new TextInputDialog("Player 1");
        p1.setHeaderText("Enter the name of Player 1:");
        p1.showAndWait();
        String Player1=p1.getEditor().getText();
        TextInputDialog p2=new TextInputDialog("Player 2");
        p2.setHeaderText("Enter the name of Player 2:");
        p2.showAndWait();
        String Player2=p2.getEditor().getText();       
        
        MemoryLogic newGame=new MemoryLogic(gamesize, CardType.NUMBER,Player1,Player2);
        this.model=newGame;
        view.setModel(newGame);
        view.updateFromModel();
        
    }
    
    void handleHighScoreEvent(){
        String highscoreString=new String();
        for(int i=0; i<model.getHighscore().size();i++){
            highscoreString=highscoreString+model.getHighscore().get(i).toString()+"\n";
        }
        view.showAlert("High Scores", highscoreString);
    }
    
    void handleRulesEvent(){
        view.showAlert("Rules:", "Active player chooses two cards. \n If the cards matches, the active player gets a point and gets to choose two new cards. \n If the two cards do not match the other player gets a turn");

    }
    
    void handleQuitEvent(){
        saveToFile();
        Platform.exit();
    }
    
    private void saveToFile() {
        try {
            IO.serializeToFile("HighScore", model.getHighscore());
        } catch (Exception ex) {
            System.out.println("An exception was found");
            
        }
        System.out.println("Saving complete");

    }

    void handleResetEvent() {
        model.resetGame();
        view.setModel(model);
    }

}
