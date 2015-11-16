//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.16 at 03:29:36 PM IST 
//


package generated;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Board }
     * 
     */
    public Board createBoard() {
        return new Board();
    }

    /**
     * Create an instance of {@link Players }
     * 
     */
    public Players createPlayers() {
        return new Players();
    }

    /**
     * Create an instance of {@link Players.Player }
     * 
     */
    public Players.Player createPlayersPlayer() {
        return new Players.Player();
    }

    /**
     * Create an instance of {@link Rummikub }
     * 
     */
    public Rummikub createRummikub() {
        return new Rummikub();
    }

    /**
     * Create an instance of {@link Tile }
     * 
     */
    public Tile createTile() {
        return new Tile();
    }

    /**
     * Create an instance of {@link Board.Sequence }
     * 
     */
    public Board.Sequence createBoardSequence() {
        return new Board.Sequence();
    }

    /**
     * Create an instance of {@link Players.Player.Tiles }
     * 
     */
    public Players.Player.Tiles createPlayersPlayerTiles() {
        return new Players.Player.Tiles();
    }

}
