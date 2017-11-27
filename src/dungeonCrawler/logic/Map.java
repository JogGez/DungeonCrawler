package dungeonCrawler.logic;

import dungeonCrawler.aqu.IGuide;
import dungeonCrawler.aqu.IThief;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
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
    private Point mapSize;
    private ArrayList<Room> roomList; //Declare the ArrayList, Roomlist(name), containing IRoom(Object).
    private ArrayList<Guide> guideList;
    private ArrayList<Thief> thiefList;
    private int numberOfGuides;
    private int numberOfThiefs;
    private int numberOfContent;
    private Player player;


    /**
     * Contructor Map
     */
    public Map(Player player)
    {
        // Initializing (gives value to) private fields (constructor parameter)
        this.width = GameConstants.getMapSize().x;
        this.height = GameConstants.getMapSize().y;
        this.mapSize = GameConstants.getMapSize();
        this.numberOfGuides = GameConstants.getNumberOfGuides();
        this.numberOfThiefs = GameConstants.getNumberOfThieves();
        this.numberOfContent = GameConstants.getRoomContents();
        this.player = player;

        //Instantiate a ArrayList, allocates the ArrayList.
        roomList = new ArrayList<>();
        guideList = new ArrayList<>();
        thiefList = new ArrayList<>();

        for (int x = 0; x < numberOfGuides; x++)
        {
            Guide guide = GuideEnum.getRandomGuide();
            guide.setRandomLocation(new Point(width, height));

            guideList.add(guide);
        }

        for (int x = 0; x < numberOfThiefs; x++)
        {
            Thief thief = new Thief(this);
            thiefList.add(thief);
        }

        // Controls how many locked we are gonna have. (2 rooms are locked, cannot be the same)
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

                //
                // Constructoren if the room should be locked or not, the last param (locked)
                roomList.add(new Room(new Point(x, y), numberOfContent, roomStrings[0], roomStrings[1], locked));//Point class in Java.
                roomNumber++;
            }
        }

    }

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
 Controls the Arraylist roomlist, that was created from the Map.
     *
     * @param playerLocation
     */
    @Override
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
     *
     * @param p
     * @return boolean
     */
    @Override
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
     *
     * @return ArrayList
     */
//    @Override
    public ArrayList<Room> getRoomList()
    {
        return roomList;
    }

    @Override
    public int numberOfEnteredRooms()
    {
        int numberOfRoomsEntered = 0;
        for (Room room : roomList)
        {
            if (room.getHasBeenEntered() == true)
            {
                numberOfRoomsEntered++;
            }
        }
        return numberOfRoomsEntered;
    }

    @Override
    public boolean isRoomLocked(Point checkPoint)
    {
        for (Room room : roomList)
        {
            if (room.getLocation().x == checkPoint.x && room.getLocation().y == checkPoint.y)
            {
                return room.isLocked();
            }
        }
        return false;

    }

    // TODO Move method and send map & roomList with it
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
    public void thiefMove()
    {
        for (Thief thief : thiefList)
        {
            ArrayList<String> exitList = new ArrayList<>();

            if (roomExists(new Point(thief.getLocation().x - 1, thief.getLocation().y)))
            {
                exitList.add("left");
            }

            if (roomExists(new Point(thief.getLocation().x + 1, thief.getLocation().y)))
            {
                exitList.add("right");
            }

            if (roomExists(new Point(thief.getLocation().x, thief.getLocation().y + 1)))
            {
                exitList.add("up");
            }

            if (roomExists(new Point(thief.getLocation().x, thief.getLocation().y - 1)))
            {
                exitList.add("down");
            }

            //TODO Thief skal interagere med os - giv os et eller andet.

            //Checks if player and thief is in the same room
            thief.move(exitList, this);
        }
    }
    
    @Override
    public boolean hasAllRoomBeenEntered()
    {
        return numberOfEnteredRooms() == roomList.size();
    }

    @Override
    public String checkRoomContent(int index)
    {
        for (Room room : roomList)
        {
            if (player.getLocation().equals(room.getLocation()))
            {
                if (room.getContent(index) instanceof Monster)
                {
                    return "Monster";
                }
                else if (room.getContent(index) instanceof Chest)
                {
                    return "Chest";
                }
                else if (room.getContent(index) instanceof Guide)
                {
                    return "Guide";
                }
                else
                {
                    return "";
                }
            }
        }
        return "";
    }

    public ArrayList<IGuide> guideArrayList()
    {
        ArrayList<? extends IGuide> guides = guideList;
        return (ArrayList<IGuide>) guides;
    }

    public ArrayList<IThief> thiefArrayList()
    {
        ArrayList<? extends IThief> thieves = thiefList;
        return (ArrayList<IThief>) thieves;
    }

    public boolean guideAndPlayerSameRoom(Guide guide, Player player)
    {
        if (guide.getLocation().equals(player.getLocation()))
        {
            return true;
        }
        return false;
    }
    
    
    public boolean guideAndPlayerSameRoom()
    {
        for (Guide guide : guideList)
        {
            if (guide.getLocation().equals(player.getLocation()))
            {
                return true;
            }
        }

        return false;
    }
        
    public void unlockRoom(Point playerLocation)
    {
        getCurrentRoom().setLocked(false);
    }

    @Override
    public Room getCurrentRoom()
    {
        for (Room room : roomList)
        {
            if (room.getLocation().equals(player.getLocation()))
            {
                return room;
            }
        }
        return new Room(new Point(0, 0), 0, "Empty", "none", false);
    }

    @Override
    public boolean roomContainsThief()
    {
        for (Thief thief : thiefList)
        {
            if (getCurrentRoom().getLocation().equals(thief.getLocation()))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean roomContainsGuide()
    {
        for (Guide guide : guideList)
        {
            if (getCurrentRoom().getLocation().equals(guide.getLocation()))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public void removeThief()
    {
        for(Iterator<Thief> i = thiefList.iterator(); i.hasNext();)
        {
            Thief thief = i.next();
            //Do Something
            if (getCurrentRoom().getLocation().equals(thief.getLocation()))
            {
                i.remove();
            }

        }
    }

    @Override
    public void getItemFromGuide(int inventoryIndex)
    {
        for (Guide guide : guideList)
        {
            if (getCurrentRoom().getLocation().equals(guide.getLocation()))
            {
                player.getInventory().addItem(guide.getItem(),inventoryIndex);
            }
        }

    }


    public Room getRoom(Point point)
    {
        for (Room room : roomList)
        {
            if (room.getLocation().equals(point))
            {
                return room;
            }
        }
        return new Room(new Point(0, 0), 0, "Empty", "none", false);
    }


    Point getMapSize()
    {
        return mapSize;
    }
}

