package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import model.CardState;
import model.CardType;
import model.MemoryLogic;

/**
 *
 * @author Jesser
 */
public class MemoryController {

    private  MemoryLogic model;
    private  MemoryView view;

    public MemoryController(MemoryLogic model, MemoryView view) {
        this.model = model;
        this.view = view;
    }

    void handleCardSelectionEvent(ActionEvent event) {
        MemoryButton temp = (MemoryButton) event.getSource();
        System.out.println("Card index " + temp.getIndex() + " " + "Card value " + temp.getValue());
        model.chooseCard(temp.getIndex());
        temp.updateButtonView();
        this.view.displayCard(temp);
        this.view.updateFromModel();
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
        String Player2=new String(p2.getEditor().getText());
        TextInputDialog p1=new TextInputDialog("Player 1");
        p1.setHeaderText("Enter the name of Player 1:");
        p1.showAndWait();
        String Player1=new String(p2.getEditor().getText());
        
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
        Platform.exit();
    }

    void handleResetEvent() {
        model.resetGame();
        this.view.updateFromModel();
    }

}
