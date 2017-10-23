package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

// Class that hold the information about a room
public class Room 
{
    // String description of the room
    private String description;
    // A HashMap list of exits from the room
    private HashMap<String, Room> exits;//lænker 2 ting sammen

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

