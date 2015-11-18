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
    
    public void moveTileInBoard(int sourceIndex, int sourceTileIndex, int destIndex, int destTileIndex)
    {
        m_board.moveTile(sourceIndex, sourceTileIndex, destIndex, destTileIndex);
    }
    
    public void playTileFromHand(int handTileIndex, int destIndex, int destTileIndex)
    {
        Tile tileToplay = m_currentPlayer.drawTileByIndex(handTileIndex);
        m_board.insertTile(tileToplay, destIndex, destTileIndex);
    }
    
    boolean validateGame()
    {
        return m_board.validate();
    }
}
