package view;

import javafx.event.ActionEvent;
import model.CardState;
import model.MemoryLogic;

/**
 *
 * @author Jesser
 */
public class MemoryController {

    private final MemoryLogic model;
    private final MemoryView view;

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

    void handleResetEvent() {
        model.resetGame();
        this.view.updateFromModel();
    }

}
