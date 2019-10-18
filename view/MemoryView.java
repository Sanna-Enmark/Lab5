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
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Card;
import model.CardState;
import model.MemoryLogic;

/**
 *
 * @author Jesser&Sanna
 */
public class MemoryView extends VBox {

    private MemoryLogic model;

    private Alert alert;

    private Label currentPlayer;
    private MemoryButton[] theButtons;
    private GridPane mainView;

    public void setModel(MemoryLogic model) {
        this.model = model;
        this.getChildren().clear();

        this.alert = new Alert(Alert.AlertType.INFORMATION);
        MemoryController controller = new MemoryController(model, this);

        mainView = initMainView();

        this.currentPlayer = new Label(model.getActivePlayer().toString());
        mainView.add(currentPlayer, 0, 0);

        ImageView[] HiddenCardView = new ImageView[model.getGameSize()];
        initHiddenImageView(HiddenCardView);

        theButtons = new MemoryButton[model.getGameSize()];

        for (int i = 0; i < model.getGameSize(); i++) {
            Card temp = model.getCard(i);
            int CardValue = temp.getValue();
            CardState state = temp.getState();
            theButtons[i] = new MemoryButton("Card " + CardValue, HiddenCardView[i], i, CardValue, state);
            mainView.add(theButtons[i], 1 + i, 3);
        }

        addEventHandlers(controller);
        MenuBar menuBar = createMenues(controller);
        this.getChildren().addAll(menuBar, mainView);
    }

    public MemoryView(MemoryLogic model) {
        this.model = model;
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        MemoryController controller = new MemoryController(model, this);

        mainView = initMainView();

        this.currentPlayer = new Label(model.getActivePlayer().toString());
        mainView.add(currentPlayer, 0, 0);

        //displayAllHiddenCards();
        ImageView[] HiddenCardView = new ImageView[model.getGameSize()];
        initHiddenImageView(HiddenCardView);

        theButtons = new MemoryButton[model.getGameSize()];

        for (int i = 0; i < model.getGameSize(); i++) {
            Card temp = model.getCard(i);
            int CardValue = temp.getValue();
            CardState state = temp.getState();
            theButtons[i] = new MemoryButton("Card " + CardValue, HiddenCardView[i], i, CardValue, state);
            mainView.add(theButtons[i], 1 + i, 3);
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
                controller.handleCardSelectionEvent(event);

            }
        };
        for (Button i : theButtons) {
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
                controller.handeNewGameEvent();
            }
        });

        ResetItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Resets game");
                controller.handleResetEvent();
            }
        });

        HighScoreItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Showing high scores");
                controller.handleHighScoreEvent();

            }
        });

        RulesItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Showing rules");
                controller.handleRulesEvent();
            }
        });

        QuitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleQuitEvent();
            }
        });
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(NewGameItem, ResetItem, HighScoreItem, RulesItem, QuitItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);

        return menuBar;
    }

    void updateFromModel() {
        this.currentPlayer.setText(model.getActivePlayer().toString());//To change body of generated methods, choose Tools | Templates.
        if (model.checkForWinner() == true) {
            System.out.println(model.getWinner().toString());
            showAlert("Result", "The winner is " + model.getWinner().toString());
        }
    }

    public void showAlert(String title, String message) {
        alert.setHeaderText("Memory:");
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    void initHiddenImageView(ImageView[] HiddenCardView) {
        Image HiddenCardImage = new Image("file:HiddenCard.png");

        for (int i = 0; i < HiddenCardView.length; i++) {
            HiddenCardView[i] = new ImageView();
            HiddenCardView[i].setImage(HiddenCardImage);
            //mainView.add(HiddenCardView[i], 1 + i, 2);
        }

    }

    void displayRevealedCard(int index, int value) {
        Image[] CardImageNumber = new Image[model.getGameSize() / 2];
        CardImageNumber[0] = new Image("file:Card0.png");
        CardImageNumber[1] = new Image("file:Card1.png");
        CardImageNumber[2] = new Image("file:Card2.png");
        CardImageNumber[3] = new Image("file:Card3.png");

        ImageView[] CardImageNumberView = new ImageView[model.getGameSize() / 2];

        for (int i = 0; i < CardImageNumberView.length; i++) {
            CardImageNumberView[i] = new ImageView();
            CardImageNumberView[i].setImage(CardImageNumber[i]);
        }

        mainView.add(CardImageNumberView[value], index + 1, 2);
    }
    
    void displayCard(MemoryButton button) {
        mainView.getChildren().remove(theButtons[button.getIndex()]);
        mainView.add(theButtons[button.getIndex()], 1 + button.getIndex(), 3);
        
    }

}
