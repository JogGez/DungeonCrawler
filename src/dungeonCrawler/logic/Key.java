/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.logic;

import dungeonCrawler.aqu.IKey;
import java.io.Serializable;

/**
 *
 * @author Linea Hoffmann
 */
class Key extends Item implements IKey, Serializable
{
    public Key (String name, String description, String ascii)
    {
        this.setName(name);
        this.setDescription(description);
        this.setAscii(ascii);
    }
}
