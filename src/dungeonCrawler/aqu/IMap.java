package dungeonCrawler.aqu;

import java.awt.*;
import java.util.ArrayList;

public interface IMap
{
    int getHeight();

    int getWidth();

    int getNumberOfContent();

    boolean roomExists(Point exitPoint);

    void setRoomHasBeenEntered(Point playerLocation);

    boolean getRoomHasBeenEntered(Point p);

    ArrayList<IRoom> getRoomList();

    int numberOfEnteredRooms();
}
