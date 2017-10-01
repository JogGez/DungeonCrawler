/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.Scanner;

/**
 *
 * @author Jonathan
 */
public class Player {
    // Scanner for player input name
   Scanner input = new Scanner(System.in);
   // String name of the player
   private String name;
   // To show player health
   private int health;
   // Player's inventory
   private int[] inventory;
   // Power of the player
   private int power;
   
   public Player(String name)
   {
       // String that gives the user info
       System.out.print("Write your name here");
       // Gets the next string input
       String playerName = input.next();
       // Gets the input and sets it to be name
       name = playerName;
       this.name = name;
   }
   
   // Constructor method?
   public Player(int[] inventory)
   {
       //Creating the array
       inventory = new int[]
       {0, 0, 0};
       this.inventory = inventory;
   }
}
