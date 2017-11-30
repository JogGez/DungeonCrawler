package dungeonCrawler.logic;

import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IPlayer;
import dungeonCrawler.aqu.ISpecial;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

class Special extends Item  implements ISpecial, Serializable
{
    public enum Type
    {
        TELEPORT, BOMB, AWESOME_NAME
    }

    private Type type;

    public Special(String name, String description, String ascii, Type type)
    {
        this.setName(name);
        this.setDescription(description);
        this.setAscii(ascii);
        this.type = type;
    }

    @Override
    public void use(IPlayer player)
    {
        switch (type)
        {
        case AWESOME_NAME:
            player.setName("(¯`·._.·(¯`·._.· " + "The " + player.getName().toUpperCase() + " ·._.·´¯)·._.·´¯)");
            break;
        }
    }

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
