package dungeonCrawler.aqu;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public interface IMerchant extends Serializable
{
    IInventory getInventory();

    String getName();

    String getDescription();

    String getAscii();

    Point getLocation();

    void move(ArrayList<String>exitList);
}
