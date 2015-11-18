/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.controller;

import java.util.ArrayList;
import java.util.List;
import rummikub.model.*;
import rummikub.view.*;

/**
 *
 * @author Guy
 */
public class RummikubApp {
    
    Menu mainMenu;
    
    public void run()
    {
        initMenu();
        mainMenu.run();
    }
    
    private void initMenu()
    {
        mainMenu = new Menu();
        
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(new PlayNewRound(), "Play a new round."));
        menuItems.add(new MenuItem(new LoadGameFromFile(), "Load a game from selected file."));
        
        mainMenu.setMenuHeader("☺ ☺ ☺ Let's Play Rummikub ☺ ☺ ☺");
        mainMenu.setMenu(menuItems);
    }
    
    public class PlayNewRound implements ICommand
    {
        @Override
        public void execute() 
        {
            playNewRound();
        }   
    }
    
    public class LoadGameFromFile implements ICommand
    {
        @Override
        public void execute() 
        {
            loadGameFromFile();
        }   
    }
    
    private void playNewRound()
    {
    }
    
    private void loadGameFromFile()
    {
    }
}