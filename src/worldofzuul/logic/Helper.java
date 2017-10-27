/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.logic;

import java.awt.*;

/**
 * The type Helper.
 *
 * @author Jonathan
 */
// Helper class
public class Helper extends RoomContent
{
    private String name;
    private String answer;
    private String description;
    private Point location;

    /**
     * Instantiates a new Helper.
     */

    public Helper()
    {

    }

    public Helper(String name, String answer, String description, int x, int y)
    {
        this.name = name;
        this.answer = answer;
        this.description = description;
        this.location = new Point(x, y);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets answer.
     *
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }


    public Point getLocation()
    {
        return location;
    }

    public void walkRandom()
    {
        if(true)
        {

        }
    }
}
