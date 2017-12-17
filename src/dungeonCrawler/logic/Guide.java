package dungeonCrawler.logic;

import dungeonCrawler.aqu.IGuide;

import java.io.Serializable;

/**
 * Guide class
 * Used to interact with player and gives tips
 * implements RoomContent, IGuide, Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
// Guide class, the old helper that doesn't move
class Guide implements RoomContent, IGuide, Serializable
{
    private String name;
    private String description;
    private String ascii;

    /**
     * Constructor for guide
     * @param name Sets the name for the guide.
     * @param description Sets the discription for the guide.
     * @param ascii Sets the ascii for the guide.
     */
    public Guide(String name, String description, String ascii)
    {
        this.name = name;
        this.ascii = ascii;
        this.description = description;
    }

    /**
     * Getter method for guide name.
     *
     * @return String
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for description.
     *
     * @return String
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter method for GameTextASCII
     *
     * @return String
     */
    @Override
    public String getAscii()
    {
        return ascii;
    }

}
