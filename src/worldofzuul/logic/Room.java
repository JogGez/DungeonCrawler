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
    private boolean hasBeenEntered;

    private Point location;

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

    public String getContent(int i)
    {
        if (content.get(i) instanceof Monster)
        {
          //Monster monster = (Monster)content.get(i);

          //String content = monster.getName();
          return  "Monster";
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


    public Point getLocation()
    {
        return location;
    }
}

