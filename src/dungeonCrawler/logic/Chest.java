/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.logic;

import dungeonCrawler.aqu.IChest;
import dungeonCrawler.aqu.IItem;

import java.io.Serializable;

/**
 * The type Chest.
 *
 * @author Gruppen, minus Mortens Bindslev
 */
class Chest implements RoomContent, IChest, Serializable
{
    Item item;

    private String name;
    private String description;
    private String ascii;

    /**
     * Constructor for chest
     * name, description, ascii.
     */
    public Chest()
    {
        item = Item.getRandomItem();

        this.name = "Chest";
        this.description = "Old boring chest";
        this.ascii = GameTextASCII.getChest();
    }

    /**
     * Getter method for Item
     * @return IItem
     */
    @Override
    public IItem getItem()
    {
        return item;
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

    /**
     * Getter method for name
     * @return String
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for Description
     * @return String
     */
    @Override
    public String getDescription()
    {
        return description;
    }

}
