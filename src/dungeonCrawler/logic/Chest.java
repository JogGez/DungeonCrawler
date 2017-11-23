/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.logic;

/**
 * The type Chest.
 *
 * @author Frederik
 */
class Chest implements RoomContent, dungeonCrawler.aqu.IChest
{
    Item item;

    public Chest()
    {
        item = Item.getRandomItem();
    }

    @Override
    public Item getItem()
    {
        return item;
    }
}
