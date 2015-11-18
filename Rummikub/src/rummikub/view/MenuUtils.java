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
    
    public static void showMenu(List<MenuItem> menu, String header)
    {
        System.out.println(header);
        System.out.println("Please choose:");

        Integer itemNum = 1;
        for(MenuItem item : menu)
        {
            System.out.println(itemNum.toString() + ". " + item.getText());
            itemNum++;
        }
        
        System.out.println("Type your selection number or 'Q' to quit and then press 'enter'");
    }
    
    public static boolean getUserSelection(Integer o_UserSelection, Integer i_MenuItemsCount) 
            throws IllegalArgumentException
    {
        boolean userQuit = false;
        Scanner scanner = new Scanner(System.in);
        
        String userSelection = scanner.nextLine();
        if ( !userSelection.equalsIgnoreCase("q") )
        {
            try
            {
                o_UserSelection = Integer.parseInt(userSelection);            
            }
            catch (NumberFormatException ex)
            {
                //throw ex;
                throw new IllegalArgumentException("Illegal Input, try againasd!");
            }
            
            if (!(o_UserSelection > 0 && o_UserSelection <= i_MenuItemsCount))
            {
                throw new IllegalArgumentException
                    ("Selected value is out of bounds.");
            }
        }
        else
        {
            o_UserSelection = 0;
            userQuit = true;
        }

        return !userQuit;
    }
}