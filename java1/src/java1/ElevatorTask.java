package java1;

public class ElevatorTask extends ElevatorCall {

	private int destinationLevel;
	
	public ElevatorTask(Person person, int personLevel, int destinationLevel) {
		super(person, personLevel);
		this.destinationLevel = destinationLevel;
	}
	
	public int getDestinationLevel() {
		return destinationLevel;
	}
}
