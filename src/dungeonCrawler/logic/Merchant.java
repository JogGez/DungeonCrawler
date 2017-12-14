package dungeonCrawler.logic;

import dungeonCrawler.aqu.IInventory;
import dungeonCrawler.aqu.IMerchant;
import dungeonCrawler.aqu.IRoomContent;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Merchant.
 *
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */

// Merchant class, the old helper that doesn't move
class Merchant implements IMerchant, Serializable
{
    private String name;
    private String description;
    private Point location;
    private String ascii;
    private Inventory inventory;

    /**
     * Constructor for Merchant
     */

    public Merchant(String name, String description, String ascii)
    {
        this.name = name;
        this.ascii = ascii;
        this.description = description;
        this.location = new Point(0, 0);

        this.inventory = new Inventory(3);
        inventory.addItem(KeyEnum.getRandomKey(), 0);
        inventory.addItem(WeaponEnum.getRandomWeapon(), 1);
        inventory.addItem(PotionEnum.getRandomPotion(), 2);
    }

    /**
     * Getter method for merchants inventory
     * @return IInventory
     */
    @Override
    public IInventory getInventory()
    {
        return inventory;
    }

    /**
     * Getter method for merchants name
     * @return String
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for merchants location
     * @return Point
     */
    @Override
    public Point getLocation()
    {
        return location;
    }

    /**
     * Method for merchant move
     * @param exitList
     */
    @Override
    public void move(ArrayList<String> exitList)
    {
        int random = (int) (Math.random() * (exitList.size() + 1));
        if (random == exitList.size())
        {
        }
        else if (exitList.get(random).equals("left"))
        {
            location = (new Point(getLocation().x - 1, getLocation().y));
        }
        else if (exitList.get(random).equals("right"))
        {
            location = (new Point(getLocation().x + 1, getLocation().y));
        }
        else if (exitList.get(random).equals("up"))
        {
            location = (new Point(getLocation().x, getLocation().y + 1));
        }
        else if (exitList.get(random).equals("down"))
        {
            location = (new Point(getLocation().x, getLocation().y - 1));
        }
    }

    /**
     * Method to set merchants position to a random place on the map
     * @param mapSize
     */
    @Override
    public void setRandomLocation(Point mapSize)
    {
        int randomX = (int) (Math.random() * mapSize.x);
        int randomY = (int) (Math.random() * mapSize.y);
        location = new Point(randomX, randomY);
    }


    /**
     * Getter method for merchants description
     * @return String
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter method for merchants ascii
     * @return String
     */
    @Override
    public String getAscii()
    {
        return ascii;
    }

}
