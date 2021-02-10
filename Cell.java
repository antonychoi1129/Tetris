
public class Cell {
	
	private int row;
	private int col;
	private BlockColor color;
	
	Cell(int row, int col, BlockColor color){
		setRow(row);
		setCol(col);
		setColor(color);
	};
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public BlockColor getColor() {
		return this.color;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public void setColor(BlockColor color) {
		this.color = color;
	}

	public void left() {
		this.col -= 1;
	}
	
	public void right() {
		this.col += 1;
	}
	
	public void down() {
		this.row += 1;
	}
	
	public String toString() {
		return ("(" + this.row + ", " + this.col + ", " + this.color + ")");
		
	}
}
