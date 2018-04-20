package java1;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Callable;

public class Person implements Runnable{
	
	private boolean inElevator;
	private boolean chosenDestination;
	private int destinationLevel;
	private int originLevel;
	
	public Person(int originLevel) {
		inElevator = false;
		this.originLevel = originLevel;
	}

	
	public int getOriginLevel() {
		return originLevel;
	}
	
	public Person onElevatorDoorsOpening() {
		if(!inElevator) {
			System.out.println("Entering elevator");
			inElevator = true;
			return this;
		} else {
			System.out.println("Leaving elevator");
			return null;
		}	
	}
	
	public Optional<Integer> getdestinationLevel() {
		if(chosenDestination) {
			return Optional.of(destinationLevel);
		} 
		return Optional.empty();
	}
	
	
	public int chooseDestination(int[] listOfLevels) {
		destinationLevel = new Random().nextInt(listOfLevels.length);
		chosenDestination = true;
		return destinationLevel;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				System.out.println("Waiting");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
