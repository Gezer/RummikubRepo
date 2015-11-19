/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Guy
 */
public class GameBuilder {

    private static final int STARTING_HAND_TILE_COUNT = 14;
    
    public static RummikubGame createNewGame(HashMap<String, Boolean> playersInfo, String name) 
    {
        RummikubGame game = new RummikubGame();
        
        game.setName(name);
        game.setBoard(new Board());
        game.setPile(new Pile(Tile.newTileSet()));
        game.setPlayers(setPlayersList(playersInfo));
        Collections.shuffle(game.getPlayers());
        game.setCurrentPlayer(game.getPlayers().get(0));
        dealTiles(game);
        
        return game;
    }
    
    private static void dealTiles(RummikubGame game)
    {
        ArrayList<Player> playersList = game.getPlayers();
        
        for (Player player : playersList) {
            for (int i = 0; i < STARTING_HAND_TILE_COUNT; i++) {
                player.addTileToHand(game.getPile().Draw());
            }
        }
    }

    private static ArrayList<Player> setPlayersList(HashMap<String, Boolean> playersInfo) 
    {
        ArrayList<Player> playersList = new ArrayList<Player>();
        
        for (Map.Entry<String, Boolean> entry : playersInfo.entrySet()) {
            String key = entry.getKey();
            Boolean value = entry.getValue();
            Player.PlayerType type = value == true ? Player.PlayerType.COMPUTER : Player.PlayerType.HUMAN;
            
            Player player = new Player(type, key);
            playersList.add(player);
        }
        
        return playersList;
    }

    public static void resetGame(RummikubGame game) 
    {
        game.setBoard(new Board());
        game.setPile(new Pile(Tile.newTileSet()));
        Collections.shuffle(game.getPlayers());
        game.setCurrentPlayer(game.getPlayers().get(0));
        dealTiles(game);
    }
}