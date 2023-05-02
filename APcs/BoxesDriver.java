/*Name:	Joe H, Meghan M, Tyler P
 *Date: 6/6/19
 *Period: 8
 *Teacher: Klus 
 *Description: Activates the game and creates a new instance of the BoxesEngine class
 */
package APcs;


public class BoxesDriver
{

    public static void main(String[] args)
    {
        BoxesEngine board = new BoxesEngine();
        GameGUI frame = new GameGUI(board);
        frame.setVisible(true);
    }

}
