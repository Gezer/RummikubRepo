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
public class Pile {
    
    private ArrayList<Tile>  m_pile = new ArrayList<>();
    
    public Pile(ArrayList<Tile> tileSet)
    {
        this.m_pile = tileSet;
        Collections.shuffle(m_pile);
    }
    
    public Tile Draw()
    {
        Tile drawn = null;
        
        if (m_pile.size() > 0) {
            drawn = m_pile.get(0);
            m_pile.remove(0);
        }
        
        return drawn;
    }
}
