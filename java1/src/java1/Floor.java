package java1;

public class Floor {
	
	private Elevator elevator;
	private int floorLevel;
	
	public Floor(Elevator elevator, int floorLevel) {
		this.elevator = elevator;
		this.floorLevel = floorLevel;
	}
	
	public int getFloorLevel() {
		return floorLevel;
	}
	
	public Elevator getElevator() {
		return elevator;
	}
	
	
}
