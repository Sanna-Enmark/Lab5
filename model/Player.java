
package model;

import java.io.Serializable;

/**
 *
 * @author senma
 */
public class Player implements Serializable, Comparable<Player>{

    private final String name;
    private int points;

    /**
     * Creates a new player with a final String as name. A new payer always
     * starts with 0 points. This class is serializable
     * @param name
     */
    public Player(String name) {
        this.name = name;
        points = 0;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    /**
     * Increases the points of this player by 1
     */
    public void addPoint() {
        this.points = points + 1;
    }
    
    public void resetPoints() {
        this.points=0;
    }
    
    @Override
    public String toString(){
        String info= "Name: " + name+ ", Points: " + points;
        return info;
    }
    
    /*
    Compares two objects of the type Player by their points.
    Returns 0 if the points are equal
    */

    @Override
    public int compareTo(Player other)  {
        
            return this.points-other.points;
        
    }

}
