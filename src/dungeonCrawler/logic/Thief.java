package dungeonCrawler.logic;

import com.sun.prism.shader.DrawRoundRect_LinearGradient_PAD_Loader;
import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IThief;
import dungeonCrawler.presentationConsole.Game;
import jdk.nashorn.internal.ir.IfNode;

import java.awt.*;
import java.util.ArrayList;

class Thief implements IThief
{
    private String ascii;
    private String name;
    private String description;
    private Point location;
    private RoomContent currentJob;
    private int currentJobCompletion;



    @Override
    public String getAscii()
    {
        return ascii;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public Point getLocation()
    {
        return location;
    }

    public Thief(Map map)
    {
        this.name = "Thief";
        this.ascii = GameTextASCII.getFrenshMan();
        this.description = "A mean thief stealing your stuff";
        this.location = getRandomLocation(map.getMapSize());
    }

    public Point getRandomLocation(Point point)
    {
        int randomX = (int)(Math.random()*point.x);
        int randomY = (int)(Math.random()*point.y);
        return new Point(randomX, randomY);
    }


    public void move(ArrayList<String> exitList, Map map)
    {
        for (RoomContent roomContent : map.getRoom(this.location).getContentArray())
        {
            if (roomContent instanceof Chest && currentJob == null)
            {
                currentJob = roomContent;
                currentJobCompletion = GameConstants.getThiftPickingRounds();
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
