
public class OBlock extends Block{
	OBlock(){
		super(new Cell(0,3,BlockColor.O_COLOR),
				  new Cell(0,4,BlockColor.O_COLOR),
			      new Cell(1,3,BlockColor.O_COLOR),
			      new Cell(1,4,BlockColor.O_COLOR), 'O', 0);
	}
	
	public void rotate() throws OutOfBoardException {}
}
