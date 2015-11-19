/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.view;

import java.util.Scanner;

/**
 *
 * @author Guy
 */
public class ConsoleUtils 
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    
    public enum ConsoleColor
    {
        RED(ANSI_RED),
        BLACK(ANSI_PURPLE),
        BLUE(ANSI_BLUE),
        YELLOW(ANSI_YELLOW);
        
        private final String colorCode;

        private ConsoleColor(String colorCode) {
            this.colorCode = colorCode;
        }    
        
        public String getValue()
        {
            return colorCode;
        }
    }
    
    public static void message(String msg)
    {
        System.out.println(msg);
    }
    
    public static void printInColor(ConsoleColor color, String str)
    {
        System.out.print(color.getValue() + str + ANSI_RESET);
    }
    
    public static String messageReadString(String msg)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print(msg);
        return scanner.nextLine();
    }
    
    public static int getIntFromUser(String msg, int minValue, int maxValue)
    {
        boolean gotLegalInput = false;
        int selectedValue = 0;
        
        while (!gotLegalInput)
        {
            ConsoleUtils.message(msg);
            try
            {
                selectedValue = ConsoleUtils.getNumberInBounds(minValue, maxValue);
                gotLegalInput = true;
            }
            catch (IllegalArgumentException ex)
            {
                ConsoleUtils.message(ex.getMessage());
            } 
        }
        
        return selectedValue;
    }
    
    public static int getNumberInBounds(Integer minValue, Integer maxValue) throws IllegalArgumentException
    {
        Scanner scanner = new Scanner(System.in);
        int userSelection=0;
        
        System.out.print("Insert a number between " + 
                minValue.toString() + "-" + maxValue.toString() + ": ");
        String userInput = scanner.nextLine();
        
        try
        {
            userSelection = Integer.parseInt(userInput);            
        }
        catch (NumberFormatException ex)
        {
            //throw ex;
            throw new IllegalArgumentException("Illegal Input, try again!");
        }
            
        if (!(userSelection >= minValue && userSelection <= maxValue))
        {
            throw new IllegalArgumentException
                ("Selected value is out of bounds.");
        }
        
        return userSelection;
    }
}