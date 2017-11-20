package dungeonCrawler.logic;

import dungeonCrawler.aqu.IGuide;
import dungeonCrawler.aqu.IPlayer;
import dungeonCrawler.aqu.IRoom;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * The Map class.
 *
 * @author
 */
class Map implements dungeonCrawler.aqu.IMap
{
    // Data fields
    private int height;
    private int width;
    private ArrayList<IRoom> roomList; //Declare the ArrayList, Roomlist(name), containing Room(Object).

    private ArrayList<Guide> guideList;
    private int numberOfGuides;
    private int numberOfContent;


    /**
     * Getter method for Height
     * Used in the array (coordinates)
     * Used to print the 3 lines in the map.
     *
     * @return int
     */
    @Override
    public int getHeight()
    {
        return height;
    }

    /**
     * Getter method for Width
     * Used to generate the Array
     *
     * @return int
     */
    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getNumberOfContent()
    {
        return numberOfContent;
    }

    /**
     * Contructor Map
     */
    public Map()
    {
        // Initializing (gives value to) private fields (constructor parameter)
        this.width = GameConstants.getMapSize().x;
        this.height = GameConstants.getMapSize().y;
        this.numberOfGuides = GameConstants.getMovingGuides();
        this.numberOfContent = GameConstants.getRoomContents();

        //Instantiate a ArrayList, allocates the ArrayList.
        roomList = new ArrayList<>();
        guideList = new ArrayList<>();
        for (int x = 0; x < numberOfGuides; x++)
        {
            Guide guide = GuideEnum.getRandomGuide();
            guide.setRandomLocation(new Point(width, height));

            guideList.add(guide);
        }

        int[] lockedRooms = new Random().ints(1, width * height).distinct().limit(GameConstants.getLockedRooms()).toArray();
        int roomNumber = 0;


        //Creates the coordinate system of the rooms. 
        for (int x = 0; x < width; x++) // Runs through the width.
        {
            for (int y = 0; y < height; y++)// runs through the height.
            {
                boolean locked = false;
                int finalRoomNumber = roomNumber;
                if (IntStream.of(lockedRooms).anyMatch(z -> z == finalRoomNumber))
                {
                    locked = true;
                }
                //RoomStrings, is a String array, that collects the name and description from the RoomEnum.
                String[] roomStrings = RoomEnum.getRandomString();


                roomList.add(new Room(new Point(x, y), numberOfContent, roomStrings[0], roomStrings[1], locked));//Point class in Java.
                roomNumber++;
            }
        }

    }

    /**
     * Method that tells if the room exists.
     *
     * @param exitPoint
     * @return boolean
     */
    @Override
    public boolean roomExists(Point exitPoint)
    {
        //For each loop. 
        //Which type, name, and list it runs through
        for (IRoom room : roomList)
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
     *
     * @param playerLocation
     */
    @Override
    public void setRoomHasBeenEntered(Point playerLocation)
    {
        for (IRoom room : roomList)
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
     *
     * @param p
     * @return boolean
     */
    @Override
    public boolean getRoomHasBeenEntered(Point p)
    {
        //For each loop: Which type, name, and list it runs through
        for (IRoom room : roomList)
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
     *
     * @return ArrayList
     */
    @Override
    public ArrayList<IRoom> getRoomList()
    {
        return roomList;
    }

    @Override
    public int numberOfEnteredRooms()
    {
        int numberOfRoomsEntered = 0;
        for (IRoom room : roomList)
        {
            if (room.getHasBeenEntered() == true)
            {
                numberOfRoomsEntered++;
            }
        }
        return numberOfRoomsEntered;
    }

    public ArrayList<Guide> getGuideList()
    {
        return guideList;
    }


    @Override
    public boolean isRoomLocked(Point checkPoint)
    {
        for (IRoom iRoom : roomList)
        {
            if (iRoom.getLocation().x == checkPoint.x && iRoom.getLocation().y == checkPoint.y)
            {
                return iRoom.isLocked();
            }
        }
        return false;

    }
    
    @Override
    public void guideMove()
    {
        for (Guide guide : guideList)
        {
            ArrayList<String> exitList = new ArrayList<>();

            if (roomExists(new Point(guide.getLocation().x - 1, guide.getLocation().y)))
            {
                exitList.add("left");
            }

            if (roomExists(new Point(guide.getLocation().x + 1, guide.getLocation().y)))
            {
                exitList.add("right");
            }

            if (roomExists(new Point(guide.getLocation().x, guide.getLocation().y + 1)))
            {
                exitList.add("up");
            }

            if (roomExists(new Point(guide.getLocation().x, guide.getLocation().y - 1)))
            {
                exitList.add("down");
            }

            //TODO Guide skal interagere med os - giv os et eller andet.

            //Checks if player and guide is in the same room
            guide.move(exitList);
            
        }
    }
    
    @Override
    public boolean guideAndPlayerSameRoom(Guide guide, Player player)
    {
        if (guide.getLocation().equals(player.getLocation()))
        {
            return true;
        }
        return false;
    }
    
    
        public boolean guideAndPlayerSameRoom(IGuide guide, IPlayer player)
    {
        if (guide.getLocation().equals(player.getLocation()))
        {
            return true;
        }
        return false;
    }
       



}

