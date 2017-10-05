package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

// Class that hold the information about a room
public class Room 
{
    //Room name
    private String name;
    // String description of the room
    private String description;
    // A HashMap list of exits from the room
    private HashMap<String, Room> exits;
    private ArrayList < Monster > monsterNPC = new ArrayList();
    private ArrayList < Helper > helperNPC = new ArrayList();
    
    private String content; /* Should this be a String ? Could it be an ArrayList of the object 'Item'?
                            Or an ArrayList of different objects? [Monster] + [Item] + [Guide/wizard..]
                            Should it random how many I
                            Or different ArrayList -> One ArrayList of monster-objects [0-2?]
                            & an ArrayList of item-objects [0 to 2?]
                            & Guide/Wizard [0 to 1?]
                            */
    
    
    // Constructor method that add a description to the room and instantiates the HashMap for exits
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    // Adds a exit to the list
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    // returns the description set for the room
    public String getShortDescription()
    {
        return description;
    }

    // returns a longer description of the room
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    // return a String with all the exits in the room
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) 
        {
            returnString += " " + exit;
        }
        return returnString;
    }

    
   // return the exit for a given direction
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

