package dungeonCrawler.aqu;

import java.awt.*;
import java.util.ArrayList;

public interface IMerchant
{
    IInventory getInventory();

    String getName();

    String getDescription();

    String getAscii();

    Point getLocation();

    void move(ArrayList<String>exitList);
}
