package dungeonCrawler.logic;

import dungeonCrawler.aqu.IGuide;
import dungeonCrawler.aqu.IRoomContent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Guide.
 *
 * @author Jonathan & Linea
 */

// Guide class, the old helper that doesn't move
class Guide implements IRoomContent, dungeonCrawler.aqu.IGuide
{
    private String name;
    private String description;
    private Point location;


    private String ascii;

    /**
     * Instantiates a new Guide.
     */

    public Guide()
    {

    }

    public Guide(String name, String description, String ascii)
    {
        this.name = name;
        this.ascii = ascii;
        this.description = description;
        this.location = new Point(0, 0);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * To use guides current location
     * Method
     *
     * @return the current location
     */
    @Override
    public Point getLocation()
    {
        return location;
    }

    @Override
    public void move(ArrayList<String> exitList)
    {
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


    public void setRandomLocation(Point mapSize)
    {
        int randomX = (int)(Math.random()*mapSize.x);
        int randomY = (int)(Math.random()*mapSize.y);
        location = new Point(randomX, randomY);

    }


    /**
     * Gets description.
     *
     * @return the description
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * gets ASCII
     *
     * @return String
     */
    @Override
    public String getAscii()
    {
        return ascii;
    }

    public void guideMove()
    {
        for (IGuide guide : logic.guideList())
        {
            ArrayList<String> exitList = new ArrayList<>();

            if (map.roomExists(new Point(guide.getLocation().x - 1, guide.getLocation().y)))
            {
                exitList.add("left");
            }

            if (map.roomExists(new Point(guide.getLocation().x + 1, guide.getLocation().y)))
            {
                exitList.add("right");
            }

            if (map.roomExists(new Point(guide.getLocation().x, guide.getLocation().y + 1)))
            {
                exitList.add("up");
            }

            if (map.roomExists(new Point(guide.getLocation().x, guide.getLocation().y - 1)))
            {
                exitList.add("down");
            }


        }
