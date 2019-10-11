/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author senma
 */
public class Card {
    private int value;
    private CardState state;
    private CardType type;
    
    /**
     * Creates a Hidden card with a final int value (generated by Logic). 
     * Type is meant to be dictated by the user
     * 
     * @param value
     * @param type
     */
    
    public Card(int value, CardType type) {
        this.state=CardState.HIDDEN;
        this.value=value;
        this.type=type;
    }
    
    public void ChangeStateToHidden(){
        this.state=CardState.HIDDEN;
    }
    
    public void ChangeStateToRevealed(){
        this.state=CardState.REVEALED;
    }
    
    public void ChangeStateToMatched(){
        this.state=CardState.MATCHED;
    }
    
    public int getValue(){
        return value;
    }
    
    public CardState getState(){
        return state;
    }
}