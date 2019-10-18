
package view;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import model.CardState;

/**
 *
 * @author Jesser
 */
   public class MemoryButton extends Button {
        private int index;
        private int value; 
        private CardState state;
        
        public MemoryButton(String string,ImageView view, int index0, int value0, CardState state0) {
            super(string, view);
            this.state = state0;
            this.index = index0;
            this.value = value0; 
        }
        
        public int getIndex (){
            return index;
        }
        
        public int getValue (){
            return value;
        }
        public CardState getState (){
            return state;
        }

    }
