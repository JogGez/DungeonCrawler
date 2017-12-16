package dungeonCrawler.logic;

import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IPlayer;
import dungeonCrawler.aqu.ISpecial;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Special class
 * Used for special items, with an unique ability.
 * implements ISpecial, Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
class Special extends Item implements ISpecial, Serializable
{
    // Used for switch cases
    public enum Type
    {
        TELEPORT, BOMB, AWESOME_NAME, VISION, EXTRA_SLOT
    }

    private Type type;

    /**
     * Constructor for Special
     * @param name
     * @param description
     * @param ascii
     * @param type
     */
    public Special(String name, String description, String ascii, Type type)
    {
        this.setName(name);
        this.setDescription(description);
        this.setAscii(ascii);
        this.type = type;
    }

    /**
     * Method to use special item
     * @param player
     */
    @Override
    public void use(IPlayer player)
    {
        switch (type)
        {
        case AWESOME_NAME:
            player.setName("(¯`·._.·(¯`·._.· " + "The " + player.getName().toUpperCase() + " ·._.·´¯)·._.·´¯)");
            break;
        case EXTRA_SLOT:
            player.getInventory().addOneSlot();
            break;
        }
    }

    /**
     * Method to use special item
     * @param player
     * @param map
     */
    @Override
    public void use(IPlayer player, IMap map)
    {
        switch (type)
        {
        case BOMB:
            for (Room room : ((ArrayList<Room>) map.getRoomList()))
            {
                if (room.getLocation().x + 1 == player.getLocation().x + 1 || room.getLocation().x - 1 == player.getLocation().x - 1
                        || room.getLocation().y + 1 == player.getLocation().y + 1 || room.getLocation().y - 1 == player.getLocation().y - 1)
                {
                    for (RoomContent content : room.getContentArray())
                    {
                        if (content instanceof Monster)
                        {
                            ((Monster) content).setHealth(((Monster) content).getHealth()/2);
                        }
                    }
                }
            }
            break;
        }
    }

    /**
     * Method to use special item
     * @param player
     * @param map
     * @param point
     */
    @Override
    public void use(IPlayer player, IMap map, Point point)
    {
        switch (type)
        {
        case TELEPORT:
            player.setLocation(point);
            break;
        }
    }

    /**
     * Getter method for special type
     * @return String
     */
    @Override
    public String getTypeString()
    {
        return type.name().toLowerCase();
    }

    Type getType()
    {
        return type;
    }
}
