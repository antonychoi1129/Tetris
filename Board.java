public class Board {
	
	static int BOARD_HEIGHT = 20;
	static int BOARD_WIDTH = 10;
	private BlockColor [][] cells;
	private Block activeBlock = null;
	private int sum = 0;
	
	
	public Board()
	{
		cells = new BlockColor [BOARD_HEIGHT][BOARD_WIDTH];
		for(int i=0; i<BOARD_HEIGHT; i++)
			for(int j=0; j<BOARD_WIDTH; j++)
				cells[i][j] = BlockColor.NO_COLOR;
	}
	
	public Block activeBlock()
	{
		return this.activeBlock;
	}
    
	public BlockColor blockAt(int x, int y)
    {
    	return this.cells[x][y];
    }

	public void clear()
	{
		for(int i=0; i<BOARD_HEIGHT; i++)
			for(int j=0; j<BOARD_WIDTH; j++)
				cells[i][j] = BlockColor.NO_COLOR;
		
		activeBlock = null;
	}
	
	public void blockLanded()
	{
		Cell [] activeCell = activeBlock().getCells();
		for(int i=0; i<4; i++)
			cells[activeCell[i].getRow()][activeCell[i].getCol()] = activeCell[i].getColor();
		this.activeBlock = null;
	}
	
	public boolean canMove()
	{
		if(this.oneLineDown()) {
        	return true;
    	}
    	return false;
	}
	
	public boolean rotate()
	{
		try {
			activeBlock.rotate();
			Cell[] activeCell = activeBlock.getCells();
			for(int i=0; i<4; i++)
			{
				if(blockAt(activeCell[i].getRow(),activeCell[i].getCol())!= BlockColor.NO_COLOR)
				{
					activeBlock.rotate();
					activeBlock.rotate();
					activeBlock.rotate();
					return false;
				}
    				
			}
			
		}
		catch(OutOfBoardException e)
		{
			return false;
		}
		return true;
	}
	
	public boolean oneLineDown()
	{
		try {
    		
    		Cell [] activeCell = activeBlock().getCells();
    		for(int i=0; i<4; i++)
    		{
    			if(activeCell[i].getRow()+1 <= 19)
    			if(blockAt(activeCell[i].getRow()+1,activeCell[i].getCol())!= BlockColor.NO_COLOR)
    				return false;
    		}
    		activeBlock.movedown();	
    	}
    	catch(OutOfBoardException e)
    	{	
    		return false;
    	}
    	return true;
	}
	
    public boolean moveLeft()
    {
    	try {
    		
    		Cell [] activeCell = activeBlock().getCells();
    		for(int i=0; i<4; i++)
    		{
    			if(activeCell[i].getCol()-1 >=0)
    				if(blockAt(activeCell[i].getRow(),activeCell[i].getCol()-1)!= BlockColor.NO_COLOR)
    					return false;
    			
    		
    		}
    		activeBlock.moveleft();	
    	}
    	catch(OutOfBoardException e)
    	{
    		return false;
    	}
    	return true;
    }
    
    public boolean moveRight()
    {
    	try {
    		Cell [] activeCell = activeBlock().getCells();
    		for(int i=0; i<4; i++)
    		{
    			if(activeCell[i].getCol()+1 <= 9)
    				if(blockAt(activeCell[i].getRow(),activeCell[i].getCol()+1)!= BlockColor.NO_COLOR)
    					return false;
    			
    		
    		}
    		activeBlock.moveright();	
    	}
    	catch(OutOfBoardException e)
    	{
    		return false;
    	}
    	return true;
    }
    
    public boolean newBlock() 
    {
    	this.activeBlock = randomBlock();
    	if(canMove()) {
        	return true;
    	}
    	return false;
    }
    
    public Block randomBlock()
    {
    	int ran = (int)Math.floor(Math.random()*7);
    	switch(ran) 
    	{
    		case 0: return new IBlock();
    		case 1: return new JBlock();
    		case 2: return new LBlock();
    		case 3: return new OBlock();
    		case 4: return new SBlock();
    		case 5: return new TBlock();
    		case 6: return new ZBlock();
    		default : return new IBlock();
    	}
    }
    
    public int removeFullRows()
    {
    	int i=19;
    	int n =0;
    		
    	while(i>=0)
    	{
    		for(int j=0; j<10; j++)
    		{
    			if(blockAt(i,j) != BlockColor.NO_COLOR)
    			{
    				if(j == 9)
        			{
        				n++;
        				for(int k=0; k<10; k++)
        				{
        					cells[i][k] = BlockColor.NO_COLOR;
        				}
        				for(int z=i; z>0; z--)
        					for(int k=0; k<10; k++)
            				{
            					cells[z][k] = cells[z-1][k];
            				}
        			}
    			}
    			else
    			{
    				i--;
    				break;
    			}
    		}	
    	}
    	this.sum += n;
    	return n;
    }
    
    public int numFullLinesRemoved()
    {
    	return this.sum;
    }
}
