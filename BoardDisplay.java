
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



public class BoardDisplay extends JPanel 
        implements ActionListener {

    private final int DELAY = 600;

    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isStarted = false;
    private boolean isPaused = false;


    private JLabel statusbar;
    Board board;
    private int squareWidth() { return (int) getSize().getWidth() / Board.BOARD_WIDTH; }
    private int squareHeight() { return (int) getSize().getHeight() / Board.BOARD_HEIGHT; }
    

    
    
    public BoardDisplay(Tetris parent, Board board) {

    	this.board=board;
        initBoard(parent);
    }
    
    private void initBoard(Tetris parent) {
        
       setFocusable(true);
       timer = new Timer(DELAY, this);
       timer.start(); 

       statusbar =  parent.getStatusBar();
       statusbar.setText("Score: "+(board.numFullLinesRemoved()*100));
       addKeyListener(new TAdapter());
       board.clear();          
    }


    public void start()  {
        
        if (isPaused)
            return;

        isStarted = true;
        isFallingFinished = false;
        board.clear();

        nextPiece();
        timer.start();
    }

    private void pause()  {
        
        if (!isStarted)
            return;

        isPaused = !isPaused;
        
        if (isPaused) {
            
            timer.stop();
            statusbar.setText("paused");
        } else {
            
            timer.start();
            statusbar.setText("Score: "+(board.numFullLinesRemoved()*100));
        }
        
        repaint();
    }
    
   

    private void dropDown() {
        while (board.oneLineDown()) {}  
    	
        removeFullRows();
    }


    private void removeFullRows() {
    	board.blockLanded();
        board.removeFullRows();
        isFallingFinished = true;
        statusbar.setText("Score: "+(board.numFullLinesRemoved()*100));
        repaint();        
    }

    private void gameOver()
    {
        timer.stop();
        isStarted = false;
        statusbar.setText("Game Over");     
    }
   
    private void nextPiece()  {
        
    	if (board.newBlock())
    	{
    		repaint();
    		return;
    	}
    	gameOver();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (isFallingFinished) {            
            isFallingFinished = false;
            nextPiece();
        } else {      
        
        	if (!board.oneLineDown())
        	{     
        		removeFullRows();

        	}
        	
        }
       repaint();
    }
    
 private void doDrawing(Graphics g) {
        
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - Board.BOARD_HEIGHT * squareHeight();

        for (int i = 0; i < Board.BOARD_HEIGHT; ++i) {
            
            for (int j = 0; j < Board.BOARD_WIDTH; ++j) {
               
		//your code starts from here
		//
		//
		
		
		//get the color of each cell of the board
                BlockColor c = board.blockAt(i,j);
	        //you code ends
    
		if (c != BlockColor.NO_COLOR)
                    drawSquare(g, 0 + j * squareWidth(),
                               boardTop + i * squareHeight(), c);
            
	    
	    }
        }

	//draw the active block
	//you code start here
	//
	//
       
        
        
        
        //if the active is not null, color the board according to its cells with the drawSquare method. 
        if(board.activeBlock() !=  null)
        {
        	Cell[] cells = new Cell[4];
        	cells = board.activeBlock().getCells();
       
        	for(int i=0; i<4; i++)
        			drawSquare(g, 0+cells[i].getCol()* squareWidth(),
        					boardTop +cells[i].getRow()* squareHeight(), cells[i].getColor());
        }
       
	


	//your code ends here. 
    }

    @Override
    public void paintComponent(Graphics g) { 

        super.paintComponent(g);
        doDrawing(g);
    }

    private void drawSquare(Graphics g, int x, int y, BlockColor shape)  {
        
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                         x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                         x + squareWidth() - 1, y + 1);

    }
    
    public void checkInput() throws InvalidInputException
	 {
  
   		throw new InvalidInputException("InvalidInputException");
	 }

    class TAdapter extends KeyAdapter {
        
         @Override
         public void keyPressed(KeyEvent e) {

             if (!isStarted || board.activeBlock() == null) {  
            	 return;
             }

             int keycode = e.getKeyCode();

             if (keycode == 'P') {
                 pause();
                 return;
             }

             if (isPaused)
                 return;

             boolean status=false;
             
             switch (keycode) {
             
             case KeyEvent.VK_A:
                 status=board.moveLeft();
                 break;
                 
             case KeyEvent.VK_D:
            	 status=board.moveRight();
            	 break;
            
             case KeyEvent.VK_W:
            	 status=board.rotate();
                 break;
                 
             case KeyEvent.VK_S:
            	 status = true;
                 dropDown();
                 break;
                 
             case KeyEvent.VK_E:
            	 System.exit(0);
            	
             default:
            	 try {
            		 checkInput();
            	 }catch(InvalidInputException a)
            	 {
            		 
            	 }
             }

           
            repaint();
             
         }
     }
   
}
