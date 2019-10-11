/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
