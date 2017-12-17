package dungeonCrawler.aqu;

import java.awt.*;
import java.io.Serializable;

/**
 * ISpecial interface
 */
public interface ISpecial extends IItem, Serializable
{
    void use(IPlayer player, IMap map);

    String getTypeString();

    void use(IPlayer player, IMap map, Point point);

    void use(IPlayer player);
}
