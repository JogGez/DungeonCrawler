package dungeonCrawler.logic;

import dungeonCrawler.aqu.IGuide;
import java.io.Serializable;

/**
 * The type guide
 *
 * @author Gruppen, minus Morten Bindslev
 */
// Guide class, the old helper that doesn't move
class Guide implements RoomContent, IGuide, Serializable
{
    private String name;
    private String description;
    private String ascii;

    /**
     * Constructor for guide
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
