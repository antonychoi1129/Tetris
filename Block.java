
public abstract class Block {
	
	protected Cell[] cells;
	private char shape;
	protected int status;
	
	
	public Block(Cell c1, Cell c2, Cell c3, Cell c4, char shape, int status)
	{
		cells = new Cell[4];
		cells[0] = c1;
		cells[1] = c2;
		cells[2] = c3;
		cells[3] = c4;
	}
	
	public Cell[] getCells()
	{
		return this.cells;
	}
	
	public char getShape() {
		return this.shape;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public void setShape(char shape) {
		this.shape = shape;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void moveleft() throws OutOfBoardException {
		for(int i=0; i<4; i++)
		{
			if(this.cells[i].getCol() == 0)
				throw new OutOfBoardException("OutOfBoardException");
			
		}
		for(int i=0; i < 4; i++)
		{
				this.cells[i].left() ;
		}
			
	}
	
	public void moveright() throws OutOfBoardException {
		for(int i=0; i<4; i++)
		{
			if(this.cells[i].getCol() == 9)
				throw new OutOfBoardException("OutOfBoardException");
			
		}
		
		for(int i=0; i<4 ; i++)
		{	
				this.cells[i].right();	
		}
			
	}
	
	public void movedown() throws OutOfBoardException {
		for(int i=0; i<4; i++)
		{
			if(this.cells[i].getRow() == 19)
				throw new OutOfBoardException("OutOfBoardException");
		}
		
		for(int i=0; i < 4; i++)
		{
				this.cells[i].down();
		}
	}
	
	public abstract void rotate() throws OutOfBoardException;

	
	
	public String toString() {
		return ("[" + this.cells[0].toString() + "; " + this.cells[1].toString() + "; "+ this.cells[2].toString() + "; "
									+ this.cells[3].toString() + ": " + this.shape + ", " + this.status + "]");
	}
}
