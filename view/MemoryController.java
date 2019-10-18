package view;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    void handleCardSelectionEvent(ActionEvent event) {
        MemoryButton temp = (MemoryButton) event.getSource();
        System.out.println("Card index " + temp.getIndex() + " " + "Card value " + temp.getValue());
        if(model.getChosenCard()==null){
            model.chooseCard1(temp.getIndex());
        }
        else{
            model.chooseCard2(temp.getIndex());  
            view.updateFromModel();
        }
        updateAllButtons();
        //Timer goes here
        model.hideRevealedCards();
        updateAllButtons();
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
        while(gamesize%2!=0){
         TextInputDialog size=new TextInputDialog("GameSize:");
        size.setHeaderText("Enter the number of cards you would like to play with:");
        size.showAndWait();
        gamesize=Integer.parseInt(size.getEditor().getText());   
        }        
        TextInputDialog p2=new TextInputDialog("Player 2");
        p2.setHeaderText("Enter the name of Player 2:");
        p2.showAndWait();
        String Player2=p2.getEditor().getText();
        TextInputDialog p1=new TextInputDialog("Player 1");
        p1.setHeaderText("Enter the name of Player 1:");
        p1.showAndWait();
        String Player1=p2.getEditor().getText();
        
        MemoryLogic newGame=new MemoryLogic(gamesize, CardType.NUMBER,Player1,Player2);
        this.model=newGame;
        view.setModel(newGame);
        
    }
    
    void handleHighScoreEvent(){
        view.showAlert("High Scores", model.getHighscore().toString());
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
