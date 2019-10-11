/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author senma
 */
public class MemoryIO {
    
    public void serializeToFile(String file, ArrayList<Player> highscore) throws Exception {
         FileOutputStream theFile = new FileOutputStream(new File(file + ".txt"));
            ObjectOutputStream o = new ObjectOutputStream(theFile);
        try {
            o.writeObject(highscore);
        } finally {
            o.close();
            theFile.close();
        }
    }
    
    
    
}
