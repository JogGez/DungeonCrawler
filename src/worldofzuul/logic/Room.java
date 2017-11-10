package worldofzuul.logic;

import java.awt.*;
import java.util.ArrayList;

/**
 * The type Room.
 */
// Class that hold the information about a room
public class Room
{
    // Room name.
    private String name;
    // Has room been entered.
    private boolean hasBeenEntered;
    // The location of the room.
    private Point location;

    public int getNumberOfContent()
    {
        return numberOfContent;
    }

    // How many things are in the room
    private int numberOfContent;
    // List of all the content in the room
    private ArrayList<RoomContent> content = new ArrayList<>();


    // Setter of HasBeenEntered
    public void setHasBeenEntered(boolean hasBeenEntered)
    {
        this.hasBeenEntered = hasBeenEntered;
    }

    // Getter of HasBeenEntered
    public boolean getHasBeenEntered()
    {
        return hasBeenEntered;
    }

    public String getName()
    {
        return name;
    }

    /**
     * Instantiates a new Room.
     */
    //Constructor
    //Parameters: point (coordinate) and numberOfContent.
    //Sets: location, name & description, hasBeenEntered, numberOfContent.
    public Room(Point p, int numberOfContent, String name)
    {
        this.location = p;
        this.name = name;
        this.hasBeenEntered = false;
        this.numberOfContent = numberOfContent;

       // TODO
        // Loop that generates a random number (0-100) and adds a (nothing here//monster//chest//helper) according to the number generated.
        // Loop runs as long theres available spaces in the room.
        // Every run of the loop fills a roomslot.
        for (int i = 0; i < this.numberOfContent; i++) //int er counter
        {
            //Generating number 0-100.
            int randomNumber = (int)(Math.random()*100);
            // If the number generated is 0-19 nothing here is added in the roomslot.
            if (randomNumber < 0)
            {
                content.add(null);
            }
            // MONSTER: If the number generated is 20-49 a monster is added in the roomslot.
            else if (randomNumber < 5)
            {
                content.add(MonsterEnum.getRandomMonster());
            }
            // CHEST: If the number generated is 50-84 a chest is added in the roomslot.
            else if (randomNumber < 100)
            {
                content.add(new Chest());
            }
            // GUIDE: If the number generated is 85-100 a helper is added in the roomslot.
            else if (randomNumber <= 100)
            {
                content.add(new Guide());
            }
        }
    }

    
    /**
     * Method for finding a value in roomContent array, and returning the value. 
     * @param index
     * @return 
     */
    public RoomContent getContent(int index)
    {
        return content.get(index);
    }

    
    /**
     * // Method for removing content
     * @param index 
     */
    public void removeContent(int index)
    {
        content.set(index, null);
    }



    
   /**
    * // Getter of location.
    * @return Point
    */
    public Point getLocation()
    {
        return location;
    }
}

