package java1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;


public class Elevator implements Runnable{

	private static enum ElevatorState {NOTMOVING, MOVINGUP, MOVINGDOWN };
	
	private static int[] numberOfLevels = {0,1,2,3};
	private int currentLevel;
	private boolean elevatorOn;
	private ElevatorState currentState;
	private List<Person> peopleInElevator;
	private List<ElevatorCall> queue;
	private AtomicBoolean areDoorsOpen;
	
	public Elevator(int startingLevel) {
		
		if(startingLevel < numberOfLevels[0]) {
			throw new IllegalArgumentException("Starting level can't be less than lowest elevator level.");
		}
		if(startingLevel > numberOfLevels.length) {
			throw new IllegalArgumentException("Starting level can't be higher than the highest elevator level.");
		}
		currentLevel = startingLevel;
		currentState = ElevatorState.NOTMOVING;

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
					System.out.println("Opening doors..");
					areDoorsOpen.set(true);
//					Thread.sleep(100); //Opening time
					
					
					notifyPeopleOnCurrentLevel();
					
					areDoorsOpen.set(false);
					System.out.println("Closing doors..");
//					Thread.sleep(100); //Closing time
					
					
				}
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
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		}	
		
	}
	
	public void enterElevator(Person person) {
		synchronized(peopleInElevator) {
			if(areDoorsOpen.get()) {
				peopleInElevator.add(person);
				//person.chooseDestination(numberOfLevels);
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
	
	private boolean shouldOpenDoors() {
		boolean openDoors = false;
		switch(currentState) {
		
		case MOVINGUP:
			//Stop at levels where a person wants to get off and pick up people who want to go up, continue to highest call
			//then switch to moving down if queue is not empty
			int highestLevel = currentLevel;
		
			synchronized(queue){
				synchronized(peopleInElevator) {
					for(ElevatorCall call : queue) {
						int personLevel = call.getPersonLevel();
						if(personLevel == currentLevel) {
							openDoors = true;
						} 
						if(personLevel > highestLevel) {
							highestLevel = personLevel;
						}
					}
					for(Person person : peopleInElevator) {
						int destination = person.getDestinationLevel();
//						if(destination.isPresent()) {
							if(destination == currentLevel) {
								openDoors = true;
							} 
							if(destination > highestLevel) {
								highestLevel = destination;
							}
//						}
					}
					if(highestLevel == currentLevel && queue.isEmpty() && peopleInElevator.isEmpty()) {
						currentState = ElevatorState.NOTMOVING;
					} else if(highestLevel > currentLevel){
						currentState = ElevatorState.MOVINGUP;
					} else {
						currentState = ElevatorState.MOVINGDOWN;
					}
				}
			}
			return openDoors;

		case MOVINGDOWN:
			//Stop at levels where a person want to get on(if they want to go down)/off and continue down to lowest destination
			
			int lowestLevel = currentLevel;
			synchronized(queue){	
				synchronized(peopleInElevator) {
					for(ElevatorCall call : queue) {
						int personLevel = call.getPersonLevel();
						if(personLevel == currentLevel) {
							openDoors = true;
						} 
						if(personLevel < lowestLevel) {
							lowestLevel = personLevel;
						}
					}
					for(Person person : peopleInElevator) {
//						Optional<Integer> destination = person.getdestinationLevel();
						int destination = person.getDestinationLevel();
//						if(destination.isPresent()) {
							int personDestination = destination;
							if(destination == currentLevel) {
								openDoors = true;
							} 
							if(personDestination < lowestLevel) {
								lowestLevel = personDestination;
							}
//						}
					}
					
					if(lowestLevel == currentLevel && queue.isEmpty() && peopleInElevator.isEmpty()) {
						currentState = ElevatorState.NOTMOVING;
					} else if(lowestLevel < currentLevel){
						currentState = ElevatorState.MOVINGDOWN;
					} else {
						currentState = ElevatorState.MOVINGUP;
					}
					
				}
			}
			return openDoors;
			
		case NOTMOVING:
			//Look through all calls and choose the closest one to move at
			synchronized(queue) {
				if(!queue.isEmpty()) {
					int level = queue.get(0).getPersonLevel();
					if(level < currentLevel) {
						currentState = ElevatorState.MOVINGDOWN;
					} else if(level > currentLevel) {
						currentState = ElevatorState.MOVINGUP;
					} else {
						openDoors = true;
					}
				}
			}
				
			return openDoors;
		
		default:
			return openDoors;
		}
		
	}
	
	public void turnOffElevator() {
		elevatorOn = false;
	}
	
	public void clickCallButton(ElevatorCall call) throws InterruptedException {
		synchronized(queue) {
			queue.add(call);
			queue.notify();
		}
	}


}
