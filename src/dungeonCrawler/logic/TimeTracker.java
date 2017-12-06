/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.logic;

import dungeonCrawler.aqu.IPlayer;
import dungeonCrawler.aqu.ITimeTracker;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Computer
 */
class TimeTracker implements ITimeTracker, Serializable
{
    private Date startTime;
    //Reference to the player in game, "the old maxtime value"
    private IPlayer player;
    
    
    public TimeTracker(Date startTime, IPlayer player)
    {
        this.player = player;        
        this.startTime = startTime;
        
    }
    
    @Override
    public int calculateRemainingTime()
    {
        Date currentTime = new Date();
        long elapsedTime = (((currentTime.getTime() - startTime.getTime()) / 1000));
        int timeLeft = player.getTime() - (int) elapsedTime;
        if (timeLeft <= 0) timeLeft = 0;
        return timeLeft;
    }
    
}
