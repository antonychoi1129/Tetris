
public class IBlock extends Block {
	
	public IBlock()
	{
		super(new Cell(0,3,BlockColor.I_COLOR),
			  new Cell(0,4,BlockColor.I_COLOR),
		      new Cell(0,5,BlockColor.I_COLOR),
		      new Cell(0,6,BlockColor.I_COLOR), 'I', 0);
	}
	
	public void rotate() throws OutOfBoardException 
	{
		Cell temp[] = new Cell[4];
        for(int i=0;i<4;i++)
            temp[i] = new Cell(super.cells[i].getRow(),super.cells[i].getCol(),super.cells[i].getColor());
		
        status = (status+1)%4;
        rotateCell(temp);
        for (int i=0;i<4;i++)
        	if(temp[i].getCol()<0||temp[i].getCol()>9||temp[i].getRow()<0||temp[i].getRow()>19)
        		throw new OutOfBoardException("OutOfBoard");
        
        rotateCell(super.cells);
		
	}
	
	public void rotateCell(Cell[] cell)
	{
		if(status == 1) {
			cell[0].setRow(cell[0].getRow()-1);
			cell[0].setCol(cell[0].getCol()+2);
			cell[1].setCol(cell[1].getCol()+1);
			cell[2].setRow(cell[2].getRow()+1);
			cell[3].setRow(cell[3].getRow()+2);
			cell[3].setCol(cell[3].getCol()-1);
		} else if(status == 2) {
			cell[0].setRow(cell[0].getRow()+2);
			cell[0].setCol(cell[0].getCol()+1);
			cell[1].setRow(cell[1].getRow()+1);
			cell[2].setCol(cell[2].getCol()-1);
			cell[3].setRow(cell[3].getRow()-1);
			cell[3].setCol(cell[3].getCol()-2);
		} else if(status == 3) {
			cell[0].setRow(cell[0].getRow()+1);
			cell[0].setCol(cell[0].getCol()-2);
			cell[1].setCol(cell[1].getCol()-1);
			cell[2].setRow(cell[2].getRow()-1);
			cell[3].setRow(cell[3].getRow()-2);
			cell[3].setCol(cell[3].getCol()+1);
		} else if(status == 0) {
			cell[0].setRow(cell[0].getRow()-2);
			cell[0].setCol(cell[0].getCol()-1);
			cell[1].setRow(cell[1].getRow()-1);
			cell[2].setCol(cell[2].getCol()+1);
			cell[3].setRow(cell[3].getRow()+1);
			cell[3].setCol(cell[3].getCol()+2);
		}
	}
}
