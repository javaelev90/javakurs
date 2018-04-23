package java1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;


public class Elevator implements Runnable{

	private static enum ElevatorState {NOTMOVING, MOVINGUP, MOVINGDOWN };
	
	private static int[] numberOfLevels = {0,1,2,3,4,5,6};
	private int currentLevel;
	private boolean elevatorOn;
	private ElevatorState currentState;
	private List<Person> peopleInElevator;
	private List<ElevatorCall> queue;
	private AtomicBoolean areDoorsOpen;
	private Set<Integer> destinations;
	
	public Elevator(int startingLevel) {
		
		if(startingLevel < numberOfLevels[0]) {
			throw new IllegalArgumentException("Starting level can't be less than lowest elevator level.");
		}
		if(startingLevel > numberOfLevels.length) {
			throw new IllegalArgumentException("Starting level can't be higher than the highest elevator level.");
		}
		currentLevel = startingLevel;
		currentState = ElevatorState.NOTMOVING;
		destinations = new HashSet<Integer>();
	}

	@Override
	public void run() {
		elevatorOn = true;
		queue = new ArrayList<ElevatorCall>();
		peopleInElevator = new ArrayList<Person>();
		areDoorsOpen = new AtomicBoolean();
		while(elevatorOn) {
			try {
				synchronized(queue) {
					synchronized(peopleInElevator) {
						while(queue.isEmpty() && peopleInElevator.isEmpty()) {
							System.out.println("Waiting for people..");
							queue.wait();
						}
					}
				}
				
				System.out.println("Current Level:" + currentLevel);
				
				if(shouldOpenDoors()) {
					synchronized(destinations) {
						destinations.remove(new Integer(currentLevel));
					}
					System.out.println("Opening doors..");
					areDoorsOpen.set(true);

//					Thread.sleep(100); //Opening time
					
					
					notifyPeopleOnCurrentLevel();
					
					areDoorsOpen.set(false);
					System.out.println("Closing doors..");
//					Thread.sleep(100); //Closing time
					
					
				}
				//Update state
				//System.out.println("Update state");
				updateElevatorState();
//				Thread.sleep(1000);
				switch(currentState) {
				case MOVINGUP:
					System.out.println("moving up");
					incrementElevatorLevel();
					break;
				case MOVINGDOWN:
					System.out.println("moving down");
					decrementElevatorLevel();
					break;
				case NOTMOVING:
					System.out.println("not moving");
					break;
				}
				
			} catch (InterruptedException e) {
				System.out.println("An InterruptedException was thrown");
			} catch (Exception e) {
				System.out.println("An Exception was thrown");
			}
					
		}	
		
	}
	
	public void enterElevator(Person person) {
		synchronized(peopleInElevator) {
			if(areDoorsOpen.get()) {
				peopleInElevator.add(person);
				synchronized(destinations) {
					destinations.add(person.getDestinationLevel());
					System.out.println(person.getName() + " wants to go to level " + person.getDestinationLevel());
				}
			}	
		}
	}
	
	public void leaveElevator(Person person) {
		synchronized(peopleInElevator) {
			if(areDoorsOpen.get()) {
				peopleInElevator.remove(person);
			}
		}
	}
	
	private void notifyPeopleOnCurrentLevel() {
		synchronized(queue) {
			List<ElevatorCall> pickedUpPeople = new ArrayList<ElevatorCall>();
			for(ElevatorCall call : queue) {
				if(call.getPersonLevel() == currentLevel) {
					pickedUpPeople.add(call);
					call.getPerson().elevatorDoorsOpening(this);
				}
			}
			queue.removeAll(pickedUpPeople);
		}
		List<Person> peopleWhoLeftElevator = new ArrayList<Person>();
		synchronized(peopleInElevator) {	
			for(Person person : peopleInElevator) {
				if(person.getDestinationLevel() == currentLevel) {
					peopleWhoLeftElevator.add(person);
				}
			}
		}
		for(Person person : peopleWhoLeftElevator) {
			person.elevatorDoorsOpening(this);
		}
		
	}
	
	private void incrementElevatorLevel() {
		currentLevel++;
	}
	
	private void decrementElevatorLevel() {
		currentLevel--;
	}
	
	private void updateElevatorState() {
		synchronized(destinations) {
			//If there are no destinations then the elevator should not move
			if(destinations.isEmpty()) {
				currentState = ElevatorState.NOTMOVING;
			}
			int higestDestination = currentLevel;
			int lowestDestination = currentLevel;
			switch(currentState) {
			case MOVINGUP:	
				for(Integer integer : destinations) {
					if(integer > higestDestination) {
						higestDestination = integer;
					}
				}
				if(higestDestination == currentLevel) {
					currentState = ElevatorState.MOVINGDOWN;
				}
				break;
			case MOVINGDOWN:
				for(Integer integer : destinations) {
					if(integer < lowestDestination) {
						lowestDestination = integer;
					}
				}
				if(lowestDestination == currentLevel) {
					currentState = ElevatorState.MOVINGUP;
				}
				break;
			case NOTMOVING:
				if(!destinations.isEmpty()) {
					int closest = findClosestLevel(destinations);
					if(currentLevel < closest) {
						currentState = ElevatorState.MOVINGUP;
					} else if(currentLevel > closest) {
						currentState = ElevatorState.MOVINGDOWN;
					}
				}
				break;
			}
		}
	}
	
	private int findClosestLevel(Set<Integer> destinations) {
		int closest = numberOfLevels.length;
		for(Integer integer : destinations) {
			int difference = Math.abs(currentLevel - integer);
			if(difference < closest) {
				closest = integer;
			}
		}
		return closest;
	}
	
	private boolean shouldOpenDoors() {
		boolean openDoors = false;
		
		synchronized(queue){
			synchronized(peopleInElevator) {
				for(ElevatorCall call : queue) {
					int personLevel = call.getPersonLevel();
					if(personLevel == currentLevel) {
						openDoors = true;
					} 
				}
				for(Person person : peopleInElevator) {
					int destination = person.getDestinationLevel();

					if(destination == currentLevel) {
						openDoors = true;
					} 
				}
			}
		}
		return openDoors;
	}

	public void turnOffElevator() {
		elevatorOn = false;
	}
	
	public void clickCallButton(ElevatorCall call) throws InterruptedException {
		synchronized(queue) {
			synchronized(destinations) {
				queue.add(call);
				destinations.add(call.getPersonLevel());
				queue.notify();
			}
		}
	}


}
