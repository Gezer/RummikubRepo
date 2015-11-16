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
public class Tile
{
    
    public enum Color {
    BLACK,
    RED,
    BLUE,
    YELLOW
    }

    private final Color m_color;
    
    private final int m_value;
    
    public Color getColor() {
        return m_color;
    }

    public int getValue() {
        return m_value;
    }
    
    private Tile(Color color, int value) {
        this.m_color = color;
        this.m_value = value;
    }
    
    private static final List<Tile> tileSet = new ArrayList<Tile>(); 
   
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 13;
    
    static
    {
        for (Color color : Color.values())
        {
            for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
                tileSet.add(new Tile(color, i));
                tileSet.add(new Tile(color, i));
            }
        }
        
        //adding the Joker: represented by 0
        tileSet.add(new Tile(Color.RED, 0));
        tileSet.add(new Tile(Color.BLACK, 0));
    }
        
    public static ArrayList<Tile> newTileSet()
    {
        return new ArrayList<Tile>(tileSet);
    }
}
