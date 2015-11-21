/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author DK
 */
public class AI
{
    public static void makeMove(RummikubGame game)
    {
        Player player = game.getCurrentPlayer();
        
        List<Tile> sequrnceToPlay = findPlayableSequence(player.getTiles());
    }

    private static List<Tile> findPlayableSequence(List<Tile> tiles)
    {
        List<Tile> sequenceToPlay = new ArrayList<>();
        
        Collections.sort(tiles);
        
        Tile.Color lastColor = tiles.get(0).getColor();
        int lastValue = 0;
        
        for (Tile tile : tiles) {
            if (tile.isJoker())
            {
                lastValue++;
            }
            else if (tile.getColor() == lastColor)
            {
                if (tile.getValue() == lastValue + 2)
                {
                    Tile joker = findJoker(tiles);
                    if (joker != null)
                    {
                        sequenceToPlay.remove(joker);
                        sequenceToPlay.add(joker);
                    }
                    else
                    {
                        sequenceToPlay.clear();
                    }
                }
                else if (tile.getValue() != lastValue + 1)
                {
                    sequenceToPlay.clear();
                }
            }
            else
            {
                sequenceToPlay.clear();
                lastColor = tile.getColor();
                lastValue = tile.getValue();
            }
            
            sequenceToPlay.add(tile);
                
        }
        
        return sequenceToPlay;
    }
    
    
//    private static List<Tile> findPlayableSequence(List<Tile> tiles)
//    {
//        List<Tile> sequenceToPlay = new ArrayList<>();
//        
//        Collections.sort(tiles);
//        
//        Tile.Color lastColor = tiles.get(0).getColor();
//        int lastValue = 0;
//        
//        for (Tile tile : tiles) {
//            if (tile.isJoker())
//            {
//                lastValue++;
//            }
//            else if (tile.getColor() == lastColor)
//            {
//                if (tile.getValue() == lastValue + 2)
//                {
//                    Tile joker = findJoker(tiles);
//                    if (joker != null)
//                    {
//                        sequenceToPlay.remove(joker);
//                        sequenceToPlay.add(joker);
//                    }
//                    else
//                    {
//                        sequenceToPlay.clear();
//                    }
//                }
//                else if (tile.getValue() != lastValue + 1)
//                {
//                    sequenceToPlay.clear();
//                }
//            }
//            else
//            {
//                sequenceToPlay.clear();
//                lastColor = tile.getColor();
//                lastValue = tile.getValue();
//            }
//            
//            sequenceToPlay.add(tile);
//                
//        }
//        
//        return sequenceToPlay;
//    }

    private static Tile findJoker(List<Tile> tiles)
    {
        Tile joker = null;
        
        for (Tile tile : tiles) {
            if (tile.isJoker())
            {
                joker = tile;
            }
        }
        
        return joker;
    }
    
    
    
    
}
