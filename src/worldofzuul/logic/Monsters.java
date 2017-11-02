package worldofzuul.logic;

import worldofzuul.presentation.ASCII;
/**
 * Monster class
 * @author
 */
public final class Monsters
{
    /**
     * Creats an array of Monsters
     */
    private static Monster[] monsters = {
            new Monster("Spider","Web crawler that will suck your body dry", ASCII.getSpider(),100,5),
            new Monster("Bat","Flying rat", ASCII.getBat(),100,5),
            new Monster("Mermaid","Beautiful creature with a lust for blood", ASCII.getMermaid(),100,5)
    };
    
    /**
     * Creats a no-args contructor
     */
    private Monsters()
    {
    }

    /**
     * Getter method for RandomMonster
     * @return Monster
     */
    public static Monster getRandomMonster()
    {
        int i = (int)(Math.random()*monsters.length);

        return new Monster(monsters[i].getName(), monsters[i].getDescription(),monsters[i].getAscii(), monsters[i].getHealth(),monsters[i].getPower());

    }
}
