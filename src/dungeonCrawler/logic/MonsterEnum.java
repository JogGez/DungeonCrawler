package dungeonCrawler.logic;

import dungeonCrawler.aqu.IMonster;
import dungeonCrawler.presentation.ASCII;

import java.util.Random;

enum MonsterEnum
{
    SPIDER  (new Monster("Shelob", "Web crawler that will suck your body dry", ASCII.getSpider(), 100, 5)),
    BAT (new Monster("Shikaka", "The white flying bat", ASCII.getBat(), 100, 5)),  //calls constructor with value 2
    MERMAID (new Monster("Ariel", "Beautiful creature with a lust for blood", ASCII.getMermaid(), 100, 5)),
    LUCIFER (new Monster("Lucifer", "Beautiful creature with a lust for blood", ASCII.getDevil(), 1000, 50))
    ; // semicolon needed when fields / methods follow

    private final IMonster monster;

    MonsterEnum(IMonster monster)
    {
        this.monster = monster;
    }

    public IMonster getMonster()
    {
        return new Monster(monster.getName(), monster.getDescription(), monster.getAscii(), monster.getHealth(), monster.getPower());
    }

    public static IMonster getRandomMonster()
    {
        // Picks a random monster from our Enum.
        IMonster monsterValues = values()[new Random().nextInt(values().length - 1)].monster;

        // And here we return a new instance of our guide from our Enumlist. Where it uses the reference MonsterEnum monsterValues
        return new Monster(monsterValues.getName(), monsterValues.getDescription(), monsterValues.getAscii(), monsterValues.getHealth(), monsterValues.getPower());
    }
}
