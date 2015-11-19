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
    
    private ArrayList<Player> players = new ArrayList<Player>();
    
    private Player currentPlayer;
   
    private Pile pile;
    
    private Board board;
    
    private String name;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Pile getPile() {
        return pile;
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }
    
    public void moveTileInBoard(int sourceIndex, int sourceTileIndex, int destIndex, int destTileIndex)
    {
        board.moveTile(sourceIndex, sourceTileIndex, destIndex, destTileIndex);
    }
    
    public void playTileFromHand(int handTileIndex, int destIndex, int destTileIndex)
    {
        Tile tileToplay = currentPlayer.drawTileByIndex(handTileIndex);
        board.insertTile(tileToplay, destIndex, destTileIndex);
    }
    
    boolean validateGame()
    {
        return board.validate();
    }
    
    boolean drawTile()
    {
        boolean succesfull = false;
        if (pile.size() > 0)
        {
            Tile drawn =  pile.Draw();
            currentPlayer.addTileToHand(drawn);
            succesfull = true;
        }
        
        return succesfull;
    }
}
