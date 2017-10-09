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
    private HashMap<String, Room> exits;

    private Point location;
    
    private ArrayList<RoomContent> content = new ArrayList<>();
    
    private String [] itemsInRoom = new String[2];
    


    /**
     * Instantiates a new Room.
     */
// Creates a no-args constructor to genereate a number between 0-3.
    public Room(Point p)
    {
        location = p;

        for (int i = 0; i < 2; i++) //int er counter
        {
            int randomThing1 = (int)(Math.random()*4);
            switch(randomThing1)
            {
                case 0: 
                    itemsInRoom[i] = "Empty";
                    break;
                case  1: System.out.println("There is a monster!");
                    itemsInRoom[i] = "Monster";                                         
                    content.add(new Monster());
                    break;
                case 2: System.out.println("There is a chest");
                    itemsInRoom[i] = "Chest";
                    content.add(new Chest());
                    break;
                case 3: System.out.println("There is another person in the room");
                    itemsInRoom[i] = "Helper";
                    content.add(new Helper());
                    break;
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
    
    public String checkOut()
    {
        
    
        for (RoomContent content : content) 
        

        if (content instanceof Monster)
        {
          Monster monster = (Monster)content;
          monster.setName("Monster");
          String checkOut = monster.getName();
          return  checkOut;
        }
        

            String checkOut = "There is not monster";
            return checkOut;

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

