package worldofzuul.logic;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Map class.
 */
public class Map
{
    // Data fields
    private int height;   
    private int width;
    private ArrayList<Room> roomList; //Declare the ArrayList, Roomlist(name), containing Room(Object).  
    
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
    
    /**
     * Contructor Map
     * @param width
     * @param height 
     * 
     */
    public Map(int width, int height)
    {
        // Initializing (gives value to) private fields (contrsuctor parameter)
        this.width = width;
        this.height = height;
        
         //Instantiate a ArrayList, allocates the ArrayList.
        roomList = new ArrayList<>(); 

        //Creates the coordinate system of the rooms. 
        for (int x = 0; x < width; x++) // Runs through the width.
        {
            for (int y = 0; y < height; y++)// runs through the height.
            {
                roomList.add(new Room(new Point(x,y),2));//Point class in Java. 
            }
        }
    }

    /**
     * Method that tells if the room exists. 
     * @param p
     * @return boolean
     */
    public boolean roomExists(Point p)
    {
        //For each loop. 
        //Which type, name, and list it runs through
        for (Room room : roomList)
        {
            //Runs through all the rooms, and tells if it have a x, y- value.  
            if (room.getLocation().x == p.x && room.getLocation().y == p.y)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Setter method for Room Has Been Entered
     * @param p 
     */
    public void setRoomHasBeenEntered(Point p)
    {
        for (Room room : roomList)
        {
            if (room.getLocation().x == p.x && room.getLocation().y == p.y)
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
