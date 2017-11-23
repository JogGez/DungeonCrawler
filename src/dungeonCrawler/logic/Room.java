package dungeonCrawler.logic;

import dungeonCrawler.aqu.IRoomContent;

import java.awt.*;
import java.util.ArrayList;

/**
 * The type Room.
 */
// Class that hold the information about a room
class Room implements dungeonCrawler.aqu.IRoom
{
    // Room name.
    private String name;

    // Has room been entered.
    private boolean hasBeenEntered;
    //Is room locked.
    private boolean isLocked;

    public void setLocked(boolean locked)
    {
        isLocked = locked;
    }
    // The location of the room.
    private Point location;
    //
    private String description;
    // How many things are in the room
    private int numberOfContent;

    public boolean isLocked()
    {
        return isLocked;
    }
    // List of all the roomContent in the room
    private ArrayList<RoomContent> roomContent = new ArrayList<>();
    /**
     * Instantiates a new Room.
     */
    //Constructor
    //Parameters: point (coordinate) and numberOfContent.
    //Sets: location, name & description, hasBeenEntered, numberOfContent.
    public Room(Point p, int numberOfContent, String name, String description, boolean isLocked)
    {
        this.location = p;
        this.name = name;
        this.hasBeenEntered = false;
        this.numberOfContent = numberOfContent;
        this.description = description;
        this.isLocked =isLocked;

        // TODO
        // Loop that generates a random number (0-100) and adds a (nothing here//monster//chest//helper) according to the number generated.
        // Loop runs as long theres available spaces in the room.
        // Every run of the loop fills a roomslot.
        for (int i = 0; i < this.numberOfContent; i++) //int er counter
        {
            //Generating number 0-100.
            int randomNumber = (int) (Math.random() * 100);
            // If the number generated is 0-19 nothing here is added in the roomslot.
            if (randomNumber < 0)
            {
                roomContent.add(null);
            }
            // MONSTER: If the number generated is 20-49 a monster is added in the roomslot.
            else if (randomNumber < 25)
            {
                roomContent.add((RoomContent) MonsterEnum.getRandomMonster());
            }
            // CHEST: If the number generated is 50-84 a chest is added in the roomslot.
            else if (randomNumber <= 100)
            {
                roomContent.add((RoomContent)new Chest());
            }
            // GUIDE: If the number generated is 85-100 a helper is added in the roomslot.
//            else if (randomNumber <= 100)
//            {
//                roomContent.add(new Guide());
//            }
        }
    }

    @Override
    public int getNumberOfContent()
    {
        return numberOfContent;
    }

    // Getter of HasBeenEntered
    @Override
    public boolean getHasBeenEntered()
    {
        return hasBeenEntered;
    }

    // Setter of HasBeenEntered
    @Override
    public void setHasBeenEntered(boolean hasBeenEntered)
    {
        this.hasBeenEntered = hasBeenEntered;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * Method for finding a value in roomContent array, and returning the value.
     *
     * @param index
     * @return
     */

    public RoomContent getContent(int index)
    {
        return roomContent.get(index);
    }

    /**
     * // Method for removing roomContent
     *
     * @param index
     */
    @Override
    public void removeContent(int index)
    {
        roomContent.set(index, null);
    }

    public String checkRoomContent(int index)
    {
        if (getContent(index) instanceof Monster)
        {
            return "Monster";
        }
        else if (getContent(index) instanceof Chest)
        {
            return "Chest";
        }
        else if (getContent(index) instanceof Guide)
        {
            return "Guide";
        }
        else
        {
            return "";
        }
    }

    /**
     * // Getter of location.
     *
     * @return Point
     */
    @Override
    public Point getLocation()
    {
        return location;
    }
}

