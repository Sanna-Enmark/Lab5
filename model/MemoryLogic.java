package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class to emulate a single game of memory
 *
 * still lacks a method to check if there are any unmatched cards in the
 * arraylist
 *
 * this version has a method to check for unmatched cards, and uses it to know
 * when to check for a winner. Winner is then added to the highscore list
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
     * Scans for gamesize, cardtype, and name of the players - all chosen by the
     * user throws illegalargumentexception if size is an odd number generates
     * an arraylist of cards with matching pairs (value!) and shuffles them in
     * an arraylist.
     *
     * @param size
     * @param type
     * @param player1
     * @param player2
     */
    public MemoryLogic(int size, CardType type, String player1, String player2) {
        if (size % 2 == 0) {
            this.gameSize = size;
        } else {
            throw new IllegalArgumentException("Size needs to be even");
        }
        this.type = type;
        theCards = new ArrayList<>(generateTheCards());
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        this.state = GameState.ACTIVE;
        highscore = new ArrayList<>();
        resetGame();
    }

    public void setHighScore(ArrayList<Player> fromfile) {
        this.highscore = fromfile;
    }

    private ArrayList<Card> generateTheCards() {
        ArrayList<Card> newCards = new ArrayList<>();
        for (int i = 0; i < this.gameSize / 2; i++) {
            for (int j = 0; j < 2; j++) {
                Card a = new Card(i, this.type);
                newCards.add(a);
            }
        }
        return newCards;
    }

    /**
     * Resets the game with the same gamesize and cardtype Sets points of both
     * players to 0, sets all cards to hidden, and shuffles
     */
    public void resetGame() {
        this.activePlayer = this.player1;
        player1.resetPoints();
        player2.resetPoints();
        ChosenCard = null;
        for (int i = 0; i < theCards.size(); i++) {
            theCards.get(i).ChangeStateToHidden();
        }
        Collections.shuffle(theCards);
    }

    /**
     * The first card a player chooses. ChosenCard is set to the card for comparison in chooseCard2
     * @param index
     */
    

    public void chooseCard1(int index) {
        if (this.theCards.get(index).getState().equals(CardState.HIDDEN)) {            
            theCards.get(index).ChangeStateToRevealed();
            this.ChosenCard = theCards.get(index);
        }
    }
    
    /**
     * Second card chosen. If matched, the active player gains a point, and the card state is changed to matched.
     * If not matched, the second card is set to revealed and the active player is changed
     * @param index 
     */

    public void chooseCard2(int index) {
        if (this.theCards.get(index).getState().equals(CardState.HIDDEN)) {
            if (match(theCards.get(index))) {
                
                ChosenCard.ChangeStateToMatched();
                theCards.get(index).ChangeStateToMatched();
                ChosenCard = null;

                if (activePlayer == player1) {
                    player1.addPoint();

                } else {
                    player2.addPoint();
                }
            } else {
                System.out.println("Changing active player");
                theCards.get(index).ChangeStateToRevealed();
                ChosenCard = null;
                changeActivePlayer(); 
            }
        }
    }
    
    /**
     * Meant to be called after chooseCard2. Scans for card with Cardstate revlealed and sets it to hidden.
     */

    public void hideRevealedCards() {
        int noOfRevealedCards=0;
        for (int i = 0; i < this.gameSize; i++) {
            if (theCards.get(i).getState() == CardState.REVEALED) {
                noOfRevealedCards=noOfRevealedCards+1;
            }
        }
        if(noOfRevealedCards>1){
            for (int i = 0; i < this.gameSize; i++) {
            if (theCards.get(i).getState() == CardState.REVEALED) {
                theCards.get(i).ChangeStateToHidden();
            }
        }
        }
        
    }

    /*
Checks if we have a winner, and if we do, add it to highscore
If we have a winner state is set to inactive
Meant to be used after every instance of chooseCard
     */
    public Boolean checkForWinner() {
        if (checkForUnMatchedCards() == false) {
            highscore.add(getWinner());
            sortAndTrimHighscoreList();
            state = GameState.INACTIVE;
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Player> getHighscore() {
        return highscore;
    }

    private void changeActivePlayer() {
        if (activePlayer == player1) {
            activePlayer = player2;
        } else {
            activePlayer = player1;
        }
    }

    public Card getCard(int index) {
        return theCards.get(index);
    }

    public boolean match(Card otherCard) {
        return ChosenCard.compareTo(otherCard) == 0;
    }

    public boolean checkIfHidden(Card c) {
        return c.getState() == CardState.HIDDEN;
    }

    /**
     * Sorts the highscore list by the points of the Player using the compareTo
     * method in Comparable interface If there is more than 10 Player in the
     * list the Player with the fewest points will be removed until there are 10
     * Player left.
     */
    public void sortAndTrimHighscoreList() {
        Collections.sort(highscore);
        if (highscore.size() > 10) {
            for (int i = 11; i < highscore.size(); i++) {
                highscore.remove(i);
            }
        }
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public GameState getGameState() {
        return state;
    }

    public int getGameSize() {
        return gameSize;
    }

    public Card getChosenCard() {
        return this.ChosenCard;
    }

    public Player getWinner() {
        state = GameState.INACTIVE;
        if (player2.getPoints() > player1.getPoints()) {
            return player2;
        } else {
            return player1;
        }
    }

    @Override
    public String toString() {
        String info = "";
        for (int i = 0; i < theCards.size(); i++) {
            info += theCards.get(i).getValue() + " ,";
        }
        return info;
    }

    private boolean checkForUnMatchedCards() {
        int noOfUnmatchedCards = 0;
        for (int i = 0; i < theCards.size(); i++) {
            if (theCards.get(i).getState() != CardState.MATCHED) {
                noOfUnmatchedCards = noOfUnmatchedCards + 1;
            }
        }
        return noOfUnmatchedCards != 0;
    }

}
