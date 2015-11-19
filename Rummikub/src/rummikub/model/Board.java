/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import java.util.*;
/**
 *
 * @author Guy
 */
public class Board {
    private ArrayList<Sequence> sequences = new ArrayList<Sequence>();

    public ArrayList<Sequence> getSequences() {
        return sequences;
    }

    

    public void moveTile(int sourceIndex, int sourceTileIndex , int destIndex, int destTileIndex)
    {
        Sequence sourceSequence = sequences.get(sourceIndex);
        Sequence destSequence = sequences.get(destIndex);   
        
        Tile tileToMove = sourceSequence.removeTile(sourceTileIndex);
        destSequence.AddTile(destTileIndex, tileToMove);     
    }

    public void insertTile(Tile tileToplay, int destIndex, int destTileIndex)
    {
        Sequence destSequence = sequences.get(destIndex);
        destSequence.AddTile(destTileIndex ,tileToplay);
    }

    boolean validate()
    {
        boolean isValid = true;
        
        for (Sequence sequence : sequences) {
            if (sequence.Size() != 0)
            {
                if (!sequence.validate())
                {
                    isValid = false;
                    break;
                }
            }
        }
        
        return isValid;
    }
}
