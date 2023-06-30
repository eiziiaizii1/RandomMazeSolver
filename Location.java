
public class Location {
	int x;
	int y;
	
	// Constructs x and y values according to given parameters
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Constructs x and y values according to a Location object's x and y components
	public Location(Location location) {
		this.x = location.x;
		this.y = location.y;
	}
	
	// Checks whether a coordinate is equal to given parameters
	public boolean equals(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	// Checks whether the objects are same
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else {
			if (obj instanceof Location) {
				Location objLocation = (Location)obj;
				return this.x == objLocation.x && this.y == objLocation.y;
			}
			else
				return false;
		}
	}

	// Returns a string representation of "Location" object the as "P(x,y)" 
	@Override
	public String toString() {
		return "P(" + x + ", " + y + ")";
	}
}
