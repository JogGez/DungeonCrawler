/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.logic;

import dungeonCrawler.presentation.ASCII;
import dungeonCrawler.aqu.IKey;
import java.util.Random;

/**
 *
 * @author Linea Hoffmann
 */
public enum KeyEnum
{
    KEY1 (new Key ("The Unicorn Key", "The key to unlock the all locked rooms", ASCII.getUnicorn())),
    KEY2 (new Key("The Teddybear Key", "The key to unlock all your childhood memories", ASCII.getTeddyBear())),
    KEY3 (new Key ("The Santa Key", "The key to unlock all the rooms, and whishes", ASCII.getSanta()))
    ;
    
    private final Key key;
    
    KeyEnum(Key key)
    {
        this.key = key;
    }

    public IKey getKey()
    {
        return new Key(key.getName(), key.getDescription(), key.getAscii());
    }

    public static IKey getRandomKey()
    {
        Key keyValues = values()[new Random().nextInt(values().length)].key;
        return new Key(keyValues.getName(), keyValues.getDescription(), keyValues.getAscii());
                
    }
    
    
}
