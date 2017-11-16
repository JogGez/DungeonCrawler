package dungeonCrawler.aqu;

import java.util.ArrayList;

public interface IInventory
{
    void addItem(IItem item, int index);

    IItem getItem(int index);

    int getItemIndex(IItem item);

    void removeItem(int index);

    ArrayList<IPotion> potionArrayList();

    int getSize();
}
