package dungeonCrawler.aqu;

import java.awt.*;

public interface IPlayer
{
//    IInventory getInventory();

    Point getLocation();

    void setLocation(Point location);

    Point getLastLocation();

    String getName();

    int getHealth();

    void setHealth(int health);

    int getScore();

    void setScore(int score);

//    IWeapon getWeapon();

//    void setCurrentWeapon(IWeapon weapon);

    int getTime();

    void setTime(int time);

    int getInventorySize();
}
