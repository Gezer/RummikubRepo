/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.controller;

import java.util.ArrayList;
import java.util.List;
import rummikub.model.*;
import rummikub.view.ConsoleUtils;

/**
 *
 * @author Guy
 */
public class TurnHandler 
{
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
        gameEnded = false;
    }
    
    private Menu turnStartActions;
    
    private void initTurnStartActionsMenu()
    {
        turnStartActions = new Menu();
        List<MenuItem> menuItems = new ArrayList<>();

        turnStartActions.setMenuHeader("Choose your next action");  
        turnStartActions.setQuitMsg("Quit this game");
        menuItems.add(new MenuItem(this :: playTurn, "Start your turn."));
        menuItems.add(new MenuItem(this :: saveGame, "Save game & start your turn."));
        menuItems.add(new MenuItem(this :: saveGameAs, "Save game as... & start your turn."));
        turnStartActions.setMenu(menuItems);
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
        final boolean runOnce = true;
        boolean userQuit = turnStartActions.run(runOnce);
        
        if (userQuit) 
        {
            gameEnded = true;
        }
    }

    private void computerTurn() 
    {
        playTurn();
    }
    
    private void playTurn()
    {
        final boolean humanTurn = game.getCurrentPlayer().getType() == Player.PlayerType.HUMAN;
        
        if (humanTurn) {
            displayHand();
        }
        displayBoard();
        
        
    }
    
    private void saveGame()
    {
        //TODO
        playTurn();
    }
    
    private void saveGameAs()
    {
        //TODO
        playTurn();
    }
    
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