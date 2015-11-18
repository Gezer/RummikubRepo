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

    public void moveTile(int sourceIndex, int sourceTileIndex , int destIndex, int destTileIndex)
    {
        Sequence sourceSequence = m_sequences.get(sourceIndex);
        Sequence destSequence = m_sequences.get(destIndex);   
        
        Tile tileToMove = sourceSequence.removeTile(sourceTileIndex);
        destSequence.AddTile(destTileIndex, tileToMove);     
    }

    public void insertTile(Tile tileToplay, int destIndex, int destTileIndex)
    {
        Sequence destSequence = m_sequences.get(destIndex);
        destSequence.AddTile(destTileIndex ,tileToplay);
    }
}
