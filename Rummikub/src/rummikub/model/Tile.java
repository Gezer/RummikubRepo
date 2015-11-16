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
public class Tile {
    
    private final Color m_color;
    private final int value;
    
    private Tile(Color color, int value) {
        this.m_color = color;
        this.value = value;
    }
    
    private static final List<Tile> deck = new ArrayList<Tile>(); 

    static{
        
    }
    }
    
    public Color getColor() {
        return m_color;
    }

    public int getValue() {
        return value;
    }
}
