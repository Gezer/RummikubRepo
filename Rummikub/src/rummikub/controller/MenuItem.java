/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.controller;

/**
 *
 * @author Guy
 */
public class MenuItem
{
    public ICommand command;
    public String text;
        
    public MenuItem(ICommand command, String text) {
        this.command = command;
        this.text = text;
    }

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void selected()
    {
        if (command != null)
        {
            command.execute();
        }
    }
}