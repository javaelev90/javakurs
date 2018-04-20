package java1;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Elevator implements Runnable{

	private static enum ElevatorState {NOTMOVING, MOVINGUP, MOVINGDOWN };
	
	private static int[] numberOfLevels = {0,1,2};
	private int currentLevel;
	private boolean elevatorOn;
	private ElevatorState currentState;
	private List<Person> peopleInElevator;
	private BlockingQueue<ElevatorCall> queue;
	
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
		queue = new LinkedBlockingQueue<ElevatorCall>();

		while(elevatorOn) {
			try {
				System.out.println("Waiting for people..");
				ElevatorCall task = queue.take();
				Person person = task.getPerson().onElevatorDoorsOpening();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		}	
		
	}
	
	private synchronized int getNextElevatorDestination() {
		
		switch(currentState) {
		
		case MOVINGUP:
			//Stop at levels where a person wants to get off and pick up people who want to go up, continue to highest call
			//then switch to moving down if queue is not emptu
			int nextLevel = currentLevel;
			for(Person person : peopleInElevator) {
				Optional<Integer> destination = person.getdestinationLevel();
				if(destination.isPresent()) {
					if(destination.get() == currentLevel) {
						nextLevel = currentLevel; 
					}
				}
			}
			
			return 0;
		case MOVINGDOWN:
			//Stop at levels where a person want to get on(if they want to go down)/off and continue down to lowest destination
			
			return 0;
		case NOTMOVING:
			//Look through all calls and choose the closest one to move at
	
			return 0;
		default:
			return 0;
		}
		
	}
	
	private int findClosestLevel(List<Person> persons) {
		return 0;
	}
	
	public void turnOffElevator() {
		elevatorOn = false;
	}
	
	public synchronized void clickCallButton(ElevatorCall call) throws InterruptedException {
		queue.put(call);

	}


}
