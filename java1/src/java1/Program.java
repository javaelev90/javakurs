package java1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Program {

	public static void main(String[] args) throws InterruptedException {
		
		Elevator elevator = new Elevator();
		
		Thread thread = new Thread(elevator);
		thread.start();
		
		
		//							NAMN	   ORIGIN FLOOR	
//		Person person1 = new Person("Pelle"		,elevator.getElevatorFloor(2));
//		Person person2 = new Person("Ingvar"	,elevator.getElevatorFloor(1));
//		Person person3 = new Person("Lisa"		,elevator.getElevatorFloor(3));
//		Person person4 = new Person("Ann"		,elevator.getElevatorFloor(6));
//		Person person5 = new Person("Gunn"		,elevator.getElevatorFloor(3));
		
		Person person1 = new Person("Pelle"		,elevator.getElevatorFloor(new Random().nextInt(elevator.getElevatorLevels().length)));
		Person person2 = new Person("Ingvar"	,elevator.getElevatorFloor(new Random().nextInt(elevator.getElevatorLevels().length)));
		Person person3 = new Person("Lisa"		,elevator.getElevatorFloor(new Random().nextInt(elevator.getElevatorLevels().length)));
		Person person4 = new Person("Ann"		,elevator.getElevatorFloor(new Random().nextInt(elevator.getElevatorLevels().length)));
		Person person5 = new Person("Gunn"		,elevator.getElevatorFloor(new Random().nextInt(elevator.getElevatorLevels().length)));
		
		List<Person>  people = new ArrayList<Person>();
		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		people.add(person5);
		
		int minimum = 100;
		CountDownLatch latch = new CountDownLatch(1);
		for(Person person : people) {	
			int sleepTime = minimum + new Random().nextInt(200);
			Thread.sleep(sleepTime);
			new Thread(()-> {
				try {
					latch.await();
					person.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
		latch.countDown();
		
	}

}
