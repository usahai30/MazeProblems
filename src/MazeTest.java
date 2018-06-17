import java.util.LinkedList;
import java.util.List;

public class MazeTest {
	public static void main(String args[])
	{
		String mazeFile = "data/maze1.maze";
		Maze maze = new Maze();
		MazeLoader.loadMaze(mazeFile, maze);
		Coordinates start = new Coordinates(0,0);
		Coordinates goal = new Coordinates(3,3);
		List<MazeNode> path = new LinkedList<MazeNode>();
		
		maze.printMaze();
		
		System.out.println("\nDepth-First Search -");
		path = maze.dfs(start, goal);
		maze.setPath(path);
		maze.printMaze();
		maze.clearPath();
		
		System.out.println("\nBreadth-First Search -");
		path = maze.bfs(start, goal);
		maze.setPath(path);
		maze.printMaze();
		path.clear();
		
		
		
	}
}
