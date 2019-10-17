package view;

import javafx.event.ActionEvent;
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
        System.out.println("Card " + temp.getIndex());
        model.chooseCard(temp.getIndex());
        this.view.updateFromModel();
    }

    void handleResetEvent() {
        model.resetGame();
        this.view.updateFromModel();
    }

}
