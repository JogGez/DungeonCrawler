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
 * @author Frederik
 */
class Chest implements RoomContent, IChest, Serializable
{
    Item item;

    private String name;
    private String description;
    private String ascii;

    public Chest()
    {
        item = Item.getRandomItem();

        this.name = "Chest";
        this.description = "Old boring chest";
        this.ascii = GameTextASCII.getChest();
    }

    @Override
    public IItem getItem()
    {
        return item;
    }

    @Override
    public String getAscii()
    {
        return this.ascii;
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

}
