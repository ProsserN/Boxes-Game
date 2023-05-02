/*Name:	Joe, Meghan, Tyler
 *Date: 5/3/19
 *Period: 8
 *Teacher: Klus
 *Description: This class creates the display that shows the game being played. It also carries out the correct string of actions that 
 *             should be followed every time a button is clicked. it has variables (arrayLists) of horizontal and vertical buttons, 
 *             a variable for the instance of the engine, JLabels for the score boards and win message, JButtons for the lines and the 
 *             new game button. There is a constructor, an initiate method, and an action performed method.
 */
package APcs;

//imports
    import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

    import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
    
    
   
public class GameGUI extends JFrame implements ActionListener
    {
    
        //whole window
        private JPanel pane;
        
        //arrays of the buttons representing the lines drawn
        private List<GameButton> horizButtons;
        private List<GameButton> vertButtons;
        
        //represents the boxes themselves
        private JLabel [][] labels;
        
        //an instance of boxesEngine
        private BoxesEngine board;
        
        //scoreBoards
        private JLabel scoreBoardRed;
        private JLabel scoreBoardBlue;
        
        private JButton restartButton;
        
        //displayed at the conclusion of the game
        private JLabel winMessage;
       
        //constructor
        public GameGUI(BoxesEngine gBoard)
        {
            board = gBoard;
            initiate();
        }
        
        public void initiate()
        {    
            board.setBlueScore(0);
            board.setRedScore(0);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Boxes");//Sets title of the window opened to "Boxes"
            //this.setSize(0,0)
            setBounds(100, 100, 1465, 900);//Sets size of the window opened and position of where the window opens
            
            horizButtons = new ArrayList<GameButton>();
            vertButtons  = new ArrayList<GameButton>();
            labels = new JLabel [8][8];
            
            pane = new JPanel();
            
            pane.setBackground(new Color(200,200,200));//Sets background color of window
            pane.setLayout(null);//Number of boxes (X-axis, Y-Axis)
           
            for(int i=0;i<8;i++)//First set of boxes Set to 8 
            {
                for(int a=0;a<9;a++)//Second set of boxes Set to 5
                {
                    
                    GameButton hButton = new GameButton(a,i,"Horizontal");//
                    horizButtons.add(hButton);
                    hButton.setBounds(i*100+70, a*100+20, 80, 20);//(i=# in for loop, 100= number of pixels 70= number of pixels shifted each time, 80= Length, 20=Width)
                    pane.add(hButton);
                    hButton.setBackground(Color.BLACK);
                    hButton.setBorder(new LineBorder(Color.BLACK));
                    hButton.addActionListener(this);
                    hButton.setEnabled(true);

                }
            }
            
            for(int i=0;i<9;i++)//First set of boxes Set to 8 
            {
                for(int a=0;a<8;a++)//Second set of boxes Set to 5
                {
            
                    GameButton vButton = new GameButton(a,i,"Vertical");//
                    vertButtons.add(vButton);
                    vButton.setBounds(i*100+50, a*100+40, 20, 80);
                    pane.add(vButton);
                    vButton.setBackground(Color.BLACK);
                    vButton.setBorder(new LineBorder(Color.BLACK));
                    vButton.addActionListener(this);
                    vButton.setEnabled(true);
                }

            }
            
            for(int i=0;i<8;i++)//First set of boxes Set to 8 
            {
                for(int a=0;a<8;a++)//Second set of boxes Set to 5
                {
            
                    JLabel l = new JLabel();//
                    labels[i][a]= l;
                    l.setBounds(i*100+70, a*100+40,80, 80);
                    pane.add(l);
                    l.setOpaque(true);
                    l.setBackground(new Color(245,245,245));
                    
                    
                }

            }
            this.add(pane);
            
            //Scoreboards
            scoreBoardRed = new JLabel();
            scoreBoardBlue = new JLabel();
            
            //score board for red is formatted
            scoreBoardRed.setBounds(920,220,480,200);
            pane.add(scoreBoardRed);
            scoreBoardRed.setOpaque(true);
            scoreBoardRed.setBackground(Color.WHITE);
            scoreBoardRed.setText("Red: ");
            scoreBoardRed.setHorizontalAlignment(scoreBoardRed.CENTER);
            scoreBoardRed.setVerticalAlignment(scoreBoardRed.CENTER);
            scoreBoardRed.setBorder(new LineBorder(Color.RED, 5));
            scoreBoardRed.setFont(new Font("Times New Roman", Font.BOLD, 50));
            scoreBoardRed.setForeground(Color.red);
            
            //score board for blue is formatted
            scoreBoardBlue.setBounds(920,430,480,200);
            pane.add(scoreBoardBlue);
            scoreBoardBlue.setOpaque(true);
            scoreBoardBlue.setBackground(Color.WHITE);
            scoreBoardBlue.setText("Blue: ");
            scoreBoardBlue.setHorizontalAlignment(scoreBoardBlue.CENTER);
            scoreBoardBlue.setVerticalAlignment(scoreBoardBlue.CENTER);   
            scoreBoardBlue.setBorder(new LineBorder(Color.BLUE, 5));
            scoreBoardBlue.setFont(new Font("Times New Roman", Font.BOLD, 50));
            scoreBoardBlue.setForeground(Color.blue);
            
            //restart game button
            restartButton = new JButton();
            
            restartButton.setBounds(1015,675,280,100);
            pane.add(restartButton);
            restartButton.setText("New Game");
            restartButton.setBackground(Color.WHITE);
            restartButton.setBorder(new LineBorder(Color.BLACK, 5));
            restartButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
            restartButton.addActionListener(this);
            
            //win message label
            winMessage = new JLabel();
            pane.add(winMessage);
            winMessage.setBounds(1015, 75, 280, 100);
            winMessage.setBackground(Color.WHITE);
            winMessage.setHorizontalAlignment(scoreBoardRed.CENTER);
            winMessage.setVerticalAlignment(scoreBoardRed.CENTER); 
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {  
            //checks if the button clicked is a game button (a line)
            if(e.getSource() instanceof GameButton)
            {
                GameButton clicked = (GameButton)(e.getSource());

            
                String o = clicked.getOrientation();
                int row = clicked.getRow();
                int col = clicked.getCol();
                
                
                if(board.getCurrentTurn().equals("blue"))
                    clicked.setBackground(Color.BLUE);
                else
                    clicked.setBackground(Color.RED);
                
                board.playATurn(o, row, col);
                board.whoseTurn(o, row, col);
                
                Color c;
                
                if(board.getCurrentTurn().equals("blue"))
                    c = new Color(174,221,252);
                else
                    c = Color.PINK;
                    
                if(board.boxMade(o, row, col))
                {
                    //box made is to left button clicked
                   if(board.whichSide(clicked.getOrientation(), clicked.getRow(), clicked.getCol()).equals("Left"))
                   {
                       labels[col-1][row].setBackground(c); 
                   }
                   //box made is to right button clicked
                   else if(board.whichSide(clicked.getOrientation(), clicked.getRow(), clicked.getCol()).equals("Right"))
                   {
                       labels[col][row].setBackground(c);
                   }
                   //boxes made are on both sides (left and right) button clicked
                   else if(board.whichSide(clicked.getOrientation(), clicked.getRow(), clicked.getCol()).equals("Left Right"))
                   {
                       labels[col][row].setBackground(c);
                       labels[col-1][row].setBackground(c);
                   }
                   //box made is above button clicked
                   else if(board.whichSide(clicked.getOrientation(), clicked.getRow(), clicked.getCol()).equals("Above"))
                   {
                       labels[col][row-1].setBackground(c);
                   }
                   //box made is below button clicked
                   else if(board.whichSide(clicked.getOrientation(), clicked.getRow(), clicked.getCol()).equals("Below"))
                   {
                       labels[col][row].setBackground(c);
                   }
                   //boxes made are above and below button clicked
                   else if(board.whichSide(clicked.getOrientation(), clicked.getRow(), clicked.getCol()).equals("Above Below"))
                   {
                       labels[col][row].setBackground(c);
                       labels[col][row-1].setBackground(c);
                   }
                }
                
                clicked.setEnabled(false);
                scoreBoardRed.setText("Red: " + board.getRedScore());
                scoreBoardBlue.setText("Blue: " + board.getBlueScore());
                
                if(board.gameIsWon())
                {
                    winMessage.setOpaque(true);
                    if(board.getRedScore()>board.getBlueScore())
                    {
                        winMessage.setBorder(new LineBorder(Color.RED, 5));
                        winMessage.setText("RED WINS!");
                        winMessage.setForeground(Color.RED);
                        winMessage.setFont(new Font("Stencil", Font.BOLD, 30));
                    }
                    else if(board.getBlueScore()>board.getRedScore())
                    {
                        winMessage.setBorder(new LineBorder(Color.BLUE, 5));
                        winMessage.setText("BLUE WINS!");
                        winMessage.setForeground(Color.BLUE);
                        winMessage.setFont(new Font("Stencil", Font.BOLD, 30));
                    }
                    else
                    {
                        winMessage.setBorder(new LineBorder(Color.BLACK, 5));
                        winMessage.setText("IT'S A TIE!");
                        winMessage.setFont(new Font("Stencil", Font.BOLD, 30));
                    }
                        
                }
                
            }
            //happens if the button clicked is not part of the game, but rather the 
            else
            {
                JButton clicked = (JButton)(e.getSource());
            
                if(clicked.equals(restartButton))
                {
                    this.remove(pane);
                    board.reset();
                    initiate();
                    
                }
                
            }
        
        }
        

    }
