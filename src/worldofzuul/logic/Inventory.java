package worldofzuul.logic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author 
 */
public class Inventory
{
    Item[] items;
 /**
 * 
 * @param size 
 */
    public Inventory(int size)
    {
        this.items = new Item[size];
        
        
        
    }
/**
 * 
 * @param item
 * @param index 
 */
    public void addItem(Item item, int index)
    {
        items[index] = item;
    }
/**
 * 
 * @param index
 * @return 
 */
    public Item getItem(int index)
    {
        return items[index];
    }
/**
 * 
 * @param item
 * @return 
 */
    public int getItemIndex(Item item)
    {
        return Arrays.asList(items).indexOf(item);
    }
/**
 * 
 * @param index 
 */
    public void removeItem(int index)
    {
        items[index] = null;
        
    }
/**
 * Method that creats a empty array, and adds our potions. 
 * @return ArrayList with potions
 */
    public ArrayList<Potion> potionArrayList()
    {
        ArrayList<Potion> avaliblePotions = new ArrayList<>();

        for (int i = 0; i < items.length; i++)
        {
            // Controls if its a weapon or potions in our Items
            if (items[i] instanceof Potion)
            {
                avaliblePotions.add((Potion)items[i]);

            }
        }

        return avaliblePotions;
    }
/**
 * Tells how big the ArrayList is.
 * Used to control our ArrayList size. 
 * @return int
 */
    public int getSize()
    {
        return items.length;
    }
}
