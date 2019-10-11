/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class to emulate a single game of memory
 * 
 * still lacks a method to check if there are any unmatched cards in the arraylist
 * 
 * this version has a method to check for unmatched cards, and uses it to know when to check for a winner.
 * Winner is then added to the highscore list
 *
 * @author senma
 */
public class MemoryLogic {
    private int gameSize;
    private CardType type;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private GameState state;
    private ArrayList<Player> highscore;
    private ArrayList<Card> theCards;
    private Card ChosenCard;
    

/**
 * Scans for gamesize, cardtype, and name of the players - all chosen by the user
 * throws illegalargumentexception if size is an odd number
 * generates an arraylist of cards with matching pairs (value!) and shuffles them in an arraylist.
     * @param size
     * @param type
     * @param player1
     * @param player2
 */
    
public MemoryLogic(int size, CardType type, String player1, String player2){
    if(size%2 == 0) this.gameSize=size;
        else throw new IllegalArgumentException("Size needs to be even");    
    this.type=type;
    theCards=new ArrayList<>(generateTheCards());
    this.player1=new Player(player1);
    this.player2=new Player(player2);
    this.state=GameState.ACTIVE;
    highscore= new ArrayList<>();
    resetGame();
}



private ArrayList<Card> generateTheCards() {
    ArrayList<Card> newCards=new ArrayList<>();
    for(int i=0; i<this.gameSize/2; i++){
        for(int j=0; j<2; j++) {
            Card a= new Card(j,this.type);
            newCards.add(a);
        }
    }
    return newCards;
}

/**
 * Resets the game with the same gamesize and cardtype
 * Sets points of both players to 0, sets all cards to hidden, and shuffles
 */

public void resetGame() {
    this.activePlayer=this.player1;
    player1.resetPoints();
    player2.resetPoints();
    ChosenCard=null;
    for(int i=0; i<theCards.size();i++){
        theCards.get(i).ChangeStateToHidden();
    }
    Collections.shuffle(theCards);
}

/**
 * Main logic of the game. Checks if chosencard is null, nothing happens if the state of the selected card isn't hidden
 * Increases active players points if chosencard isn't null and the card selected by index is of the same value, as well as sets the two cards state to matched
 * if the card selected by index does not have the same value chosencard and card selected by index is set to hidden, as well as changing active player
 */

public void chooseCard(int index) {
    if(this.ChosenCard==null && theCards.get(index).getState()==CardState.HIDDEN){
        ChosenCard=theCards.get(index);
        ChosenCard.ChangeStateToRevealed();
    }
    else if(theCards.get(index).getState()==CardState.HIDDEN) {
        if(match(theCards.get(index))) {
            ChosenCard.ChangeStateToMatched();
            theCards.get(index).ChangeStateToMatched();
            ChosenCard=null;
            if (activePlayer==player1) {
                player1.addPoint();
                
            }
            else {
                player2.addPoint();
                
            }
        }
        else {
            ChosenCard.ChangeStateToHidden();
            theCards.get(index).ChangeStateToHidden();
            ChosenCard=null;
            changeActivePlayer();
        }
    }
}

/*
Checks if we have a winner, and if we do, add it to highscore
If we have a winner state is set to inactive
Meant to be used after every instance of chooseCard
*/

public void checkForWinner() {
    if(checkForUnMatchedCards()==false){
                    highscore.add(getWinner());
                    sortAndTrimHighscoreList();
                    state=GameState.INACTIVE;
                }
}

private void changeActivePlayer() {
    if(activePlayer==player1) {
        activePlayer=player2;
    }
    else {
        activePlayer=player1;
    }
}

public boolean match (Card otherCard){
        return this.ChosenCard.equals(otherCard);
}

/*
Sorts the highscore list by the points of the Player using the compareTo method in Comparable interface
If there is more than 10 Player in the list the Player with the fewest points will be removed until there are 10 Player left.
*/

public void sortAndTrimHighscoreList() {
    Collections.sort(highscore);
    if(highscore.size()>10){
        for (int i=11; i<highscore.size(); i++) {
            highscore.remove(i);
        }
    }
}

public Player getWinner() {
    state=GameState.INACTIVE;
    if(player2.getPoints()>player1.getPoints()) {
        return player2;
    }
    else return player1;
}


@Override
public String toString() {
 
    String info = "Secret : ";
    return "test";
}


private boolean checkForUnMatchedCards() {
    int noOfUnmatchedCards=0;
    for (int i=0; i<theCards.size(); i++) {
        if(theCards.get(i).getState()!=CardState.MATCHED) {
            noOfUnmatchedCards=noOfUnmatchedCards+1;
        }
    }
        return noOfUnmatchedCards != 0;
}


}
