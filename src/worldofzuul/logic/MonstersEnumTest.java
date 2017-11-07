package worldofzuul.logic;

import worldofzuul.presentation.ASCII;

import java.util.Random;

public enum MonstersEnumTest
{
    SPIDER  (new Monster("Spider","Web crawler that will suck your body dry", ASCII.getSpider(),100,5)),
    BAT (new Monster("Bat","Flying rat", ASCII.getBat(),100,5)),  //calls constructor with value 2
    MERMAID (new Monster("Mermaid","Beautiful creature with a lust for blood", ASCII.getMermaid(),100,5))
    ; // semicolon needed when fields / methods follow

    private final Monster monster;

    MonstersEnumTest(Monster monster)
    {
        this.monster = monster;
    }

    public Monster getMonster()
    {
        return new Monster(monster.getName(),monster.getDescription(),monster.getAscii(),monster.getHealth(),monster.getPower());
    }

    public Monster getRandomMonster()
    {
        // Picks a random monster from our Enum.
        Monster monsterValues = values()[new Random().nextInt(values().length)].monster;
        // And here we return a new instance of our guide from our Enumlist. Where it uses the reference Monster monsterValues
        return new Monster(monsterValues.getName(),monsterValues.getDescription(),monsterValues.getAscii(),monsterValues.getHealth(),monsterValues.getPower());
//        int randomMonster = new Random().nextInt();
//        return values()[(new Monster(randomMonster)].getMonster(randomMonster);
//        //return values()[new Random().nextInt(values().length)].monster;
    }
}
