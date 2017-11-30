package dungeonCrawler.aqu;

import java.awt.*;
import java.io.Serializable;

public interface IPlayer extends Serializable
{
    IInventory getInventory();

    Point getLocation();

    void setLocation(Point location);

    Point getLastLocation();

    String getName();

    int getHealth();

    void setHealth(int health);

    int getScore();

    IWeapon getWeapon();

    void setScore(int score);

    void setWeapon(IWeapon weapon);

    int getTime();

    void setTime(int time);

    int getInventorySize();

    void setName(String name);
}
