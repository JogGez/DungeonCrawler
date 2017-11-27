package dungeonCrawler.aqu;

import java.util.ArrayList;

public interface IInventory
{
//    void addItem(IItem item, int index);

//    int getItemIndex(IItem item);

    IItem getItem(int index);

    int getItemIndex(IItem item);

    void removeItem(int index);

//    ArrayList<IPotion> potionArrayList();
//
//    ArrayList<IKey> keyArrayList();

    ArrayList<IPotion> potionArrayList();

    int getSize();

    ArrayList<IKey> keyArrayList();

    void addItem(IItem item, int inventoryIndex);
}
