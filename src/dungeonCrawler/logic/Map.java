package dungeonCrawler.logic;

import dungeonCrawler.aqu.*;
import org.omg.PortableServer.RequestProcessingPolicy;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Map class
 * Used as the game world
 * implements IMap, Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
class Map implements IMap, Serializable
{
    // Data fields
    private int height;
    private int width;
    private Point mapSize;
    private ArrayList<Room> roomList; //Declare the ArrayList, Roomlist(name), containing IRoom(Object).
    private ArrayList<Merchant> merchantList;
    private ArrayList<Thief> thiefList;
    private int numberOfMerchants;
    private int numberOfThiefs;
    private int numberOfContent;
    private Player player;

    /**
     * Constructor for Map
     * @param player
     */
    public Map(Player player)
    {
        // Initializing (gives value to) private fields (constructor parameter)
        this.width = GameSettings.getMapSize().x;
        this.height = GameSettings.getMapSize().y;
        this.mapSize = GameSettings.getMapSize();
        this.numberOfMerchants = GameSettings.getNumberOfMerchants();
        this.numberOfThiefs = GameSettings.getNumberOfThieves();
        this.numberOfContent = GameSettings.getRoomContents();
        this.player = player;

        //Instantiate a ArrayList, allocates the ArrayList.
        roomList = new ArrayList<>();
        merchantList = new ArrayList<>();
        thiefList = new ArrayList<>();

        for (int x = 0; x < numberOfMerchants; x++)
        {
            Merchant merchant = MerchantEnum.getRandomMerchant();
            merchant.setRandomLocation(new Point(width, height));

            merchantList.add(merchant);
        }

        for (int x = 0; x < numberOfThiefs; x++)
        {
            Thief thief = new Thief(this);
            thiefList.add(thief);
        }

        // Controls how many locked we are gonna have. (2 rooms are locked, cannot be the same)
        int[] lockedRooms = new Random().ints(1, width * height).distinct().limit(GameSettings.getLockedRooms()).toArray();
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

        // Prevent 2 rooms from being locked at the same time. Around spawn.
        for (Room room : roomList)
        {
            for (Room room2 : roomList)
            {
                if(room.getLocation().equals(new Point(1,0)) && room2.getLocation().equals(new Point(0,1)))
                {
                    if (room.isLocked() && room2.isLocked())
                    {
                        if ((new Random(2).nextInt() == 0)) room.setLocked(false);
                        else  room2.setLocked(false);

                        while (true)
                        {
                            int randomRoom = new Random(mapSize.x + mapSize.y).nextInt();
                            if (!roomList.get(randomRoom).isLocked())
                            {
                                roomList.get(randomRoom).setLocked(true);
                                break;
                            }
                        }


                    }
                }
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
     * Controls the Arraylist roomlist, that was created from the Map.
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
     * Returns the setter methods value.
     *
     * @param roomLocation
     * @return boolean
     */
    @Override
    public boolean getRoomHasBeenEntered(Point roomLocation)
    {
        //For each loop: Which type, name, and list it runs through
        for (Room room : roomList)
        {
                // if x == x and y==y, for point roomLocation, reurns entered.
            if (room.getLocation().x == roomLocation.x && room.getLocation().y == roomLocation.y)
            {
                return getRoomHasBeenEntered(roomLocation);
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
    public ArrayList<? extends IRoom> getRoomList()
    {
        return roomList;
    }

    /**
     * Counter for entered rooms
     *
     * @return int
     */
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

    /**
     * Checks if room is locked.
     *
     * @param checkPoint
     * @return boolean
     */
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

    /**
     * Makes the merchant move
     */
    @Override
    public void merchantMove()
    {
        for (Merchant merchant : merchantList)
        {
            ArrayList<String> exitList = new ArrayList<>();

            if (roomExists(new Point(merchant.getLocation().x - 1, merchant.getLocation().y)))
            {
                exitList.add("left");
            }

            if (roomExists(new Point(merchant.getLocation().x + 1, merchant.getLocation().y)))
            {
                exitList.add("right");
            }

            if (roomExists(new Point(merchant.getLocation().x, merchant.getLocation().y + 1)))
            {
                exitList.add("up");
            }

            if (roomExists(new Point(merchant.getLocation().x, merchant.getLocation().y - 1)))
            {
                exitList.add("down");
            }

            //Checks if player and merchant is in the same room
            merchant.move(exitList);
        }
    }

    /**
     * Makes the thief Move, uses method from thief move
     */
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

    /**
     * Checks if all rooms has been entered.
     * @return boolean
     */
    @Override
    public boolean hasAllRoomBeenEntered()
    {
        return numberOfEnteredRooms() == roomList.size();
    }

    /**
     * Looks at what object is in the room
     * @param index
     * @return String
     */
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

    /**
     * List of merchants
     * @return ArrayList
     */
    public ArrayList<? extends IMerchant> merchantArrayList()
    {
        return merchantList;
    }

    /**
     * List of thieves
     * @return ArrayList
     */
    public ArrayList<? extends IThief> thiefArrayList()
    {
        return thiefList;
    }

    /**
     * Sets the boolean locked to false
     * @param location
     */
    public void unlockRoom(Point location)
    {
        for (Room room : roomList)
        {
            if (room.getLocation().equals(location))
            {
                room.setLocked(false);
            }
        }
    }

    /**
     * Getter method for merchant
     * @return IMerchant
     */
    @Override
    public IMerchant getMerchant()
    {
        for (Merchant merchant :merchantList)
        {
            if (merchant.getLocation().equals(player.getLocation()))
            {
                return merchant;
            }
        }

        return null;
    }


    /**
     * Getter method for thief
     * @return IThief
     */
    @Override
    public IThief getThief()
    {
        for (Thief thief : thiefList)
        {
            if (thief.getLocation().equals(player.getLocation()))
            {
                return thief;
            }
        }

        return null;
    }

    /**
     * Getter method for the room the player is in
     * @return Room
     */
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

    /**
     * Checks if the room has a thief or not
     * @return boolean
     */
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

    /**
     * Checks if the room has a merchant or not
     * @return boolean
     */
    @Override
    public boolean roomContainsMerchant()
    {
        for (Merchant merchant : merchantList)
        {
            if (getCurrentRoom().getLocation().equals(merchant.getLocation()))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Removes the thief from the room
     */
    @Override
    public void removeThief()
    {
        //Do Something
        thiefList.removeIf(thief -> getCurrentRoom().getLocation().equals(thief.getLocation()));
    }

    /**
     *
     * @param point
     * @return Room
     */
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

    /**
     * Gives you X and Y for the Map Size
     * @return Point
     */
    Point getMapSize()
    {
        return mapSize;
    }
}

