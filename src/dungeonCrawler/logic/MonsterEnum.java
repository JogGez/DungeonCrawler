package dungeonCrawler.logic;

import dungeonCrawler.aqu.IMonster;

import java.util.Random;

enum MonsterEnum
{
    SPIDER  (new Monster("Shelob", "Web crawler that will suck your body dry", GameTextASCII.getSpider(), 100, 5)),
    BAT (new Monster("Shikaka", "The white flying bat", GameTextASCII.getBat(), 100, 5)),  //calls constructor with value 2
    MERMAID (new Monster("Ariel", "Beautiful creature with a lust for blood", GameTextASCII.getMermaid(), 100, 5)),
    LUCIFER (new Monster("Lucifer", "Beautiful creature with a lust for blood", GameTextASCII.getDevil(), 1000, 50))
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
        Monster monsterValues = values()[new Random().nextInt(values().length - 1)].monster;

        // And here we return a new instance of our guide from our Enumlist. Where it uses the reference MonsterEnum monsterValues
        return new Monster(monsterValues.getName(), monsterValues.getDescription(), monsterValues.getAscii(), monsterValues.getHealth(), monsterValues.getPower());
    }
}
