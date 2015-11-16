/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.view;

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
        System.out.println(color.getValue() + str + ANSI_RESET);
    }
}