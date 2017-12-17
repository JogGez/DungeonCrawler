package dungeonCrawler.aqu;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * IThief interface
 */
public interface IThief extends Serializable
{
    String getAscii();

    String getName();

    String getDescription();

    Point getLocation();
}
