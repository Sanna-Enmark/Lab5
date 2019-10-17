
package view;

import javafx.scene.control.Button;

/**
 *
 * @author Jesser
 */
   public class MemoryButton extends Button {
        private int index;
        private int value; 
        
        public MemoryButton(String string, int index0, int value0) {
            super(string);
            this.index = index0;
            this.value = value0; 
        }
        
        public int getIndex (){
            return index;
        }
        
        public int getValue (){
            return value;
        }
        

    }
