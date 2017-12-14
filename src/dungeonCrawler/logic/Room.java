package dungeonCrawler.logic;

import dungeonCrawler.aqu.IRoom;
import dungeonCrawler.aqu.IRoomContent;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Room.
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
// Class that hold the information about a room
class Room implements IRoom, Serializable
{
    // Room name.
    private String name;
    // Has room been entered.
    private boolean hasBeenEntered;
    // Is room locked.
    private boolean isLocked;
    // The location of the room.
    private Point location;
    //
    private String description;
    // How many things are in the room
    private int numberOfContent;
    // List of all the roomContent in the room
    private ArrayList<RoomContent> roomContent;

    /**
     * Constructor for Room
     */
    //Constructor
    //Parameters: point (coordinate) and numberOfContent.
    //Sets: location, name & description, hasBeenEntered, numberOfContent.
    public Room(Point location, int numberOfContent, String name, String description, boolean isLocked)
    {
        this.location = location;
        this.name = name;
        this.hasBeenEntered = false;
        this.numberOfContent = numberOfContent;
        this.description = description;
        this.isLocked = isLocked;
        this.roomContent = new ArrayList<>();

        
        // Loop that generates a random number (0-100) and adds a (nothing here//monster//chest//helper) according to the number generated.
        // Loop runs as long theres available spaces in the room.
        // Every run of the loop fills a roomslot.
        for (int i = 0; i < this.numberOfContent; i++) //int er counter
        {
            //Generating number 0-100.
            int randomNumber = (int) (Math.random() * 100);

            // MONSTER: If the number generated is under getChangeOfMonster int a monster is added in the room slot.
            if (randomNumber < GameSettings.getChanceOfMonster())
            {
                roomContent.add(MonsterEnum.getRandomMonster());
            }
            // CHEST: If the number generated is under getChangeOfMonster + getChanceOfChest int  a chest is added in the room slot.
            else if (randomNumber <= GameSettings.getChanceOfMonster() + GameSettings.getChanceOfChest())
            {
                roomContent.add(new Chest());
            }
            // GUIDE: If the number generated is under getChangeOfMonster + getChanceOfChest + getChanceOfGuide int a helper is added in the room slot.
            else if (randomNumber <= GameSettings.getChanceOfMonster() + GameSettings.getChanceOfChest() + GameSettings.getChanceOfGuide())
            {
                // TODO Add someone here
                roomContent.add(GuideEnum.getRandomGuide());
            }
            // If the number generated is 0-19 nothing here is added in the roomslot.
            else if (randomNumber < 100)
            {
                roomContent.add(null);
            }
        }
    }

    /**
     * Setter method for locked Room
     * @param locked
     */
    public void setLocked(boolean locked)
    {
        isLocked = locked;
    }

    /**
     * Method to lock room
     * @return boolean
     */
    public boolean isLocked()
    {
        return isLocked;
    }

    /**
     * Getter method for number of room content
     * @return int
     */
    @Override
    public int getNumberOfContent()
    {
        return numberOfContent;
    }

    // Getter of HasBeenEntered
    /**
     * Getter method for room entered
     * @return boolean
     */
    @Override
    public boolean getHasBeenEntered()
    {
        return hasBeenEntered;
    }

    // Setter of HasBeenEntered

    /**
     * Setter method for room has been entered
     * @param hasBeenEntered
     */
    @Override
    public void setHasBeenEntered(boolean hasBeenEntered)
    {
        this.hasBeenEntered = hasBeenEntered;
    }

    /**
     * Getter method for room name
     * @return String
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for room description
     * @return String
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * Method for finding a value in roomContent array, and returning the value.
     * @param index
     * @return IRoomContent
     */
    @Override
    public IRoomContent getContent(int index)
    {
        return roomContent.get(index);
    }

    /**
     * Getter method for room content array
     * @return ArrayList
     */
    public ArrayList<RoomContent> getContentArray()
    {
        return roomContent;
    }

    /**
     * Method for removing room content in array
     * @param index
     */
    @Override
    public void removeContent(int index)
    {
        roomContent.set(index, null);
    }

    /**
     * Method to check room content
     * @param index
     * @return String
     */
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
        else if (getContent(index) instanceof Merchant)
        {
            return "Merchant";
        }
        else
        {
            return "";
        }
    }

    /**
     * Getter method for room location.
     * @return Point
     */
    @Override
    public Point getLocation()
    {
        return location;
    }
}

