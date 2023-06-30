
public class SolverResult {
	private int deadCount = 0;
	private int failureCount = 0;
	private int successCount = 0;
	
	// returns total count (getter)
	public int getTotal() {
		return deadCount + failureCount + successCount;
	}
	
	public void incrementDead() {
		deadCount++;
	}
	
	// getter method that returns deadCount
	public int getDeadCountCount() {
		return deadCount;
	}
	
	// getter method that returns DeadPercentage
	public double getDeadPercentage() {
		int total = getTotal();
		
		return total > 0 ? (100.0 * deadCount) / total : 0.0;
	}	
	
	public void incrementFailure() {
		failureCount++;
	}
	
	// getter method that returns failureCount
	public int getFailureCount() {
		return failureCount;
	}
	
	// getter method that returns FailurePercentage
	public double getFailurePercentage() {
		int total = getTotal();
		
		return total > 0 ? (100.0 * failureCount) / total : 0.0;
	}	
	
	public void incrementSuccess() {
		successCount++;
	}
	
	// getter method that returns successCount
	public int getSuccessCount() {
		return successCount;
	}	
	
	// getter method that returns SuccessPercentage
	public double getSuccessPercentage() {
		int total = getTotal();
		
		return total > 0 ? (100.0 * successCount) / total : 0.0;
	}	
	
	// Returns a string representation of "SolverResult" object the as given percentages 
	@Override
	public String toString() {
		return  "Dead percentage: " + getDeadPercentage() + "%\n" +
				"Failure percentage: " + getFailurePercentage() + "%\n" +
				"Success percentage: " + getSuccessPercentage() + "%";
	}
}
