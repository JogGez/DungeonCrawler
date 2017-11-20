package dungeonCrawler.logic;

import dungeonCrawler.aqu.IItem;
import dungeonCrawler.aqu.IPotion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author 
 */
class Inventory implements dungeonCrawler.aqu.IInventory
{
    IItem[] items;
 /**
 * 
 * @param size 
 */
    public Inventory(int size)
    {
        this.items = new IItem[size];
        
        
        
    }
/**
 * 
 * @param item
 * @param index 
 */
    @Override
    public void addItem(IItem item, int index)
    {
        items[index] = item;
    }
/**
 * 
 * @param index
 * @return 
 */
    @Override
    public IItem getItem(int index)
    {
        return items[index];
    }
/**
 * 
 * @param item
 * @return 
 */
    @Override
    public int getItemIndex(IItem item)
    {
        return Arrays.asList(items).indexOf(item);
    }
/**
 * 
 * @param index 
 */
    @Override
    public void removeItem(int index)
    {
        items[index] = null;
        
    }
/**
 * Method that creats a empty array, and adds our potions. 
 * @return ArrayList with potions
 */
    @Override
    public ArrayList<IPotion> potionArrayList()
    {
        ArrayList<IPotion> avaliblePotions = new ArrayList<>();

        for (int i = 0; i < items.length; i++)
        {
            // Controls if its a weapon or potions in our Items
            if (items[i] instanceof Potion)
            {
                avaliblePotions.add((IPotion)items[i]);

            }
        }

        return avaliblePotions;
    }
/**
 * Tells how big the ArrayList is.
 * Used to control our ArrayList size. 
 * @return int
 */
    @Override
    public int getSize()
    {
        return items.length;
    }
}
