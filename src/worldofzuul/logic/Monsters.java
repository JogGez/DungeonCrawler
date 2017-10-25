package worldofzuul.logic;

import worldofzuul.presentation.ASCII;

public final class Monsters
{
    private static Monster[] monsters = {
            new Monster("Spider","Web crawler that will suck your body dry", ASCII.getSpider(),100,5),
            new Monster("Bat","Flying rat", ASCII.getBat(),100,5),
            new Monster("Mermaid","Beautiful creature with a lust for blood", ASCII.getMermaid(),100,5)
    };

    private Monsters()
    {


    }

// TODO get new monster instead of reference to a monster
    public static Monster getRandomMonster()
    {
        int i = (int)(Math.random()*monsters.length);

        return new Monster(monsters[i].getName(), monsters[i].getDescription(),monsters[i].getAscii(), monsters[i].getHealth(),monsters[i].getPower());

    }
}
