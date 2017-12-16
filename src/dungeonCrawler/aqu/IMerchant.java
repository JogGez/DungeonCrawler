package dungeonCrawler.aqu;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * IMerchant interface
 */
public interface IMerchant extends Serializable
{
    IInventory getInventory();

    String getName();

    void setRandomLocation(Point mapSize);

    String getDescription();

    String getAscii();

    Point getLocation();

    void move(ArrayList<String>exitList);
}
