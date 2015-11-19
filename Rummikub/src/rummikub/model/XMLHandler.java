/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import generated.Players;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;

/**
 *
 * @author DK
 */
public class XMLHandler {

    private static final generated.ObjectFactory objFactory = new generated.ObjectFactory();

    
    

    private XMLHandler(){}
    
    public static generated.Rummikub createXMLGameObj(RummikubGame game) {
        generated.Rummikub rummikub = objFactory.createRummikub();

        generated.Board board = createXMLBoardObj(game.getBoard());
        generated.Players players = createXMLPlayersObj(game.getPlayers());
        
        rummikub.setBoard(board);
        rummikub.setPlayers(players);
        rummikub.setName(game.getName());
        rummikub.setCurrentPlayer(game.getCurrentPlayer().getName());
        
        return rummikub;
    }
    
    private static Players createXMLPlayersObj(ArrayList<Player> gamePlayers)
    {
        generated.Players players = objFactory.createPlayers();
        
        for (Player gamePlayer : gamePlayers) {
            generated.Players.Player player = createXMLPlayerObj(gamePlayer);
            players.getPlayer().add(player);
        }
        
        return players;
    }
    
    private static generated.Players.Player createXMLPlayerObj(Player gamePlayer) 
    {
        generated.Players.Player player = objFactory.createPlayersPlayer();
        
        player.setName(gamePlayer.getName());
        generated.PlayerType type = gamePlayer.getType() == Player.PlayerType.HUMAN?
                                    generated.PlayerType.HUMAN : generated.PlayerType.COMPUTER;
        player.setType(type);
        generated.Players.Player.Tiles tiles = createXMLPLayerTiles(gamePlayer.getTiles());
        player.setTiles(tiles);
        
        return player;
    }
    
    private static Players.Player.Tiles createXMLPLayerTiles(List<Tile> gameTiles) {
        generated.Players.Player.Tiles tiles = objFactory.createPlayersPlayerTiles();
        
        for (Tile gameTile : gameTiles) {
            generated.Tile tile = createXMLTileObj(gameTile);
            tiles.getTile().add(tile);
        }
        
        return tiles;
    }

    
    private static generated.Board createXMLBoardObj(rummikub.model.Board gameBoard) {
        generated.Board board = objFactory.createBoard();

        for (Sequence gameSequence : gameBoard.getSequences()) {
            generated.Board.Sequence sequence = createXMLSequenceObj(gameSequence);
            board.getSequence().add(sequence);
        }

        return board;
    }

    private static generated.Board.Sequence createXMLSequenceObj(Sequence gameSequence) {
        generated.Board.Sequence sequence = objFactory.createBoardSequence();

        for (Tile gameTile : gameSequence.getTiles()) {
            generated.Tile tile = createXMLTileObj(gameTile);
            sequence.getTile().add(tile);
        }

        return sequence;
    }

    private static generated.Tile createXMLTileObj(rummikub.model.Tile gameTile) {
        generated.Tile tile = objFactory.createTile();

        tile.setValue(gameTile.getValue());
        generated.Color color = createXMLColorObj(gameTile.getColor());
        tile.setColor(color);

        return tile;
    }

    private static generated.Color createXMLColorObj(rummikub.model.Tile.Color gameColor) {
        generated.Color color;

        switch (gameColor) {
            case BLACK:
                color = generated.Color.BLACK;
                break;
            case BLUE:
                color = generated.Color.BLUE;
                break;
            case RED:
                color = generated.Color.RED;
                break;
            case YELLOW:
                color = generated.Color.YELLOW;
                break;
            default:
                throw new AssertionError();
        }

        return color;
    }

    public static void saveGame(RummikubGame gameToSave, String path) throws FileNotFoundException, JAXBException {
        generated.Rummikub rummikub = createXMLGameObj(gameToSave);
        OutputStream xmlOutputStream = new FileOutputStream(path);

        JAXBContext context = JAXBContext.newInstance(generated.Rummikub.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.marshal(rummikub, xmlOutputStream);
    }
}
