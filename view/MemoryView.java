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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.MemoryLogic;

/**
 *
 * @author senma
 */
public class MemoryView extends VBox {
    public MemoryView (MemoryLogic model){
        MemoryController controller = new MemoryController(model, this);
        
        GridPane mainView = initMainView();
        addEventHandlers(controller);
        MenuBar menuBar = createMenues(controller);
        this.getChildren().addAll(menuBar, mainView); // I am a VBox (this)

        updateFromModel();
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
                MenuItem RulesItem= new MenuItem("Show Rules");
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
			}
                });
                
                HighScoreItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
			public void handle(ActionEvent event) {
				System.out.println("Showing high scores");
			}
                });
                
                RulesItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
			public void handle(ActionEvent event) {
				System.out.println("Showing rules");
			}
                });
                
                
		QuitItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		Menu fileMenu = new Menu("File");
		fileMenu.getItems().addAll(NewGameItem,ResetItem,HighScoreItem,RulesItem,QuitItem);
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu);
		
		return menuBar;
    }
    
    

    private void updateFromModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
