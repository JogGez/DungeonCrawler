package worldofzuul.logic;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory
{
    Item[] items;

    public Inventory(int size)
    {
        this.items = new Item[size];
        for (int i = 0; i < items.length; i++)
        {
            items[i] = new Item();
            items[i].name = "empty";
        }
    }

    public void addItem(Item item, int index)
    {
        items[index] = item;
    }

    public Item getItem(int index)
    {
        return items[index];
    }

    public int getItemIndex(Item item)
    {
        return Arrays.asList(items).indexOf(item);
    }

    public void removeItem(int index)
    {
        items[index] = new Item();
        items[index].name = "empty";
    }

    public ArrayList<Potion> potionArrayList()
    {
        ArrayList<Potion> avaliblePotions = new ArrayList<>();

        for (int i = 0; i < items.length; i++)
        {
            if (items[i] instanceof Potion)
            {
                avaliblePotions.add((Potion)items[i]);

            }
        }

        return avaliblePotions;
    }

    public int getSize()
    {
        return items.length;
    }
}
