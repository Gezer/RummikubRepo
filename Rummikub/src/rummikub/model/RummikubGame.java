/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import java.util.*;
/**
 *
 * @authors Guy Dunski Dmitry katz
 */
public class RummikubGame {
    
    private ArrayList<Player> m_players = new ArrayList<Player>();
    
    private Player m_currentPlayer;
   
    private Pile m_pile;
    
    private Board m_board;
    
    public void moveTileInBoard(int sourceIndex, int tileIndex, int destIndex)
    {
        m_board.moveTile(sourceIndex, tileIndex, destIndex);
    }
    
    public void playTileFromHand(int tileIndex, int destIndex)
    {
        Tile tileToplay = m_currentPlayer.drawTileByIndex(tileIndex);
        m_board.insertTile(tileToplay, destIndex);
    }
}
