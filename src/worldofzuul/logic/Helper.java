/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.logic;

/**
 * The type Helper.
 *
 * @author John
 */
// Helper class
public class Helper extends RoomContent
{
    private String name;
    private String answer;
    private String description;

    /**
     * Instantiates a new Helper.
     *
     * @param name        the name
     * @param answer      the answer
     * @param description the description
     */
    
    public Helper()
            {
                
            }
    public Helper(String name, String answer, String description)
    {
        this.name = name;
        this.answer = answer;
        this.description = description;
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

}
