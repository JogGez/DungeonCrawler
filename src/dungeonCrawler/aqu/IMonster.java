package dungeonCrawler.aqu;

import java.io.Serializable;

/**
 * IMonster interface
 */
public interface IMonster extends Serializable
{
    String getName();

    void setName(String name);

    int getHealth();

    void setHealth(int health);

    int getPower();

    void setPower(int power);

    String getDescription();

    void setDescription(String description);

    String getAscii();
}
