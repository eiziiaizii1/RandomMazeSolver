
public class RandomMazeSolver {

	RandomMazeSolver() {
		
	}
	
	//Robot enters the maze depending on given experimentCount, and moves depending on maximumTrialCount
	public static SolverResult solve(World world, int experimentCount) {
		SolverResult solverResult = new SolverResult();

		int trialFactor = 100;
		int maximumTrialCount = trialFactor * world.getArea();
		
		for (int i=0; i<experimentCount; i++) {
			if (i % 256 == 0) {
				System.out.print(".");
			}
			
			executeExperiment(world, maximumTrialCount);
			
			if (world.isRobotDead())
				solverResult.incrementDead();
			else {
				if (world.isRobotAtEnd())
					solverResult.incrementSuccess();
				else
					solverResult.incrementFailure();
			}
		}
		
		System.out.println("\n");
		
		return solverResult;
	}
	
	private static void executeExperiment(World world, int maximumTrialCount) {
		// student code
		
		world.restartTheWorld();
		
		// robot moves randomly until it is dead, reached the end, or its trails end
		for (int i = 0; i < maximumTrialCount; i++) {
			world.moveRobotRandomly();
			
			if(world.isRobotAtEnd() || world.isRobotDead()) {
				break;
			}
		}
		
	}
	
}
