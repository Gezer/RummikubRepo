/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.view;

import rummikub.controller.MenuItem;
import java.util.*;

/**
 *
 * @author Guy
 */
public class MenuUtils {
    
    public static void showMenu(List<MenuItem> menu, String header, String quitMsg)
    {
        System.out.println(header);
        System.out.println("Please choose:");

        Integer itemNum = 1;
        for(MenuItem item : menu)
        {
            System.out.println(itemNum.toString() + " - " + item.getText());
            itemNum++;
        }
        
        System.out.println("Type your selection number or 'Q' to " + quitMsg + " and then press 'enter'");
    }
    
    public static int getUserSelection(Integer i_MenuItemsCount) 
            throws IllegalArgumentException
    {
        Scanner scanner = new Scanner(System.in);
        int userSelection=0;
        
        String userInput = scanner.nextLine();
        if ( !userInput.equalsIgnoreCase("q") )
        {
            try
            {
                userSelection = Integer.parseInt(userInput);            
            }
            catch (NumberFormatException ex)
            {
                //throw ex;
                throw new IllegalArgumentException("Illegal Input, try again!");
            }
            
            if (!(userSelection > 0 && userSelection <= i_MenuItemsCount))
            {
                throw new IllegalArgumentException
                    ("Selected value is out of bounds.");
            }
        }
        
        return userSelection;
    }
}