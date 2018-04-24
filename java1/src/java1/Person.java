package java1;

import java.util.Random;

public class Person extends Thread implements ElevatorDoorsListener{
	
	private boolean inElevator;
	private int originLevel;
	private int destinationLevel;
	private String name;
	
	public Person(String name, int originLevel) {
		inElevator = false;
		this.originLevel = originLevel;
		this.name = name;
	}
	
	public int getOriginLevel() {
		return originLevel;
	}
	
	public int getDestinationLevel() {
		return destinationLevel;
	}
	
	public String getPersonName() {
		return name;
	}
	
	public int chooseDestination(int[] listOfLevels) {
		destinationLevel = new Random().nextInt(listOfLevels.length);
		return listOfLevels[destinationLevel];
	}

	public String toString() {
		return name;		
	}

	@Override
	public void onElevatorDoorsEvent(Elevator elevator) {
		if(!inElevator) {
			if(elevator.getCurrentLevel() == originLevel) {
				if(elevator.enterElevator()) {
					System.out.println("-"+name+" entered the elevator");
					chooseDestination(elevator.getElevatorLevels());
					elevator.clickOnDestination(destinationLevel);
					System.out.println("-"+name + " wants to go to level " + destinationLevel);
					inElevator = true;
				} else {
					System.out.println("-"+name+" could not enter the elevator");
				}
			}				
		} else {
			if(elevator.getCurrentLevel() == destinationLevel) {
				if(elevator.leaveElevator()) {
					System.out.format("-%s left the elevator(origin: %d, destination: %d)%n", name, originLevel, destinationLevel);
					inElevator = false;
				} else {
					System.out.println("-"+name+" could not leave the elevator");
					elevator.clickOnDestination(destinationLevel);
					System.out.println("-"+name + " wants to go to level " + destinationLevel);
				}
			}
		}
	}

	
}
