package dungeonCrawler.aqu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * IInventory interface
 */
public interface IInventory extends Serializable
{
//    void addItem(IItem item, int index);

//    int getItemIndex(IItem item);

    void addOneSlot();

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
