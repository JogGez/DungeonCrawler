/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.logic;

import dungeonCrawler.aqu.IItem;
import dungeonCrawler.aqu.IRoomContent;

/**
 * The type Chest.
 *
 * @author Frederik
 */
class Chest implements IRoomContent, dungeonCrawler.aqu.IChest
{
    IItem item;

    public Chest()
    {
        item = Item.getRandomItem();
    }

    @Override
    public IItem getItem()
    {
        return item;
    }
}
