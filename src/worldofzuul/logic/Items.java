package worldofzuul.logic;

import worldofzuul.presentation.ASCII;

public final class Items
{
    private static Item[] weapons = {

            new Weapon("Excalibur","The Sword of King Arthur", ASCII.getSword(),1000,1000),
            new Weapon("Durandal","Sword of Roland, legendary paladin of Charlemagne",ASCII.getSword(),100,15),
            new Weapon("Legbiter","The Sword of Viking King Magnus III", ASCII.getSword(),150,5),
            new Weapon("The Holy Lance","Also known as the Spear of Destiny and the Lance of Longinus, was the spear that allegedly pierced the side of Jesus during the crucifixion. ",ASCII.getSword(),500,1500),
            new Weapon("Goujian","Emperor Goujian, King of Yue States Sword", ASCII.getSword(),75,15),
            new Weapon("Mjölnir","The Mighty Hammer of Thor", ASCII.getSword(),250,100),
            new Weapon("Floppy Fish","Floppy Fish from the sea :(", ASCII.getSword(),1,0)

    };

    private static Item[] potions = {

            new Potion("Potion","Health Potion from the mighty wizard Merlin.",ASCII.getPotion(),50),
            new Potion("Potion","Health Potion from the mighty wizard Gandalf.",ASCII.getPotion(),75),
            new Potion("Potion","Health Potion from the mighty wizard Albus Dumbledore.",ASCII.getPotion(),100)
    };


    private Items()
    {
    }

    public static Item getRandomItem()
    {
        int randomItem = (int)(Math.random()*100);

        if (randomItem < 60)
        {
            return weapons[(int)(Math.random()*weapons.length)];
        }
        else
        {
            return potions[(int)(Math.random()*potions.length)];
        }

    }
}
