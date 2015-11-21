/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.controller;

import java.util.*;
import rummikub.view.*;

/**
 *
 * @author Guy
 */
public class Menu
{
    private List<MenuItem> menu;
    private String header;
    private String quitMsg;
    
    public String getHeader() {
        return header;
    }

    public void setMenuHeader(String header) {
        this.header = header;
    }
    
    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    public void setQuitMsg(String quitMsg) {
        this.quitMsg = quitMsg;
    }
    
    public boolean run(boolean runOnce)
    {
        boolean userQuit = false;
        Integer userSelection;
        
        while (!userQuit)
        {
            MenuUtils.showMenu(menu, header, quitMsg);
            try
            {
                userSelection = MenuUtils.getUserSelection(menu.size());
                userQuit = userSelection == 0;
                if (!userQuit)
                {
                    menu.get(userSelection-1).selected();
                    if (runOnce) {
                        break;
                    }
                }
            }
            catch (IllegalArgumentException ex)
            {
                ConsoleUtils.message(ex.getMessage());
            }
        }
        
        return userQuit;
    }
}