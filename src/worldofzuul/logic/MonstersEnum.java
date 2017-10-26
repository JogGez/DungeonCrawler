package worldofzuul.logic;

import worldofzuul.presentation.ASCII;

import java.util.Random;

public enum MonstersEnum
{
    SPIDER  ("Spider","Web crawler that will suck your body dry", ASCII.getSpider(),100,5),
    BAT ("Bat","Flying rat", ASCII.getBat(),100,5),  //calls constructor with value 2
    MERMAID ("Mermaid","Beautiful creature with a lust for blood", ASCII.getMermaid(),100,5)
    ; // semicolon needed when fields / methods follow

    private final String name;
    private final String description;
    private final String ascii;
    private final int health;
    private final int power;

    MonstersEnum(String name, String description, String ascii, int health, int power)
    {
        this.name = name;
        this.description = description;
        this.ascii = ascii;
        this.health = health;
        this.power = power;
    }

    public Monster getMonster()
    {
        return new Monster(this.name, this.description,this.ascii,this.health,this.power);
    }

    public static Monster getRandomMonster() {
        int r = new Random().nextInt(values().length);
//        Random random = new Random();
//        int r = random.nextInt(values().length);
        return new Monster(values()[r].name,values()[r].description,values()[r].ascii,values()[r].health,values()[r].power) ;
    }
}
