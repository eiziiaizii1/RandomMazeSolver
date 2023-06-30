import java.util.Random;

public class World {
	private final static Random rand = new Random();
	private final int initialLifeCount = 100;
	private int robotLifeCount;
	private Location robot = null;
	private final Location start;
	private final Location end;
	private final double holeProbability;
	private final double wallProbability;

	public enum CellType {
		Wall,
		Corridor;
		
		// if CellType is Wall prints "#", if not prints "." 
		@Override
		public String toString() {
			return this == Wall ? "#" : ".";
		}
		
		// returns a random CellType according to wallProbability parameter
		static CellType random(double wallProbability) {
			return (rand.nextDouble() < wallProbability ? Wall : Corridor);
		}
	}
	
	final CellType[][] cells;

	// Constructs the "world"
	public World(int width, int height, double holeProbability, double wallProbability, Location start, Location end) {
		this.holeProbability = holeProbability;
		this.wallProbability = wallProbability;
		this.start = start;
		this.end = end;
						
		cells = new CellType[height][width];
		
		restartTheWorld();		
	}
	
	public void restartTheWorld() {
		createRandomMaze(holeProbability, wallProbability);
		rebootTheRobot();		
	}
	
	// creates a maze according to given probabilities
	private void createRandomMaze(double holeProbability, double wallProbability) {
		for (int y=0; y<cells.length; y++) {
			CellType[] row = cells[y];
			
			for (int x=0; x<row.length; x++) {
			// if current location is start or end of the maze sets current cell as "Corridor"
				if (start.equals(x, y) || end.equals(x, y))
					row[x] = CellType.Corridor;
				else {
					// if holeProbability is higher than a random number, sets a random cellType
					// according to wallProbability, if not cell becomes null(hole) 
					if (rand.nextDouble() > holeProbability) {
						row[x] = CellType.random(wallProbability);
					}else
						row[x]=null;
				}
			}
		}		
	}
	
	// getter method which returns area of maze
	public int getArea() {
		return cells.length * cells[0].length;
	}
	
	// revives the robot at the start position
	private void rebootTheRobot() {
		robotLifeCount = initialLifeCount;
		
		this.robot = new Location(start);
	}
	
	public boolean isRobotDead() {
		return (robotLifeCount <= 0);
	}
	
	public boolean isRobotAtStart() {
		return !isRobotDead() && robot.equals(start);
	}
	
	public boolean isRobotAtEnd() {
		return !isRobotDead() && robot.equals(end);
	}
	
	// returns CellType according to robot's location
	public CellType robotAt() {
		return robot == null ? null : cells[robot.y][robot.x];
	}
		
	public void moveRobotRandomly() {
		int deltaX, deltaY;
		// used do-while loop to be sure that sum of deltas are 1 (so robot moves 1 cell) 
		do {
			deltaX = rand.nextInt(-1, 2);
			deltaY = rand.nextInt(-1, 2);
		}
		while (Math.abs(deltaX) + Math.abs(deltaY) != 1);
		
		int newX = robot.x + deltaX;
		int newY = robot.y + deltaY;
		
		// if movement is within the range canMove becomes true
		boolean canMove = (newY >= 0 && newY < cells.length &&
						   newX >= 0 && newX < cells[newY].length &&
						   cells[newY][newX] != CellType.Wall);
		
		// if movement is available changes the robot's location
		if (canMove) {
			robot.x = newX;
			robot.y = newY;
			
			// null is a hole, if robot falls it lifeCount decreases by 1
			if (cells[robot.y][robot.x] == null) {
				robotLifeCount--;
			}
		}
	}
	
	// returns a string representation of "world": hole="x", robotPos="*", start="S", end="E"
	// Corridors="." and Walls="#" (these two symbols comes from 'enum CellType')
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int y=0; y<cells.length; y++) {
			CellType[] row = cells[y];

			for (int x=0; x<row.length; x++) {		
				if (robot != null && robot.equals(x, y))
					sb.append("*");
				else {
					if (row[x] == null)
						sb.append("x");
					else {
						if (start.equals(x, y))
							sb.append("S");
						else
							if (end.equals(x, y))
								sb.append("E");
							else
								sb.append(row[x]);
					}
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
}
