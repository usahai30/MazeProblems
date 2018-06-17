import java.util.LinkedList;
import java.util.List;

public class MazeNode {
	
	private int row;
	private int col;
	private char dispChar;
	private List<MazeNode> neighbors;
	
	public static final char EMPTY = '-';
	public static final char PATH = 'o';
	public static final char START = 'S';
	public static final char GOAL = 'G';
	
	public MazeNode(int row, int col)
	{
		this.row = row;
		this.col = col;
		neighbors = new LinkedList<MazeNode>();
		setDispChar(EMPTY);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public char getDispChar() {
		return dispChar;
	}
	public void setDispChar(char dispChar) {
		this.dispChar = dispChar;
	}
	
	public List<MazeNode> getNeighbors() {
		return neighbors;
	}
	public void addNeighbors(MazeNode neighbor) {
		this.neighbors.add(neighbor);
	}
}
