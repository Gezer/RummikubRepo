/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import java.util.*;
/**
 *
 * @author DK
 */
public class Sequence
{
    private List<Tile> m_tiles = new ArrayList<Tile>(); 
    
    public void  AddTile(Tile toAdd)
    {
        m_tiles.add(toAdd);
        Collections.sort(m_tiles);
    }

    int Size() {
        return m_tiles.size();
    }

    Tile removeTile(int tileIndex)
    {
        return m_tiles.remove(tileIndex);
    }
    
    
}
