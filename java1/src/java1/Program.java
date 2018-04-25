package java1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class Program {

	public static void main(String[] args) throws InterruptedException {
		
		Elevator elevator = new Elevator();
			
		
		Thread thread = new Thread(elevator);
		thread.start();
		
		
		//							NAMN	   ORIGIN	
		Person person1 = new Person("Pelle"		,elevator.getElevatorFloor(2));
		Person person2 = new Person("Ingvar"	,elevator.getElevatorFloor(1));
		Person person3 = new Person("Lisa"		,elevator.getElevatorFloor(3));
		Person person4 = new Person("Ann"		,elevator.getElevatorFloor(6));
		Person person5 = new Person("Gunn"		,elevator.getElevatorFloor(3));
		
		
		
		new Thread(person1).start();
		new Thread(person2).start();
		new Thread(person3).start();
		new Thread(person4).start();
		new Thread(person5).start();
		
		List<Person>  people = new ArrayList<Person>();
		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		people.add(person5);
		
		int minimum = 100;
		for(Person person : people) {	
			int sleepTime = minimum + new Random().nextInt(800);
			Thread.sleep(sleepTime);
			elevator.clickElevatorCallButton(person.getOriginFloor().getFloorLevel());
		}
	}

}
