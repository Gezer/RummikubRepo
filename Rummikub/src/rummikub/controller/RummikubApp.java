/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.controller;

import java.io.Console;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import javax.xml.bind.JAXBException;
import rummikub.model.*;
import rummikub.view.*;

/**
 *
 * @author Guy
 */
public class RummikubApp {
    
    private final static int MIN_PLAYER_NUM = 2;
    private final static int MAX_PLAYER_NUM = 4;
    
    private Menu mainMenu;
    
    private RummikubGame game;
    
    public void run()
    {
        final boolean runOnce = true;
        
        initMenu();
        mainMenu.run(runOnce);
        mainMenu.getMenu().add(new MenuItem(this :: playAnotherRound, "Play another round."));
        mainMenu.run(!runOnce);
    }
    
    private void initMenu()
    {
        mainMenu = new Menu();
        
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(this :: playNewGame, "Play a new game."));
        menuItems.add(new MenuItem(this :: loadGameFromFile, "Load a game from selected file."));
        
        mainMenu.setMenuHeader("☺ ☺ ☺ Let's Play Rummikub ☺ ☺ ☺");
        mainMenu.setMenu(menuItems);
    }
    
    private void playNewGame()
    {
        setNewGame();
        
        //xml check
        try
        {
            XMLHandler.saveGame(game, "david.xml");
        }
        catch(FileNotFoundException ex)
        {
            
        }
        catch(JAXBException ex)
        {
            
            //asasasd
        }
        
        playGame();
    }
    
    private void loadGameFromFile()
    {
        //TODO: load game from file
        playGame();
    }
    
    private void playAnotherRound()
    {
        setNewRound();
        playGame();
    }

    private void setNewGame() 
    {
        HashMap<String, Boolean> playersInfo = new HashMap<>();
        int numOfPlayers = ConsoleUtils.getIntFromUser("How many players?", MIN_PLAYER_NUM, MAX_PLAYER_NUM);
        int numOfComputerPlayers = ConsoleUtils.getIntFromUser("How many are AI?", 1, numOfPlayers);
        String gameName;
        
        gameName = ConsoleUtils.messageReadString("Insert the name of the game: ");
        
        for (int i = 0; i < numOfComputerPlayers; i++) {
            playersInfo.put("com" + Integer.toString(i+1), Boolean.TRUE);
        }
        
        for (int i = numOfComputerPlayers; i < numOfPlayers; i++) {
            while(true)
            {
                String name = ConsoleUtils.messageReadString("Insert human player " + Integer.toString(i+1) + " name: ");
                if (!playersInfo.containsKey(name)) {
                    playersInfo.put(name, Boolean.FALSE);
                    break;
                }
                else
                {
                    ConsoleUtils.message("This name is already taken, try again.");
                }
            }
        }
        
        game = GameBuilder.createNewGame(playersInfo, gameName);
    }
    
    private void setNewRound()
    {
        GameBuilder.resetGame(game);
    }

    private void playGame() 
    {
        
    }
}
