package dungeonCrawler.aqu;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public interface IRoom extends Serializable
{
    int getNumberOfContent();

    // Setter of HasBeenEntered
    void setHasBeenEntered(boolean hasBeenEntered);

    // Getter of HasBeenEntered
    boolean getHasBeenEntered();

    String getName();

    String getDescription();

    IRoomContent getContent(int index);

    ArrayList<? extends IRoomContent> getContentArray();

    void removeContent(int index);

    Point getLocation();

    boolean isLocked();

    void setLocked(boolean locked);
}
