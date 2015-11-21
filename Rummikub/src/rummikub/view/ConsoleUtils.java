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
    public static final String ANSI_GREEN = "\u001B[32m";
    
    public enum ConsoleColor
    {
        RED(ANSI_RED),
        BLACK(ANSI_GREEN),
        BLUE(ANSI_BLUE),
        YELLOW(ANSI_YELLOW),
        RESET(ANSI_RESET);
        
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
    
    public static void print(String msg)
    {
        System.out.print(msg);
    }
    
    public static void printInColor(ConsoleColor color, String str)
    {
        System.out.print("[" + color.getValue() + str + ANSI_RESET + "] ");
    }
    
    public static void printColumnIndices()
    {
        System.out.println("Column num 0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   20   21   ");
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
    
    private static final int MAX_SOURCE_ROW = 30;
    private static final int MAX_SOURCE_COL = 30;
    private static final int MIN_DEST_ROW =    1;
    private static final int MAX_DEST_ROW =   30;
    private static final int MAX_DEST_COL =   13;
    
    public static int[] getMoveIndices()
    {
        boolean gotLegalInput = false;
        int sourceRow = 0;
        int sourceCol = 0;
        int destRow = 0;
        int destCol = 0;
        
        while (!gotLegalInput)
        try
        {
            message("Insert source row: ");
            sourceRow = getNumberInBounds(0, MAX_SOURCE_ROW);
            message("Insert source col: ");
            sourceCol = getNumberInBounds(0, MAX_SOURCE_COL);
            message("Insert dest row: ");
            destRow = getNumberInBounds(MIN_DEST_ROW, MAX_DEST_ROW);
            message("Insert dest col: ");
            destCol = getNumberInBounds(0, MAX_DEST_COL);
            gotLegalInput = true;
        }
        catch(IllegalArgumentException ex)
        {
            message("illegal input: try again.");
        }
        
        int[] moveIndices = {sourceRow, sourceCol, destRow, destCol};
        
        return moveIndices;
    }
}