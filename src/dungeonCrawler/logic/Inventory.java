package dungeonCrawler.logic;

import dungeonCrawler.aqu.IInventory;
import dungeonCrawler.aqu.IItem;
import dungeonCrawler.aqu.IPotion;
import dungeonCrawler.aqu.IKey;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Inventory for items
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
class Inventory implements IInventory, Serializable
{
    Item[] items;

    /**
     * Constructor for inventory
     * @param size
     */
    public Inventory(int size)
    {
        this.items = new Item[size];
    }

    /**
     * Method to add a slot to the inventory array
     */
    @Override
    public void addOneSlot()
    {
        this.items = Arrays.copyOf(items, items.length + 1);
    }

    /**
     * Getter method for item
     * @param index
     * @return IItem
     */
    @Override
    public IItem getItem(int index)
    {
        return items[index];
    }

    /**
     * Getter method for index for item slot
     * @param item
     * @return int
     */
    @Override
    public int getItemIndex(IItem item)
    {
        return Arrays.asList(items).indexOf(item);
    }

    /**
     * Method for removing item on index number
     * @param index
     */
    @Override
    public void removeItem(int index)
    {
        items[index] = null;
    }

    /**
     * Method that creats a empty array, and adds our potions.
     *
     * @return ArrayList with potions
     */
    @Override
    public ArrayList<IPotion> potionArrayList()
    {
        ArrayList<IPotion> availablePotions = new ArrayList<>();

        for (int i = 0; i < items.length; i++)
        {
            // Controls if its a weapon or potions in our Items
            if (items[i] instanceof Potion)
            {
                availablePotions.add((Potion) items[i]);
            }
        }

        return availablePotions;
    }

    /**
     * Tells how big the ArrayList is.
     * Used to control our ArrayList size.
     *
     * @return int
     */
    @Override
    public int getSize()
    {
        return items.length;
    }

    @Override
    public ArrayList<IKey> keyArrayList()
    {
        ArrayList<IKey> availableKeys = new ArrayList<>();

        for (int i = 0; i < items.length; i++)
        {
            // Controls if its a weapon or potions in our Items
            if (items[i] instanceof Key)
            {
                availableKeys.add((Key) items[i]);
            }
        }

        return availableKeys;
    }

    @Override
    /**
     * Method for adding items to the array
     */
    public void addItem(IItem item, int inventoryIndex)
    {
        items[inventoryIndex] = (Item) item;
    }
}
