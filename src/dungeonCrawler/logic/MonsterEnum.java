package dungeonCrawler.logic;

import java.io.Serializable;
import java.util.Random;

enum MonsterEnum implements Serializable
{
    SPIDER  (new Monster("Shelob", "Web crawler that will suck your body dry.", GameTextASCII.getSpider(), 100, 5)),
    BAT (new Monster("Shikaka", "The white flying bat.", GameTextASCII.getBat(), 100, 5)),  //calls constructor with value 2
    MERMAID (new Monster("Ariel", "Beautiful creature with a lust for blood.", GameTextASCII.getMermaid(), 100, 5)),
    XENOMORPH (new Monster("Xenomorph", "In space, no one can hear you scream.", GameTextASCII.getXenomorph(), 100, 5)),
    HAMSTER (new Monster("Hamster", "Don't be fooled by its furry look, its a rapid killer.", GameTextASCII.getHamster(), 100, 5)),
    LANDSHARK (new Monster("Land Shark", "The infamous land shark with rows upon rows of deadly sharp teeth.", GameTextASCII.getShark(), 100, 5)),
    CUPID (new Monster("Cupid", "This little devil is the root of all evil.", GameTextASCII.getCupid(), 100, 5)),
    SANTA (new Monster("Santa Claus", "The biggest lie you tell your children.", GameTextASCII.getSanta(), 100, 5)),
    WHALE (new Monster("Moby Dick", "The great white whale from the famous Herman Melville book.", GameTextASCII.getWhale(), 100, 5)),
    MERMAID2 (new Monster("Mermaid nr. 2", "This even more evil sister of Ariel.", GameTextASCII.getMermaid2(), 100, 5)),
    RABBIT (new Monster("Killer Rabbit of Caerbannog", "Rabbit of Caerbannog from the AWSOME movie \"Monty Python and the Holy Grail.\"", GameTextASCII.getRabbit(), 100, 5)),
    SHEEP (new Monster("Sheep", "Simple easy to kill sheep.", GameTextASCII.getSheep(), 100, 5)),
    GRIM (new Monster("The Grim Reaper", "Death is coming to collect you soul.", GameTextASCII.getGrim(), 100, 5)),
    GHOST (new Monster("Slimer", "The Jar Jar Binks of the Ghostbusters universe.", GameTextASCII.getGhost(), 100, 5)),
    ; // semicolon needed when fields / methods follow

    private final Monster monster;

    MonsterEnum(Monster monster)
    {
        this.monster = monster;
    }

    public Monster getMonster()
    {
        return new Monster(monster.getName(), monster.getDescription(), monster.getAscii(), monster.getHealth(), monster.getPower());
    }

    public static Monster getRandomMonster()
    {
        // Picks a random monster from our Enum.
        Monster monsterValues = values()[new Random().nextInt(values().length)].monster;

        // And here we return a new instance of our merchant from our Enumlist. Where it uses the reference MonsterEnum monsterValues
        return new Monster(monsterValues.getName(), monsterValues.getDescription(), monsterValues.getAscii(), monsterValues.getHealth(), monsterValues.getPower());
    }

    public static Monster getLucifer()
    {
        // And here we return a new instance of our merchant from our Enumlist. Where it uses the reference MonsterEnum monsterValues
        return new Monster("Lucifer", "The LORD of the Underworld, The one true evil.", GameTextASCII.getDevil(), 1000, 50);
    }
}
