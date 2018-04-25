package java1;

import java.util.Random;

public class Person implements Runnable{
	
	private boolean inElevator;
	private Floor originFloor;
	private Floor destinationFloor;
	private String name;
	private boolean reachedDestination;
	
	public Person(String name, Floor floor) {
		inElevator = false;
		originFloor = floor;
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
	
//	@Override
//	public void onElevatorDoorsEvent(Elevator elevator) {
//		if(!reachedDestination) {
//			if(!inElevator) {
//				if(elevator.getCurrentLevel() == originFloor.getFloorLevel()) {
//					if(elevator.enterElevator()) {
//						System.out.println("-"+name+" entered the elevator");
//						int destination = chooseDestination(elevator.getElevatorLevels());
//						destinationFloor = elevator.clickOnElevatorDestinationPanel(destination);
//						System.out.println("-"+name + " wants to go to level " + destinationLevel);
//						inElevator = true;
//					} else {
//						System.out.println("-"+name+" could not enter the elevator");
//					}
//				}				
//			} else {
//				if(elevator.getCurrentLevel() == destinationLevel) {
//					if(elevator.leaveElevator()) {
//						System.out.format("-%s left the elevator(origin: %d, destination: %d)%n", name, originLevel, destinationLevel);
//						inElevator = false;
//						reachedDestination = true;
//					} else {
//						System.out.println("-"+name+" could not leave the elevator");
//						elevator.clickOnElevatorDestinationPanel(destinationLevel);
//						System.out.println("-"+name + " wants to go to level " + destinationLevel);
//					}
//				}
//			}
//		}	
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!reachedDestination) {
			if(!inElevator) {
				try {
					synchronized(originFloor) {
						originFloor.wait();
					
						Elevator elevator = originFloor.getElevator();
	
						if(elevator.enterElevator()) {
							System.out.println("-"+name+" entered the elevator");
							int destination = chooseDestination(elevator.getElevatorLevels());
							
							destinationFloor = elevator.clickOnElevatorDestinationPanel(destination);
							
							System.out.println("-"+name + " wants to go to level " + destination);
							inElevator = true;
						} else {
							System.out.println("-"+name+" could not enter the elevator");
						}
					}
					
				} catch (InterruptedException e) {
					System.out.println("InterruptedException was caught when waiting on origin floor: "+originFloor);
				}
			
			} else {
				try {
					synchronized(destinationFloor) {
						destinationFloor.wait();
					
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
					}
				} catch (InterruptedException e) {
					System.out.println("InterruptedException was caught when waiting on destination floor: "+destinationFloor);
				}
			}
		}
		
		
	}

	
}
