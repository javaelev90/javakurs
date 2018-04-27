package java1;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;


public class Elevator implements Runnable{

	private static enum ElevatorState {NOT_MOVING, MOVING_UP, MOVING_DOWN };
	private static final int MAX_WAITING_CYCLES = 2;
	
	private static final int[] elevatorLevels = {0,1,2,3,4,5,6};
	private int currentLevel;
	private ElevatorState currentState;
	private AtomicBoolean areDoorsOpen;
	private Set<Integer> destinations;
	private Map<Integer, Floor> floors;
	
	public Elevator() {
		currentLevel = elevatorLevels[new Random().nextInt(elevatorLevels.length)];
		currentState = ElevatorState.NOT_MOVING;
		destinations = Collections.synchronizedSet(new HashSet<Integer>());
		this.floors = Collections.synchronizedMap(new HashMap<Integer, Floor>());
		IntStream.range(elevatorLevels[0], elevatorLevels.length).forEach(i -> floors.put(i, new Floor(this,i)));
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
		destinations = Collections.synchronizedSet(new HashSet<Integer>());
		this.floors = Collections.synchronizedMap(new HashMap<Integer, Floor>());
		IntStream.range(elevatorLevels[0], elevatorLevels.length).forEach(i -> floors.put(i, new Floor(this,i)));
	}

	@Override
	public void run() {
		areDoorsOpen = new AtomicBoolean();
		System.out.println("Elevator is starting on level "+currentLevel);
		while(true) {
			try {
				//waits for people to call elevator
				int waitingCycles = waitOnElevatorCallEvents();
				if(waitingCycles > MAX_WAITING_CYCLES) {
					break;
				}
				System.out.println("---------------<--Elevator status-->-----------------");
				System.out.println("Current Level:" + currentLevel);

				System.out.println("Elevator destinations: "+ destinations.toString());
				
				
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
			synchronized(destinations) {
				destinations.wait(1000);
			}
			waitingCycles++;
			if(waitingCycles > MAX_WAITING_CYCLES) {
				break;
			}
		}
		
		return waitingCycles;
	}
	
	private void openDoorEvent() throws InterruptedException {
		System.out.println("-----------------<--Level Event-->-------------------");
		
		System.out.println("<-||->Opening doors..");
		Thread.sleep(100); //Opening time
		areDoorsOpen.set(true);
		
		//Notifies people that want to get off or on the elevator on the current level
		callDoorListeners();
		Thread.sleep(200); //Stays open for this amount of time
		
		System.out.println("|-><-|Closing doors..");
		Thread.sleep(100); //Closing time
		areDoorsOpen.set(false);
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
	public Floor getElevatorFloor(int i) {

		return floors.get(i);
		
	}
	
	public int[] getElevatorLevels() {
		return elevatorLevels;
	}
	
	public boolean enterElevator() {
		return areDoorsOpen.get();
	}
	
	public boolean leaveElevator() {
		return areDoorsOpen.get();
	}
	
	public Floor clickOnElevatorDestinationPanel(int destination) {

		destinations.add(destination);
		
		return floors.get(destination);
		
	}
	
	public void clickElevatorCallButton(int destination) throws InterruptedException {

		if(!destinations.contains(destination)) {
			destinations.add(destination);
			synchronized(destinations){
				destinations.notify();
			}
			
		}
		
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	// !---------------PUBLIC FUNCTIONALITY END--------------------!
	
	private void callDoorListeners() throws InterruptedException {

		Floor floor = floors.get(currentLevel);
		synchronized(floor) {
			floor.notifyAll();
		}
		
	}
	
	private void incrementElevatorLevel() {
		currentLevel++;
	}
	
	private void decrementElevatorLevel() {
		currentLevel--;
	}
	
	private void updateElevatorState() {

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
		
		for(Integer destination : destinations) {
			if(destination == currentLevel) {
				openDoors = true;
				destinations.remove(destination);
				break;
			}
		}		
			
		return openDoors;
	}
	
	
	
}
