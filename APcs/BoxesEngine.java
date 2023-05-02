/*Name:	Joe H, Meghan M, Tyler P
 *Date: 5/6/19
 *Period: 8
 *Teacher: Klus
 *Description: This class contains methods which ensure that the rules of the game are being followed, keep score, keep track of whose
 *             whose turn it is, as well as methods to tell when the game is won, when a box is made, which side of the button on which 
 *             the box was made, update the arrays of buttons, and to reset the game. This class also contains a constructor, getters and
 *             setters.             
 */
package APcs;

import javax.swing.JButton;


public class BoxesEngine
{
    //variables to keep track of the name of the player whose turn it is
    private String [] players = {"blue", "red"};
    private String currentTurn = "blue";
    
    //keeps track in array "players"
    private int colorCount = 0;
    
    //score keeping variables
    private int redScore;
    private int blueScore;
    
    //arrays of the vertical and horizontal buttons in the arrays that make up the game board
    //for each cell, blue = 1, red = 2, empty = 0
    private int[][] vert;
    private int[][] horiz;
    
    //constructor
    public BoxesEngine()
    {
        //makes new arrays of buttons that are the following sizes (these make a game board of size 8 boxes by 8 boxes)
        vert = new int[8][9];
        horiz = new int [9][8];
    }
    
    
    
  //post: completes one player's turn of boxes, to be executed each time a button is clicked
    public void playATurn(String o, int row, int col)
    {        
        updateGrid(o,row,col);
        updateScore(o,row,col);        
    }
    
    
    //pre: o is the orientation of the button taken, row and col are integers representing the row and column of the button taken
    //post: returns the color of the player whose turn it is
    //      Color remains the same if a box is complete, otherwise it is changed
    public String whoseTurn(String orientation, int row, int col)  //what read in?
    {
     //when a box is made, turn doesn't change   
        if(!boxMade(orientation, row, col))
        {
            if(colorCount ==0)
            {
                currentTurn = players[colorCount+1];
                colorCount ++;
            }    
            else
            {
                currentTurn = players[colorCount-1];
                colorCount--;
            }    
        }
 
        return currentTurn;
    }
    
    
    //pre: o is the orientation of the button taken, row and col are integers representing the row and column of the button taken
    //post: returns true if a box is complete with four lines, false if not
    public boolean boxMade(String o, int row, int col) //read
    {
        if(o.equals("Horizontal")) // button clicked horizontal
        {
            if(row==0)
            {
                //check box below
                return (horiz[row+1][col]!=0 && vert[row][col]!=0  && vert[row][col+1]!=0 );
            }
            else if (row == horiz.length-1)
            {
                //check box above
                return (horiz[row-1][col]!=0  && vert[row-1][col]!=0 && vert[row-1][col+1]!=0 );
            }
            else
            {
                //check box below and above
                return ((horiz[row+1][col]!=0  && vert[row][col]!=0  && vert[row][col+1]!=0 )||(horiz[row-1][col]!=0  && vert[row-1][col]!=0  && vert[row-1][col+1]!=0 ));
            }
        }
        else if(o.equals("Vertical")) //button clicked is on the vertical
        {
            if(col==0)
            {
                //check box to right
                return (horiz[row][col]!=0  && horiz[row+1][col]!=0  && vert[row][col+1]!=0 );
                
            }
            else if(col == vert[0].length-1)
            {
                //check box to left
                return (horiz[row][col-1]!=0  && horiz[row+1][col-1]!=0  && vert[row][col-1]!=0 );
            }
            else
            {
                //check boxes right left
                return  ((horiz[row][col]!=0  && horiz[row+1][col]!=0  && vert[row][col+1]!=0 )||(horiz[row][col-1]!=0  && horiz[row+1][col-1]!=0  && vert[row][col-1]!=0 ));
            }

        }
        return false;
    }
    
    
    //pre: o is the orientation of the button taken, row and col are integers representing the row and column of the button taken
    //post: returns a String representing the score of the game in the order blue's score, red's score
    public String updateScore(String o, int row, int col)
    {
        //two boxes are made
        if(whichSide(o,row,col).equals("Left Right")||whichSide(o,row,col).equals("Above Below"))
        {
            if(boxMade(o,row,col))
            {
                if(currentTurn.equals("blue"))
                    blueScore += 2;
                else
                    redScore += 2;
            }
        }
        //one box is made
        else
        {
            if(boxMade(o,row,col))
            {
                if(currentTurn.equals("blue"))
                    blueScore ++;
                else
                    redScore++; 
            }
        }     
        return "Blue: " + blueScore + "\nRed: " + redScore;
    }
    
    
    //pre: o is the orientation of the button taken, row and col are integers representing the row and column of the button taken
    //post: the array vert or horiz (depending on the orientation) is updated as to who owns each button
    public int updateGrid(String o, int row, int col)
    {
        if(o.equals("Horizontal"))
        {
            if(currentTurn.equals("blue"))
            {    
                horiz[row][col] = 1;
                return horiz[row][col];
            }
            else
            {
                horiz[row][col] = 2;
                return horiz[row][col];
            }
        }
        else
        {
            if(currentTurn.equals("blue"))
                {
                    vert[row][col] = 1;
                    return vert[row][col];
                }
            else
                {
                    vert[row][col] = 2;
                    return vert[row][col];
                }
         }
    }
    

    //post: returns true if the game is finished, false otherwise
    public boolean gameIsWon()
    {
        //specific to an 8 by 8 box board
        return redScore + blueScore == 64;
    }
    
    //pre: o is the orientation of the button, r is the row of the button clicked, col is the column index of the button clicked
    //post: returns a string telling whether the box made was above, below, to the left, or to the right of the line clicked
    public String whichSide(String o, int r, int col)
    {
        //check left and right 
        if(o.equals("Vertical"))
        {
            //on far right side
            if(col==0)
            {
                if(boxMade(o,r,col))
                    return "Right";    
            }
            //if on the far right side
            else if(col == vert[0].length-1)
            {
                if(boxMade(o,r,col))
                    return "Left"; 
            }
            //check boxes left and right
            else if((horiz[r][col]!=0  && horiz[r+1][col]!=0  && vert[r][col+1]!=0 )&&(horiz[r][col-1]!=0  && horiz[r+1][col-1]!=0  && vert[r][col-1]!=0 ))
                return "Left Right";
            //check box to right
            else if(horiz[r][col]!=0  && horiz[r+1][col]!=0  && vert[r][col+1]!=0)
                return "Right";
            else
                return "Left";       
        }
        else
        {
            //on the top
            if(r==0)
            {
                if(boxMade(o,r,col))
                    return "Below";
            }
            //on bottom
            else if (r == horiz.length-1)
            {
                if(boxMade(o,r,col))
                    return "Above";
            }
            //check above and below
            else if((horiz[r+1][col]!=0  && vert[r][col]!=0  && vert[r][col+1]!=0 )&&(horiz[r-1][col]!=0  && vert[r-1][col]!=0  && vert[r-1][col+1]!=0 ))
                return "Above Below";
            //check below
            else if(horiz[r+1][col]!=0 && vert[r][col]!=0  && vert[r][col+1]!=0)
                return "Below";
            else if(horiz[r-1][col]!=0  && vert[r-1][col]!=0 && vert[r-1][col+1]!=0 )
                return "Above";
        }
        return "";
    }
    
    //post: all variables are set back to normal and the game can begin again
    public void reset()
    {
        currentTurn = "blue";
        colorCount = 0;
        redScore = 0;
        blueScore = 0;
        vert = new int[8][9];
        horiz = new int [9][8];
    }
    
    //getters
    public String getCurrentTurn()
    {
        return currentTurn;
    }
    
    public int getRedScore()
    {
        return redScore;
    }
    
    public int getBlueScore()
    {
        return blueScore;
    }
    
    //setters
    public void setBlueScore(int n)
    {
        blueScore = n;
    }
    
    public void setRedScore(int n)
    {
        redScore = n;
    }
}
