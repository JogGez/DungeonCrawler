package worldofzuul.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The type Room.
 */
// Class that hold the information about a room
public class Room 
{
    //Room name
    private String name;
    // String description of the room
    private String description;



    // A HashMap list of exits from the room
    private boolean hasBeenEntered; //Kontrollere om du har været i rummet før, så kan den vise det på mappet. 

    private Point location; //point x og y værdi. Point er en klasse i Java. 

    private HashMap<String, Room> exits;//Holder styr på hvilke rum der ligger rundt om rummet. 

    private ArrayList<RoomContent> content = new ArrayList<>();
    
    private String [] itemsInRoom = new String[2];
    

    public void setHasBeenEntered(boolean hasBeenEntered)
    {
        this.hasBeenEntered = hasBeenEntered;
    }

    public boolean getHasBeenEntered()
    {
        return hasBeenEntered;
    }
    /**
     * Instantiates a new Room.
     */
// Creates a no-args constructor to genereate a number between 0-3.
    public Room(Point p)
    {
        this.location = p;
        this.description = "Just a plain old, boring room ;/";
        this.hasBeenEntered = false;

        // New and improved random room creator...
        for (int i = 0; i < 2; i++) //int er counter
        {
            int randomThing1 = (int)(Math.random()*100);

            if (randomThing1 < 25)
            {
                itemsInRoom[i] = "Empty";
                content.add(new RoomContent());
            }
            else if (randomThing1 < 75)
            {
                itemsInRoom[i] = "Monster";
                content.add(new Monster());
            }
            else if (randomThing1 < 85)
            {
                itemsInRoom[i] = "Chest";
                content.add(new Chest());
            }
            else if (randomThing1 <= 100)
            {
                itemsInRoom[i] = "Helper";
                content.add(new Helper());
            }

        }
    }


    /**
     * Instantiates a new Room.
     *
     * @param description the description
     */
// Constructor method that add a description to the room and instantiates the HashMap for exits
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }
    
    public String getContent(int i)
    {
        if (content.get(i) instanceof Monster)
        {
          Monster monster = (Monster)content.get(i);
          monster.setName("Monster");
          String content = monster.getName();
          return  content;
        }
        if (content.get(i) instanceof Helper)
        {
            Helper helper = (Helper) content.get(i);
            String content = "Helper";
            return  content;
        }
        if (content.get(i) instanceof Chest)
        {
            Chest chest = (Chest)content.get(i);

            String content = "Chest";
            return  content;
        }
        else
        {
            RoomContent empty = (RoomContent)content.get(i);
            String content = "Empty";
            return  content;
        }
    }
    

    
    /**
     * Sets exit.
     *
     * @param direction the direction
     * @param neighbor  the neighbor
     */
// Adds a exit to the list
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Gets short description.
     *
     * @return the short description
     */
// returns the description set for the room
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Gets long description.
     *
     * @return the long description
     */
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


    /**
     * Gets exit.
     *
     * @param direction the direction
     * @return the exit
     */
// return the exit for a given direction
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public Point getLocation()
    {
        return location;
    }
}

