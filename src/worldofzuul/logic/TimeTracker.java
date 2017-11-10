/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.logic;

import java.util.Date;

/**
 *
 * @author Computer
 */
public class TimeTracker
{



    private Date startTime;
    //Reference to the player in game, "the old maxtime value"
    private Player player;
    
    
    public TimeTracker(Date startTime, Player player)
    {
        this.player = player;        
        this.startTime = startTime;
        
    }
    
    public int calculateRemainingTime()
    {
        Date currentTime = new Date();
        long elapsedTime = (((currentTime.getTime() - startTime.getTime()) / 1000));
        int timeLeft = player.getTime() - (int) elapsedTime;
        
        return timeLeft;
    }
    
}
