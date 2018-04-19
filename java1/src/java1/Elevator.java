package java1;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Elevator implements Runnable{

	
	private int numberOfLevels;
	private int currentLevel;
	private boolean elevatorOn;
	private List<Person> work;
	private List<Person> peopleInElevator;
	
	public Elevator(int numberOfLevels, int startingLevel) {
		
		if(numberOfLevels < 2) {
			throw new IllegalArgumentException("Number of levels can't be less than 2.");
		}
		if(startingLevel < 0) {
			throw new IllegalArgumentException("Starting level can't be less than 0.");
		}
		if(startingLevel > numberOfLevels) {
			throw new IllegalArgumentException("Starting level can't be higher than number of levels.");
		}
		this.numberOfLevels = numberOfLevels;
		currentLevel = startingLevel;
	}

	@Override
	public void run() {
		elevatorOn = true;
//		ExecutorService service = Executors.;
//		FutureTask<String> task = new FutureTask<String>();
		while(elevatorOn) {
			
			
			
		}
	}
	
	public void turnOffElevator() {
		elevatorOn = false;
	}
	
	
	
	public synchronized boolean addToWorkList(Person person) {
		return work.add(person);
	}
	

}
