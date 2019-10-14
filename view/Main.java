
package view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CardType;
import model.MemoryLogic;

/**
 *
 * @author Jesser
 */
/**
 * Creates a window and ties model, view and controller together.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // create model, view and controller, and tie them together
        MemoryLogic model = new MemoryLogic(8, CardType.NUMBER, "Jesser", "Sanna");
        MemoryView view = new MemoryView(model); // also creates the controller

        // create the window, add the view, and show it
        Scene scene = new Scene(view);
        primaryStage.setTitle("Memory");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
