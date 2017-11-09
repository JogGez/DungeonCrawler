/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.logic;

/**
 * The type Chest.
 *
 * @author Frederik
 */
public class Chest implements RoomContent
{
    Item item;

    public Chest()
    {
        item = Items.getRandomItem();
    }

    public Item getItem()
    {
        return item;
    }
}
