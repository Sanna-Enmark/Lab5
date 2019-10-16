/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.MemoryLogic;

/**
 *
 * @author senma
 */
public class MemoryView extends VBox {

    private MemoryLogic model;
    
    private final Alert alert;

    private Label currentPlayer;
    private MemoryButton[] theButtons;

    public MemoryView(MemoryLogic model) {
        this.model = model;
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        MemoryController controller = new MemoryController(model, this);
        
        GridPane mainView = initMainView();

        this.currentPlayer = new Label(model.getActivePlayer().toString());
        mainView.add(currentPlayer, 0,0);

        theButtons = new MemoryButton[model.getGameSize()];

        for (int i = 0; i < model.getGameSize(); i++) {
            theButtons[i] = new MemoryButton("Card " + model.getCard(i).getValue(),i);
            mainView.add(theButtons[i], 1+i, 2);
        }
        
        addEventHandlers(controller);
        MenuBar menuBar = createMenues(controller);
        this.getChildren().addAll(menuBar, mainView); // I am a VBox (this)

    }

    private GridPane initMainView() {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setVgap(8);
        gridPane.setHgap(5);

        return gridPane;
    }

    private void addEventHandlers(MemoryController controller) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        EventHandler<ActionEvent> CardSelection = new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
            MemoryButton temp = (MemoryButton)event.getSource();
            System.out.println("Card " + temp.getIndex());
            model.chooseCard(temp.getIndex());
            updateFromModel();
            
            }
        };
        for (Button i : theButtons){
            i.setOnAction(CardSelection);
        }
        
    }

    private MenuBar createMenues(MemoryController controller) {
        // File: Quit
        MenuItem NewGameItem = new MenuItem("Start new game");
        MenuItem ResetItem = new MenuItem("Reset");
        MenuItem HighScoreItem = new MenuItem("Show HighScore");
        MenuItem RulesItem = new MenuItem("Show Rules");
        MenuItem QuitItem = new MenuItem("Quit");

        NewGameItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Starting new game");
            }
        });

        ResetItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Resets game");
                model.resetGame();
                updateFromModel();
            }
        });

        HighScoreItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Showing high scores");
                showAlert("High Scores",model.getHighscore().toString());

            }
        });

        RulesItem.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Showing rules");
                showAlert("Rules:","Active player chooses two cards. \n If the cards matches, the active player gets a point and gets to choose two new cards. \n If the two cards do not match the other player gets a turn");
            }
        });
                
        QuitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(NewGameItem, ResetItem, HighScoreItem, RulesItem, QuitItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);

        return menuBar;
    }

    private void updateFromModel() {
        this.currentPlayer.setText(model.getActivePlayer().toString());//To change body of generated methods, choose Tools | Templates.
        if (model.checkForWinner() == true){
            System.out.println(model.getWinner().toString());
            showAlert("Result","The winner is " + model.getWinner().toString());
        }
    }

    private void showAlert(String title, String message) {
        
        // TODO - setParent ot similar, to set position relative
        // to main window
        
        alert.setHeaderText("Memory:");
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show(); 
    }
    
    private static class MemoryButton extends Button {
        private int index;
        
        public MemoryButton(String string, int i) {
            super(string);
            this.index = i;
        }
        
        public int getIndex (){
            return index;
        }

    }
}
