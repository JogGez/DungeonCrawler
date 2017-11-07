package worldofzuul.logic;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Map class.
 * @author 
 */
public class Map implements RoomConstants
{
    // Data fields
    private int height;   
    private int width;
    private ArrayList<Room> roomList; //Declare the ArrayList, Roomlist(name), containing Room(Object).
    private ArrayList<Guide> guideList;
    private int helperNumbers;
    private int numberOfContent;
    
    /**
     * Getter method for Height
     * Used in the array (coordinates)
     * Used to print the 3 lines in the map. 
     * @return int
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * Getter method for Width
     * Used to generate the Array
     * @return int
     */
    public int getWidth()
    {
        return width;
    }

    public int getNumberOfContent()
    {
        return numberOfContent;
    }

    /**
     * Contructor Map
     * @param width
     * @param height 
     * 
     */
    public Map(int width, int height, int helperNumber, int numberOfContent)
    {
        // Initializing (gives value to) private fields (constructor parameter)
        this.width = width;
        this.height = height;
        this.helperNumbers = helperNumber;
        this.numberOfContent = numberOfContent;
        
         //Instantiate a ArrayList, allocates the ArrayList.
        roomList = new ArrayList<>();
        guideList = new ArrayList<>();
        for (int x = 0; x < helperNumber; x++)
        {
            guideList.add(new Guide());
        }

        //Creates the coordinate system of the rooms. 
        for (int x = 0; x < width; x++) // Runs through the width.
        {
            for (int y = 0; y < height; y++)// runs through the height.
            {
                int randomNameNumber = (int)(Math.random()*3);
                roomList.add(new Room(new Point(x,y), numberOfContent, RoomConstants.ROOM_NAME_DESCRIPTION[(randomNameNumber)]));//Point class in Java.
            }
        }

    }

    /**
     * Method that tells if the room exists. 
     * @param exitPoint
     * @return boolean
     */
    public boolean roomExists(Point exitPoint)
    {
        //For each loop. 
        //Which type, name, and list it runs through
        for (Room room : roomList)
        {
            //Runs through all the rooms, and tells if it have a x, y- value.  
            if (room.getLocation().x == exitPoint.x && room.getLocation().y == exitPoint.y)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Setter method for Room Has Been Entered
     * Controls the Arraylist roomlist, that was created from the Map.
     * @param playerLocation
     */
    public void setRoomHasBeenEntered(Point playerLocation)
    {
        for (Room room : roomList)
        {
            if (room.getLocation().x == playerLocation.x && room.getLocation().y == playerLocation.y)
            {
                room.setHasBeenEntered(true);
            }
        }
    }
    
    /**
     * Getter Method 
     * Returens the setter methods value. 
     * @param p
     * @return boolean
     */
    public boolean getRoomHasBeenEntered(Point p)
    {
        //For each loop: Which type, name, and list it runs through
        for (Room room : roomList)
        {
            // if x == x and y==y, for point p, reurns entered. 
            if (room.getLocation().x == p.x && room.getLocation().y == p.y)
            {
                return getRoomHasBeenEntered(p);
            }
        }
        return false;
    }
    /**
    * Getter Method for Roomlist
    * @return ArrayList
    */
    public ArrayList<Room> getRoomList()
    {
        return roomList;
    }


    
}
