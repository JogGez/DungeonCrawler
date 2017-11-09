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


    private int maxTime;
    private Date startTime;
    
    
    public TimeTracker(Date startTime)
    {
        this.maxTime = GameConstants.getTimeAmount();
        this.startTime = startTime;
        
    }
    
    public int calculateRemainingTime()
    {
        Date currentTime = new Date();
        long elapsedTime = (((currentTime.getTime() - startTime.getTime()) / 1000));
        int timeLeft = maxTime - (int) elapsedTime;
        
        return timeLeft;
    }
}
