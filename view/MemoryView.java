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

    private static MemoryLogic model;
    
    private final Alert alert;

    private static Label currentPlayer;
    private Button[] theButtons;

    public MemoryView(MemoryLogic model) {
        this.model = model;
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        MemoryController controller = new MemoryController(model, this);

        GridPane mainView = initMainView();

        this.currentPlayer = new Label(model.getActivePlayer().toString());
        mainView.add(currentPlayer, 1, 0);

        theButtons = new Button[model.getGameSize()];

        for (int i = 0; i < model.getGameSize(); i++) {
            theButtons[i] = new Button("Card" + i);
            theButtons[i].setOnAction(new ButtonHandler(i, model));
            mainView.add(theButtons[i], 2, i);
        }

        MenuBar menuBar = createMenues(controller);
        this.getChildren().addAll(menuBar, mainView); // I am a VBox (this)

    }

    private GridPane initMainView() {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        return gridPane;
    }

    private void addEventHandlers(MemoryController controller) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                showAlert(model.getHighscore().toString());

            }
        });

        RulesItem.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Showing rules");
                showAlert("Active player chooses two cards. \n If the cards matches, the active player gets a point and gets to choose two new cards. \n If the two cards do not match the other player gets a turn");
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

    public static void updateFromModel() {
        MemoryView.currentPlayer.setText(model.getActivePlayer().toString());//To change body of generated methods, choose Tools | Templates.
    }

    private void showAlert(String message) {
        
        // TODO - setParent ot similar, to set position relative
        // to main window
        
        alert.setHeaderText("Memory:");
        alert.setTitle("Rules:");
        alert.setContentText(message);
        alert.show(); 
    }
    
    private static class ButtonHandler implements EventHandler<ActionEvent> {

        private int index;

        public ButtonHandler(int i, MemoryLogic model) {
            this.index = i;
            

        }

        @Override
        public void handle(ActionEvent event) {
            System.out.println("Pressed button" + index);
            model.chooseCard(index);
            updateFromModel();
        }
    }
}
