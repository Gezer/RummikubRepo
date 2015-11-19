/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package rummikub.model;

/**
 *
 * @author DK
 */
public class XMLHandler {
    
    private static final generated.ObjectFactory objFactory = new generated.ObjectFactory();
    
    private XMLHandler(){}
    
    public static generated.Rummikub createXMLGameObj(RummikubGame game)
    {
        generated.Rummikub rummikub = objFactory.createRummikub();
        
        generated.Board board = createBoard(game.getBoard());
        
        return rummikub;
    }

    private static generated.Board createBoard(rummikub.model.Board gameBoard) 
    {
        generated.Board board = objFactory.createBoard();
        
        for (Sequence gameSequence : gameBoard.getSequences()) {
            generated.Board.Sequence sequence = createSequence(gameSequence);
            board.getSequence().add(sequence);
        }
        
        return board;
    }
   
    private static generated.Board.Sequence createSequence(Sequence gameSequence)
    {
        generated.Board.Sequence sequence = objFactory.createBoardSequence();
        
        for(Tile gameTile : gameSequence.getTiles())
        {
            generated.Tile tile = createTile(gameTile);
            sequence.getTile().add(tile);
        }      
        
        return sequence;
    }
    
    private static generated.Tile createTile(rummikub.model.Tile gameTile) {
         generated.Tile tile = objFactory.createTile();
         
         tile.setValue(gameTile.getValue());
         generated.Color color = createColor(gameTile.getColor());
         tile.setColor(color);
         
         return tile;
    }
    
    private static generated.Color createColor(rummikub.model.Tile.Color color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
