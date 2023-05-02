/*Name:	Joe Himmelberg
 *Date: 6/6/19
 *Period: 8
 *Teacher: Klus
 *Description: A class that makes a specific type of JButton that knows its own row, column, orientation, color, and whether or not
 *             it has been taken. To be honest, a lot of this became pretty pointless after we got into the game.
 */
package APcs;

import java.awt.Color;

import javax.swing.JButton;


public class GameButton extends JButton
{
    private final String orientation;
    private Color c;
    private boolean taken;
    private int row;
    private int col;
    
    public GameButton(int r, int c1, String o)
    {
        c = Color.WHITE;
        taken = false;
        //changed when constructed
        row = r;
        col = c1;
        orientation = o;
    }
    
    //getters
    public Color getColor()
    {
        return c;
    }
    
    public boolean getTaken()
    {
        return taken;
    }
    
    public String getOrientation()
    {
        return orientation;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public int getRow()
    {
        return row;
    }
    
    //setters
    
    //pre: takes the desired new color for the button
    //post: returns the new color of the vertical button
    public Color setColor(Color x)
    {
        c=x;
        return c;
    }
}
