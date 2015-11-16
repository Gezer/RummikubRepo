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
    private ArrayList<Sequence> m_sequences = new ArrayList<Sequence>();

    void moveTile(int sourceIndex, int tileIndex, int destIndex)
    {
        Sequence sourceSequence = m_sequences.get(sourceIndex);
        Sequence destSequence = m_sequences.get(destIndex);   
        if (sourceSequence.Size() <= tileIndex)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Tile tileToMove = sourceSequence.removeTile(tileIndex);
        destSequence.AddTile(tileToMove);     
    }

    void insertTile(Tile tileToplay, int destIndex)
    {
        Sequence destSequence = m_sequences.get(destIndex);
        destSequence.AddTile(tileToplay);
    }
}
