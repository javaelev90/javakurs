package java1;

import java.util.Random;

public class Person implements Runnable{
	
	private boolean inElevator;
	private Floor originFloor;
	private Floor destinationFloor;
	private String name;
	private boolean reachedDestination;
	
	public Person(String name, Floor originFloor) {
		inElevator = false;
		this.originFloor = originFloor;
		this.name = name;
	}

	public Floor getOriginFloor() {
		return originFloor;
	}
	
	public Floor getDestinationLevel() {
		return destinationFloor;
	}
	
	public int chooseDestination(int[] listOfLevels) {
		int destinationLevel = new Random().nextInt(listOfLevels.length);
		return listOfLevels[destinationLevel];
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public void run() {
	
		while(!reachedDestination) {
			if(!inElevator) {
				try {
					System.out.println("!! "+name+" called elevator on level "+originFloor.getFloorLevel()+" !!");
					Elevator elevator = originFloor.getElevator();
					elevator.clickElevatorCallButton(originFloor.getFloorLevel());
					synchronized(originFloor) {
						originFloor.wait();
					}
					if(elevator.enterElevator()) {
						System.out.println("-"+name+" entered the elevator");
						int destination = chooseDestination(elevator.getElevatorLevels());
						
						destinationFloor = elevator.clickOnElevatorDestinationPanel(destination);
						
						System.out.println("-"+name + " wants to go to level " + destination);
						inElevator = true;
					} else {
						System.out.println("-"+name+" could not enter the elevator");
					}
					
					
				} catch (InterruptedException e) {
					System.out.println("InterruptedException was caught when waiting on origin floor: "+originFloor);
				}
			
			} else {
				try {
					synchronized(destinationFloor) {
						destinationFloor.wait();
					}
					Elevator elevator = destinationFloor.getElevator();
					if(elevator.leaveElevator()) {
						System.out.format("-%s left the elevator(origin: %d, destination: %d)%n",
								name, originFloor.getFloorLevel(), destinationFloor.getFloorLevel());
						inElevator = false;
						reachedDestination = true;
					} else {
						System.out.println("-"+name+" could not leave the elevator");
						
						elevator.clickOnElevatorDestinationPanel(destinationFloor.getFloorLevel());
						System.out.println("-"+name + " wants to go to level " + destinationFloor.getFloorLevel());
					}
					
				} catch (InterruptedException e) {
					System.out.println("InterruptedException was caught when waiting on destination floor: "+destinationFloor);
				}
			}
		}
		
		
	}

	
}
