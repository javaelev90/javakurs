package java1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;


public class Elevator implements Runnable{

	private static enum ElevatorState {NOT_MOVING, MOVING_UP, MOVING_DOWN };
	private static final int MAX_WAITING_CYCLES = 2;
	
	private static int[] elevatorLevels = {0,1,2,3,4,5,6};
	private int currentLevel;
	private ElevatorState currentState;
	private List<Person> peopleInElevator;
	private List<ElevatorCall> queue;
	private AtomicBoolean areDoorsOpen;
	private Set<Integer> destinations;
	
	public Elevator() {
		currentLevel = new Random().nextInt(elevatorLevels.length);
		currentState = ElevatorState.NOT_MOVING;
		destinations = new HashSet<Integer>();
	}
	
	public Elevator(int startingLevel) {
		
		if(startingLevel < elevatorLevels[0]) {
			throw new IllegalArgumentException("Starting level can't be less than lowest elevator level.");
		}
		if(startingLevel > elevatorLevels.length) {
			throw new IllegalArgumentException("Starting level can't be higher than the highest elevator level.");
		}
		currentLevel = startingLevel;
		currentState = ElevatorState.NOT_MOVING;
		destinations = new HashSet<Integer>();
	}

	@Override
	public void run() {
		queue = new ArrayList<ElevatorCall>();
		peopleInElevator = new ArrayList<Person>();
		areDoorsOpen = new AtomicBoolean();
		while(true) {
			try {
				synchronized(queue) {
					synchronized(peopleInElevator) {
						int waitingCycles = 0;
						while(queue.isEmpty() && peopleInElevator.isEmpty()) {
							System.out.println("Waiting for people..");
							queue.wait(1000);
							waitingCycles++;
							if(waitingCycles > MAX_WAITING_CYCLES) {
								break;
							}
						}
						if(waitingCycles > MAX_WAITING_CYCLES) {
							break;
						}
					}
				}
				
				System.out.println("-Current Level:" + currentLevel);
				System.out.println("--People in elevator: "+ peopleInElevator.toString());
				System.out.println("--Elevator destinations: "+ destinations.toString());
	
				//Checks if there are people that want to get off/on on the current level
				if(shouldOpenDoors()) {

					System.out.println("Opening doors..");
					areDoorsOpen.set(true);

					Thread.sleep(100); //Opening time
					
					//Notifies people that want to get off or on the elevator on the current level
					notifyPeopleOnCurrentLevel();
					
					areDoorsOpen.set(false);
					System.out.println("Closing doors..");
					Thread.sleep(100); //Closing time
					
					synchronized(destinations) {
						destinations.remove(new Integer(currentLevel));
					}
				}
				//Updates which way to move
				updateElevatorState();
				
				switch(currentState) {
				case MOVING_UP:
					System.out.println("Moving up..");
					incrementElevatorLevel();
					break;
				case MOVING_DOWN:
					System.out.println("Moving down..");
					decrementElevatorLevel();
					break;
				case NOT_MOVING:
					System.out.println("Not moving..");
					break;
				}
				Thread.sleep(300);
				
			} catch (InterruptedException e) {
				System.out.println("An InterruptedException was thrown");
			} catch (Exception e) {
				System.out.println("An Exception was thrown");
			}
					
		}	
		System.out.println("Elevator exceeded waiting limit and will turn off");
		
	}
	
	public boolean enterElevator(Person person) {
		synchronized(peopleInElevator) {
			if(areDoorsOpen.get()) {
				peopleInElevator.add(person);
				int destination = person.chooseDestination(elevatorLevels);
				clickOnDestination(destination);
				System.out.println("-"+person.getPersonName() + " wants to go to level " + destination);
				return true;
			}	
			return false;
		}
	}
	
	public boolean leaveElevator(Person person) {
		synchronized(peopleInElevator) {
			if(areDoorsOpen.get()) {
				peopleInElevator.remove(person);
				return true;
			} else {
				int destination = person.getDestinationLevel();
				clickOnDestination(destination);
				System.out.println("-"+person.getPersonName() + " wants to go to level " + destination);
				return false;
			}
			
		}
		
	}
	
	private void clickOnDestination(int destination) {
		synchronized(destinations) {
			destinations.add(destination);
		}
	}
	
	private void notifyPeopleOnCurrentLevel() throws InterruptedException {
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
				currentState = ElevatorState.NOT_MOVING;
			}
			int higestDestination = currentLevel;
			int lowestDestination = currentLevel;
			switch(currentState) {
			case MOVING_UP:	
				for(Integer integer : destinations) {
					if(integer > higestDestination) {
						higestDestination = integer;
					}
				}
				if(higestDestination == currentLevel) {
					currentState = ElevatorState.MOVING_DOWN;
				}
				break;
			case MOVING_DOWN:
				for(Integer integer : destinations) {
					if(integer < lowestDestination) {
						lowestDestination = integer;
					}
				}
				if(lowestDestination == currentLevel) {
					currentState = ElevatorState.MOVING_UP;
				}
				break;
			case NOT_MOVING:
				if(!destinations.isEmpty()) {
					int closest = findClosestLevel(destinations);
					if(currentLevel < closest) {
						currentState = ElevatorState.MOVING_UP;
					} else if(currentLevel > closest) {
						currentState = ElevatorState.MOVING_DOWN;
					}
				}
				break;
			}
		}
	}
	
	private int findClosestLevel(Set<Integer> destinations) {
		int closest = elevatorLevels.length;
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
			for(ElevatorCall call : queue) {
				int personLevel = call.getPersonLevel();
				if(personLevel == currentLevel) {
					openDoors = true;
				} 
			}
		}
		synchronized(peopleInElevator) {
			for(Person person : peopleInElevator) {
				int destination = person.getDestinationLevel();

				if(destination == currentLevel) {
					openDoors = true;
				} 
			}
		}
		
		return openDoors;
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
