
package view;

import javafx.scene.control.Button;

/**
 *
 * @author Jesser
 */
   public class MemoryButton extends Button {
        private int index;
        
        public MemoryButton(String string, int i) {
            super(string);
            this.index = i;
        }
        
        public int getIndex (){
            return index;
        }

    }
