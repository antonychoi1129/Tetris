
public class ZBlock extends Block{
	ZBlock(){
		super(new Cell(0,3,BlockColor.Z_COLOR),
				  new Cell(0,4,BlockColor.Z_COLOR),
			      new Cell(1,4,BlockColor.Z_COLOR),
			      new Cell(1,5,BlockColor.Z_COLOR), 'Z', 0);
	}
		
	public void rotate() throws OutOfBoardException 
	{
		Cell temp[] = new Cell[4];
		
        for(int i=0;i<4;i++)
            temp[i] = new Cell(super.cells[i].getRow(),super.cells[i].getCol(),super.cells[i].getColor());
        
        for (int i=0;i<4;i++)
        	if(i==2)
                continue;
            else
            {
            	rotateCell(temp[i]);
                if(temp[i].getCol()<0||temp[i].getCol()>9||temp[i].getRow()<0||temp[i].getRow()>19)
                    throw new OutOfBoardException("OutOfBoard");
            }
        
        for (int i = 0; i < 4; i++) 
           if (i == 2) 
        	   continue;
           else 
               rotateCell(super.cells[i]);
	}

	public void rotateCell(Cell cell)
	{
		cell.setRow(cell.getRow() - super.cells[2].getRow());
		cell.setCol(cell.getCol() - super.cells[2].getCol());
		int rowTmp = cell.getRow();
		int colTmp = cell.getCol();
		cell.setRow(((int)(rowTmp * Math.cos(Math.toRadians(-90))) - (int)(colTmp * Math.sin(Math.toRadians(-90)))));
		cell.setCol(((int)(rowTmp * Math.sin(Math.toRadians(-90))) + (int)(colTmp * Math.cos(Math.toRadians(-90)))));
		cell.setRow(cell.getRow() + super.cells[2].getRow());
	 	cell.setCol(cell.getCol() + super.cells[2].getCol());
	}

}
