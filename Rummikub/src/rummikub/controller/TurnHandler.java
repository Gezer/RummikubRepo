/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import rummikub.model.*;
import rummikub.view.ConsoleUtils;

/**
 *
 * @author Guy
 */
public class TurnHandler 
{
    private final static String GAME_CLONE_PATH = "C:/Users/Guy/Desktop/RummikubRepo/Rummikub/resources/cloneclonegameClone.xml";
    
    private final static boolean RUN_ONCE = true;
    
    private String lastSavePath;
    
    private boolean gameEnded = false;
    
    private RummikubGame game;

    public void setGame(RummikubGame game) 
    {
        this.game = game;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }
    
    public TurnHandler() 
    {
        initTurnStartActionsMenu();
        initPlayCycleMenu();
        gameEnded = false;
    }
    
    private Menu turnStartActions;
    
    private void initTurnStartActionsMenu()
    {
        turnStartActions = new Menu();
        List<MenuItem> menuItems = new ArrayList<>();

        turnStartActions.setMenuHeader("Choose your next action:");  
        turnStartActions.setQuitMsg("Quit this game");
        menuItems.add(new MenuItem(this :: startTurnDummy, "Start your turn."));
        menuItems.add(new MenuItem(this :: saveGame, "Save game & start your turn."));
        menuItems.add(new MenuItem(this :: saveGameAs, "Save game as... & start your turn."));
        turnStartActions.setMenu(menuItems);
    }
    
    private Menu playCycleMenu;
    
    private void initPlayCycleMenu()
    {
        playCycleMenu = new Menu();
        List<MenuItem> menuItems = new ArrayList<>();
        
        playCycleMenu.setMenuHeader("Choose your next play:");
        playCycleMenu.setMenuHeader("End your turn");
        menuItems.add(new MenuItem(this :: moveTile, "Move a Tile."));
        menuItems.add(new MenuItem(this :: drawFromPile, "Draw from pile (this will end your turn and revert all your changes)."));
        playCycleMenu.setMenu(menuItems);
    }
    
    public void playNextTurn()
    {
        Player currentPlayer = game.getCurrentPlayer();
        
        ConsoleUtils.message(currentPlayer.getName() + " turn!");
        
        if (currentPlayer.getType() == Player.PlayerType.HUMAN) 
        {
            humanTurn();
        }
        else
        {
            computerTurn();
        }
    }
    
    private void humanTurn() 
    {
        boolean userQuit = turnStartActions.run(RUN_ONCE);
        
        if (userQuit) 
        {
            gameEnded = true;
        }
        
        playTurn();
    }

    private void computerTurn() 
    {
        playTurn();
    }
    
    private void playTurn()
    {
        //saving game for backup in case we need a clone
        try
        {
            XMLHandler.saveGame(game, GAME_CLONE_PATH);
        }
        catch(FileNotFoundException | JAXBException | SAXException ex)
        {
            ConsoleUtils.message("An internal save error has occured, terminating current game");
            gameEnded = true;
            return;
        }
        
        moveCycle();
        
        if (!game.va) 
        {
            IllegalBoardPenalty();
        }
        
        //TODO: game.move to next player
    }
    
    private void moveCycle() 
    {
        final boolean humanTurn = game.getCurrentPlayer().getType() == Player.PlayerType.HUMAN;
        boolean finishedMoves = false;
        Player currentPlayer = game.getCurrentPlayer();
        int initialPileSize = game.getPile().getTiles().size();
        int initialHandSize = currentPlayer.getTiles().size();
        
        if (humanTurn) 
        {
            while (!finishedMoves)
            {
                displayHand();
                displayBoard();
                finishedMoves = playCycleMenu.run(RUN_ONCE);
                
                if (finishedMoves) 
                {
                    if (currentPlayer.getTiles().size() == initialHandSize) 
                    {
                        ConsoleUtils.message("No plays made, drawing from pile!");
                        drawFromPile();
                    }
                }
                else if (game.getPile().getTiles().size() == initialPileSize - 1) 
                {
                    finishedMoves = true;
                }
            }
        }
        else
        {
            displayBoard();
            //TODO: AI plays his shit
            //if drawn: messege, if moved show board.
        }
    }
    
    private void moveTile()
    {
        int[] moveIndices = ConsoleUtils.getMoveIndices();
        
        int sourceRow = moveIndices[0];
        int sourceCol = moveIndices[0];
        int destRow = moveIndices[0] - 1;
        int destCol = moveIndices[0];
        
        if (sourceRow == 0) 
        {
            try
            {
                game.playTileFromHand(sourceCol, destRow, destCol);
            }
            catch(ArrayIndexOutOfBoundsException ex)
            {
                ConsoleUtils.message("Unable to complete your play, wront indices!");
            }
        }
        else
        {
            try
            {
                game.moveTileInBoard(sourceRow, sourceCol, destRow, destCol);            
            }
            catch(ArrayIndexOutOfBoundsException ex)
            {
                ConsoleUtils.message("Unable to complete your play, wront indices!");
            }
        }
    }
    
    private void drawFromPile()
    {
        game.getCurrentPlayer().addTileToHand(game.getPile().Draw());
        displayHand();
    }
    
    private void IllegalBoardPenalty()
    {
        RummikubGame gameClone;
            
            try
            {
                gameClone = XMLHandler.loadGame(GAME_CLONE_PATH);
            }
            catch(JAXBException | SAXException | FileNotFoundException ex)
            {
                ConsoleUtils.message("An internal load error has occured, terminating current game");
                gameEnded = true;
                return;
            }
            
            game.setBoard(gameClone.getBoard());
            game.setPile(gameClone.getPile());
            game.setPlayers(gameClone.getPlayers());
            
            ConsoleUtils.message("Illegal board state, 3 tiles penalty!");
            game.getCurrentPlayer().addTileToHand(game.getPile().Draw());
            game.getCurrentPlayer().addTileToHand(game.getPile().Draw());
            game.getCurrentPlayer().addTileToHand(game.getPile().Draw());
            displayBoard();
    }
    
    private void saveGame()
    {
        if (lastSavePath != null) 
        {
            try
            {
                XMLHandler.saveGame(game, lastSavePath);
            }
            catch(FileNotFoundException | JAXBException | SAXException ex)
            {
                ConsoleUtils.message("An error has occured while saving game to file");
            }
        }
        else
        {
            ConsoleUtils.message("No last save file deteted, you need to save as... first!");
            saveGameAs();
        }
    }
    
    private void saveGameAs()
    {
            //TODO: get path from user
            //lastSavePath = the path we got from him
         
    }
    
    private void startTurnDummy(){}
    
    private void displayBoard()
    {
        ConsoleUtils.message("The Board:");
        int i=1;
        for (Sequence sequence : game.getBoard().getSequences()) {
            displaySequence(sequence.getTiles(), i);
            i++;
        }
        ConsoleUtils.printColumnIndices();
    }
    
    private void displayHand()
    {
        ConsoleUtils.message("Your Hand:");
        displaySequence(game.getCurrentPlayer().getTiles(), 0);
        ConsoleUtils.printColumnIndices();
    }

    private void displaySequence(List<Tile> sequence, Integer index)
    {
        //display line index
        String indexMsg = "index ";
        if (index<=9) {
            indexMsg = indexMsg + " ";
        }
        ConsoleUtils.print(indexMsg + index.toString() + ": ");
        
        for (Tile tile : sequence) {
            //set the string(length must be 3): 
            Integer value = tile.getValue();
            String displayValue = value ==0 ? "☺" : value.toString();
            if (value <=9) {
                displayValue = displayValue + " ";
            }
            
            //set the color:
            ConsoleUtils.ConsoleColor color = transformToViewColor(tile.getColor());
            
            //print:
            ConsoleUtils.printInColor(color, displayValue);
        }
        
        ConsoleUtils.message("");
    }
    
    private ConsoleUtils.ConsoleColor transformToViewColor(Tile.Color color)
    {
        ConsoleUtils.ConsoleColor transformed;
        
        switch (color) {
            case BLACK:
                transformed = ConsoleUtils.ConsoleColor.BLACK;
                break;
            case BLUE:
                transformed = ConsoleUtils.ConsoleColor.BLUE;
                break;
            case RED:
                transformed = ConsoleUtils.ConsoleColor.RED;
                break;
            case YELLOW:
                transformed = ConsoleUtils.ConsoleColor.YELLOW;
                break;
            default:
                transformed = ConsoleUtils.ConsoleColor.RESET;
        }
        
        return transformed;
    }
}