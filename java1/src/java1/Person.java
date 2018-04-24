package java1;

import java.util.Random;

public class Person extends Thread{
	
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
	
	public void elevatorDoorsOpening(Elevator elevator) throws InterruptedException {
		if(!inElevator) {
			if(elevator.enterElevator(this)) {
				System.out.println("-"+name+" entered the elevator");
				inElevator = true;
			} else {
				System.out.println("-"+name+" could not enter the elevator");
			}		
		} else {
			if(elevator.leaveElevator(this)) {
				System.out.println("-"+name+" left the elevator");
				inElevator = false;
			} else {
				System.out.println("-"+name+" could not leave the elevator");
			}
		}	
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
}
