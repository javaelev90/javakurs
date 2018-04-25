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
	
	private static final int[] elevatorLevels = {0,1,2,3,4,5,6};
	private int currentLevel;
	private ElevatorState currentState;
	private List<ElevatorCall> queue;
	private AtomicBoolean areDoorsOpen;
	private Set<Integer> destinations;
	
	public Elevator() {
		currentLevel = elevatorLevels[new Random().nextInt(elevatorLevels.length)];
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
		areDoorsOpen = new AtomicBoolean();
		while(true) {
			try {
				//waits for people to call elevator
				int waitingCycles = waitOnElevatorCallEvents();
				if(waitingCycles > MAX_WAITING_CYCLES) {
					break;
				}
				System.out.println("---------------<--Elevator status-->-----------------");
				System.out.println("Current Level:" + currentLevel);
				synchronized(destinations) {
					System.out.println("Elevator destinations: "+ destinations.toString());
				}
				
				//Checks if there are people that want to get off/on on the current level
				while (shouldOpenDoors()){
					openDoorEvent();
				} 
				System.out.println("-----------------------------------------------------");
				
				
				//Updates which way to move
				updateElevatorState();
				
				moveElevator();
				
				Thread.sleep(300);
				
			} catch (InterruptedException e) {
				System.out.println("An InterruptedException was thrown");
			}			
		}	
		System.out.println("Elevator exceeded waiting limit and will turn off");		
	}
	
	private int waitOnElevatorCallEvents() throws InterruptedException {
		int waitingCycles = 0;
	
				
		while(destinations.isEmpty()) {
			System.out.println("Elevator is waiting for people..");
			synchronized(queue) {
				queue.wait(1000);
			}
			
			waitingCycles++;
			if(waitingCycles > MAX_WAITING_CYCLES) {
				break;
			}
		}
		return waitingCycles;
	}
	
	private void openDoorEvent() throws InterruptedException {
		synchronized(destinations) {
			destinations.remove(new Integer(currentLevel));
		}
		System.out.println("-----------------<--Level Event-->-------------------");
		System.out.println("<-||->Opening doors..");
		areDoorsOpen.set(true);

		Thread.sleep(100); //Opening time
		
		//Notifies people that want to get off or on the elevator on the current level
		callDoorListeners();
		
		areDoorsOpen.set(false);
		System.out.println("|-><-|Closing doors..");
		Thread.sleep(100); //Closing time
	}
	
	private void moveElevator() {
		
		switch(currentState) {
		case MOVING_UP:
			System.out.println("\n Elevator moving up..\n");
			incrementElevatorLevel();
			break;
		case MOVING_DOWN:
			System.out.println("\n Elevator moving down..\n");
			decrementElevatorLevel();
			break;
		case NOT_MOVING:
			System.out.println("\n Elevator not moving..\n");
			break;
		}
		
	}
	
	// !--------------PUBLIC FUNCTIONALITY START------------------!
	public int[] getElevatorLevels() {
		return elevatorLevels;
	}
	
	public boolean enterElevator() {
		return areDoorsOpen.get();
	}
	
	public boolean leaveElevator() {
		return areDoorsOpen.get();
	}
	
	public void clickOnElevatorDestinationPanel(int destination) {
		synchronized(destinations) {
			destinations.add(destination);
		}
	}
	
	public void clickElevatorCallButton(ElevatorCall call) throws InterruptedException {
		synchronized(queue) {
			synchronized(destinations) {
				queue.add(call);
				destinations.add(call.getOriginLevel());
				queue.notify();
			}
		}
		System.out.println(call.toString()+" called elevator on level " +call.getOriginLevel());
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	// !---------------PUBLIC FUNCTIONALITY END--------------------!
	
	private void callDoorListeners() throws InterruptedException {
		synchronized(queue) {
			for(ElevatorCall call : queue) {
				call.getListener().onElevatorDoorsEvent(this);
			}
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
		
		synchronized(destinations) {
			for(Integer destination : destinations) {
				if(destination == currentLevel) {
					openDoors = true;
				}
			}
		}
		
		return openDoors;
	}
	
	
	
}
