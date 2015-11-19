/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import generated.Color;
import java.io.*;
import javax.xml.bind.*;

/**
 *
 * @author DK
 */
public class XMLHandler {

    private static final generated.ObjectFactory objFactory = new generated.ObjectFactory();

    private XMLHandler() {
    }

    public static generated.Rummikub createXMLGameObj(RummikubGame game) {
        generated.Rummikub rummikub = objFactory.createRummikub();

        generated.Board board = createXMLBoardObj(game.getBoard());

        return rummikub;
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
        generated.Color color = createColor(gameTile.getColor());
        tile.setColor(color);

        return tile;
    }

    private static generated.Color createColor(rummikub.model.Tile.Color gameColor) {
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
