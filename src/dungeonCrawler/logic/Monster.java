package dungeonCrawler.logic;

import dungeonCrawler.aqu.IMonster;
import dungeonCrawler.aqu.IRoomContent;

/**
 * The type Monster.
 *
 * @author Computer
 */
//Monster Class, inherit from IRoomContent because ???
class Monster implements IRoomContent, IMonster
{
    private String name;
    private String description;
    private String ascii;
    private int health;
    private int power;

    /**
     * 
     * @param name
     * @param description
     * @param ascii
     * @param health
     * @param power 
     */
    public Monster(String name, String description, String ascii, int health, int power)
    {
        this.name = name;
        this.description = description;
        this.ascii = ascii;
        this.health = health;
        this.power = power;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    @Override
    public int getHealth()
    {
        return health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    @Override
    public void setHealth(int health)
    {
        this.health = health;
    }


    /**
     * Gets power.
     *
     * @return the power
     */
    @Override
    public int getPower()
    {
        return power;
    }

    /**
     * Sets power.
     *
     * @param power the power
     */
    @Override
    public void setPower(int power)
    {
        this.power = power;
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
     * Sets description.
     *
     * @param description the description
     */
    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Getter method for Ascii
     * @return String
     */
    @Override
    public String getAscii()
    {
        return this.ascii;
    }
}
