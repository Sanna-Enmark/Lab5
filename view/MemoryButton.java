
package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
        private ImageView view;
        
        public MemoryButton(String string,ImageView view0, int index0, int value0, CardState state0) {
            super(string, view0);            
            this.state = state0;
            this.index = index0;
            this.value = value0; 
            this.view = view0;
        }
        
        public void updateButtonView(){
            
            if(state == CardState.HIDDEN){
                Image Image0= new Image("file:HiddenCard.png");
                this.view.setImage(Image0);
                super.setGraphic(view);
            }
            
            else if (state != CardState.HIDDEN && this.value == 0){
                Image Image0= new Image("file:Card0.png");
                this.view.setImage(Image0);
                super.setGraphic(view);
            }
            
            else if (state != CardState.HIDDEN && this.value == 1){
                Image Image0= new Image("file:Card1.png");
                this.view.setImage(Image0);
                super.setGraphic(view);
            }
            
            else if (state != CardState.HIDDEN && this.value == 2){
                Image Image0= new Image("file:Card2.png");
                this.view.setImage(Image0);
                super.setGraphic(view);
            }
            
            else if (state != CardState.HIDDEN && this.value == 3){
                Image Image0= new Image("file:Card3.png");
                this.view.setImage(Image0);
                super.setGraphic(view);
            }
            
            else {
                
            }
            
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
        
        public void setState (CardState state1){
            this.state = state1;
        }

    }
