import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Coordinates{
	int row;
	int col;
	
	public Coordinates(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
}

public class Maze {
	private MazeNode[][] nodes;
	private int height;
	private int width;
	
	private final int DEFAULT_SIZE = 10;
	
	public Maze()
	{
		this.width = DEFAULT_SIZE;
		this.height = DEFAULT_SIZE;
		nodes = new MazeNode[height][width];		
	}
	
	public Maze(int width, int height)
	{
		this.width = width;
		this.height = height;
		nodes = new MazeNode[height][width];		
	}
	
	public void initialise(int width, int height)
	{
		this.width = width;
		this.height = height;
		nodes = new MazeNode[height][width];		
	}
	
	public void addNode(int row, int col)
	{
		nodes[row][col] = new MazeNode(row,col);
	}
	
	public void linkEdges() {
		int height = nodes.length;
		int width = nodes[0].length;
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				if(nodes[row][col]!=null)
				{
					//Upper Node
					if(row>0 && nodes[row-1][col]!=null)
						nodes[row][col].addNeighbors(nodes[row-1][col]);
					
					//Left Node
					if(col>0 && nodes[row][col-1]!=null)
						nodes[row][col].addNeighbors(nodes[row][col-1]);
					
					//Down Node
					if(row<height-1 && nodes[row+1][col]!=null)
						nodes[row][col].addNeighbors(nodes[row+1][col]);
					
					//Right Node
					if(col<width-1 && nodes[row][col+1]!=null)
						nodes[row][col].addNeighbors(nodes[row][col+1]);
				}
			}
		}
	}
	
	public void printMaze(){	
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				if (nodes[r][c] == null) {
					System.out.print('*');
				} else {
					System.out.print(nodes[r][c].getDispChar());
				}
			}
			System.out.print("\n");
		}
	}
	
	public void setPath(List<MazeNode> list)
	{
		for(MazeNode each: list)
		{
			if(each == list.get(0))
				each.setDispChar(MazeNode.START);
			else if(each == list.get(list.size()-1))
				each.setDispChar(MazeNode.GOAL);
			else
				each.setDispChar(MazeNode.PATH);
		}
	}
	
	public void clearPath() {
		for (int r = 0; r < nodes.length; r++) {
			for (int c = 0; c < nodes[r].length; c++) {
				MazeNode n = nodes[r][c];
				if (n != null) {
					n.setDispChar(MazeNode.EMPTY);
				}
			}
		}
	}
	
	public List<MazeNode> dfs(Coordinates st, Coordinates go){
		
		MazeNode start = nodes[st.row][st.col];
		MazeNode goal = nodes[go.row][go.col];
		
		if(start==null || goal==null)
		{
			System.out.println("Start or Goal is Null. No Path exists!");
			return null;
		}
		
		HashMap<MazeNode, MazeNode> parentMap = new HashMap<MazeNode, MazeNode>();
		HashSet<MazeNode> visited = new HashSet<MazeNode>();
		Stack<MazeNode> stack = new Stack<MazeNode>();
		stack.push(start);
		
		boolean found = false;
		
		while(!stack.empty())
		{
			MazeNode curr = stack.pop();
			if(curr==goal)
			{
				found = true;
				break;
			}
			
			for(MazeNode next: curr.getNeighbors())
			{
				if(!visited.contains(next))
				{
					visited.add(next);
					//child, parent
					parentMap.put(next, curr);
					stack.push(next);
				}
			}
		}
		
		if (!found) {
			System.out.println("No path exists");
			return new LinkedList<MazeNode>();
		}
		
		LinkedList<MazeNode> path = new LinkedList<MazeNode>();
		
		MazeNode curr = goal;
		while(curr!=start)
		{
			path.addFirst(curr);
			curr = parentMap.get(curr);
		}
		path.addFirst(curr);
		
		return path;
	}	
	
public List<MazeNode> bfs(Coordinates st, Coordinates go){
		
		MazeNode start = nodes[st.row][st.col];
		MazeNode goal = nodes[go.row][go.col];
		
		if(start==null || goal==null)
		{
			System.out.println("Start or Goal is Null. No Path exists!");
			return null;
		}
		
		HashMap<MazeNode, MazeNode> parentMap = new HashMap<MazeNode, MazeNode>();
		HashSet<MazeNode> visited = new HashSet<MazeNode>();
		Queue<MazeNode> queue = new LinkedList<MazeNode>();
		queue.add(start);
		
		boolean found = false;
		
		while(!queue.isEmpty())
		{
			MazeNode curr = queue.remove();
			if(curr==goal)
			{
				found = true;
				break;
			}
			
			for(MazeNode next: curr.getNeighbors())
			{
				if(!visited.contains(next))
				{
					visited.add(next);
					//child, parent
					parentMap.put(next, curr);
					queue.add(next);
				}
			}
		}
		
		if (!found) {
			System.out.println("No path exists");
			return new LinkedList<MazeNode>();
		}
		
		LinkedList<MazeNode> path = new LinkedList<MazeNode>();
		
		MazeNode curr = goal;
		while(curr!=start)
		{
			path.addFirst(curr);
			curr = parentMap.get(curr);
		}
		path.addFirst(curr);
		
		return path;
	}	

}
