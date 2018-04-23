package java1;

import java.util.Random;

public class Person implements Runnable{
	
	private boolean inElevator;
//	private boolean chosenDestination;
	private int destinationLevel;
	private int originLevel;
	private String name;
	private boolean reachedDestination;
	
	public Person(String name, int originLevel, int destinationLevel) {
		inElevator = false;
		this.originLevel = originLevel;
		this.destinationLevel = destinationLevel;
		reachedDestination = false;
		this.name = name;
	}

	
	public int getOriginLevel() {
		return originLevel;
	}
	
	public void elevatorDoorsOpening(Elevator elevator) {
		if(!inElevator) {
			System.out.println(name+" is entering elevator");
			elevator.enterElevator(this);
			inElevator = true;
		} else {
			System.out.println(name+" is leaving elevator");
			elevator.leaveElevator(this);
			reachedDestination = true;
			inElevator = false;
		}	
	}
	
	public int getDestinationLevel() {
		return destinationLevel;
	}
	
	public String getName() {
		return name;
	}
	
//	public int chooseDestination(int[] listOfLevels) {
//		destinationLevel = new Random().nextInt(listOfLevels.length);
//		chosenDestination = true;
//		return destinationLevel;
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!reachedDestination) {

			//System.out.println(name+" is waiting "+(inElevator ? "in the elevator" : "on the elevator"));
			
		}
		
		//System.out.println("Reached destination:"+toString());
	}
	
	public String toString() {
		return "\nName: "+name
				+"\nOrigin: "+originLevel
				+"\nDestination: "+destinationLevel
				+"\n";
				
	}
}
