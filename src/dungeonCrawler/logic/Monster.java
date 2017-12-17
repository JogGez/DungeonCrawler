package dungeonCrawler.logic;

import dungeonCrawler.aqu.IMonster;

import java.io.Serializable;

/**
 * Monster class
 * Hostile npc and do battle with player
 * implements IMonster, Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
//Monster Class, inherit from IRoomContent
class Monster implements RoomContent, IMonster, Serializable
{
    private String name;
    private String description;
    private String ascii;
    private int health;
    private int power;

    /**
     * Constructor for Monster
     * @param name Sets the name to use.
     * @param description Sets the discription to use.
     * @param ascii Sets the ascii to use.
     * @param health Sets the health to use.
     * @param power Sets the power to use.
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
     * Getter method for monster name
     * @return String
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Setter method for monster name
     * @param name Sets the name to use.
     */
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter method for monster health
     * @return int
     */
    @Override
    public int getHealth()
    {
        return health;
    }

    /**
     * Setter method for monster health
     * @param health Sets the health to use.
     */
    @Override
    public void setHealth(int health)
    {
        this.health = health;
    }


    /**
     * Getter Method for monster power
     * @return int
     */
    @Override
    public int getPower()
    {
        return power;
    }

    /**
     * Setter method for monster power
     * @param power Sets the power to use.
     */
    @Override
    public void setPower(int power)
    {
        this.power = power;
    }

    /**
     * Getter method for monster description
     * @return String
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * Setter method for mosnter
     * @param description Sets the description to use.
     */
    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Getter method for ascii
     * @return String
     */
    @Override
    public String getAscii()
    {
        return this.ascii;
    }
}
