package dungeonCrawler.logic;

import com.sun.prism.shader.DrawRoundRect_LinearGradient_PAD_Loader;
import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IThief;
import dungeonCrawler.presentationConsole.Game;
import jdk.nashorn.internal.ir.IfNode;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Thief class
 * A NPC that removes chests in a room after a certain amount of time, set in game settings
 * implements IThief, Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
class Thief implements IThief, Serializable
{
    private String ascii;
    private String name;
    private String description;
    private Point location;
    private RoomContent currentJob;
    private int currentJobCompletion;


    /**
     * Constructor for Thief
     * @param map
     */
    public Thief(Map map)
    {
        this.name = "Thief";
        this.ascii = GameTextASCII.getFrenshMan();
        this.description = "A mean thief stealing your stuff";
        this.location = getRandomLocation(map.getMapSize());
    }

    /**
     * Getter method for thief ascii
     * @return String
     */
    @Override
    public String getAscii()
    {
        return ascii;
    }

    /**
     * Getter method for thief name
     * @return String
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for thief description
     * @return String
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter method for thief location
     * @return Point
     */
    @Override
    public Point getLocation()
    {
        return location;
    }

    /**
     * Getter method for thief random location
     * @param point
     * @return Point
     */
    public Point getRandomLocation(Point point)
    {
        int randomX = (int)(Math.random()*point.x);
        int randomY = (int)(Math.random()*point.y);
        return new Point(randomX, randomY);
    }


    /**
     * Method for thief move
     * @param exitList
     * @param map
     */
    public void move(ArrayList<String> exitList, Map map)
    {
        for (RoomContent roomContent : map.getRoom(this.location).getContentArray())
        {
            if (roomContent instanceof Chest && currentJob == null)
            {
                currentJob = roomContent;
                currentJobCompletion = GameSettings.getThiftPickingRounds();
                return;
            }
            else if (roomContent instanceof Chest && currentJob != null && currentJobCompletion != 0)
            {
                currentJobCompletion--;
                return;
            }
            else if (roomContent instanceof Chest && currentJob != null && currentJobCompletion == 0)
            {
                //map.getRoom(this.location).getContentArray().remove(roomContent);
                map.getRoom(this.location).getContentArray().set(map.getRoom(this.location).getContentArray().indexOf(roomContent), null);
                currentJob = null;
                break;
            }
        }

        int random = (int)(Math.random()*(exitList.size()+1));
        if (random == exitList.size())
        {
        }
        else if (exitList.get(random).equals("left"))
        {
            location = (new Point(getLocation().x-1,getLocation().y));
        }
        else if (exitList.get(random).equals("right"))
        {
            location = (new Point(getLocation().x+1,getLocation().y));
        }
        else if (exitList.get(random).equals("up"))
        {
            location = (new Point(getLocation().x,getLocation().y+1));
        }
        else if (exitList.get(random).equals("down"))
        {
            location = (new Point(getLocation().x,getLocation().y-1));
        }
    }
}
