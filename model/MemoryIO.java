package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Jesser
 */
public class MemoryIO {

    /**
     * serializes an object to a file
     *
     * @param file
     * @param HighScores ArrayList of the type player
     * @throws IOException
     */
    public void serializeToFile(String file, ArrayList<Player> HighScores) throws IOException {
        FileOutputStream theFile = new FileOutputStream(new File(file + ".txt"));
        ObjectOutputStream o = new ObjectOutputStream(theFile);
        try {
            o.writeObject(HighScores);
            System.out.println("Saving complete");
        } finally {
            o.close();
            theFile.close();
        }
    }

    /**
     *
     * @param file String
     * @return an array list of the type Player, that conatins all the players
     * with the highiest scores
     * @throws Exception
     */
    public ArrayList<Player> deSerializeFromFile(String file) throws Exception {
        ArrayList<Player> inputArrayList = new ArrayList<>();
        FileInputStream theFileInput = new FileInputStream(file + ".txt");
        ObjectInputStream oi = new ObjectInputStream(theFileInput);
        try {
            inputArrayList = (ArrayList<Player>) oi.readObject();
            System.out.println("Loading successful");
        } finally {
            oi.close();
            theFileInput.close();
            return inputArrayList;
        }
    }

}
