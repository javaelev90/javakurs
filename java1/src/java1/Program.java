package java1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {

	public static void main(String[] args) throws InterruptedException {
		Elevator elevator = new Elevator();
		
		Thread thread = new Thread(elevator);
		thread.start();
		
		
		//							NAMN	   ORIGIN	
		Person person1 = new Person("Pelle"		,2);
		Person person2 = new Person("Ingvar"	,1);
		Person person3 = new Person("Lisa"		,3);
		Person person4 = new Person("Ann"		,6);
		Person person5 = new Person("Gunn"		,3);
		
		person1.start();
		person2.start();
		person3.start();
		person4.start();
		person5.start();
		
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
			elevator.clickElevatorCallButton(new ElevatorCall(person, person.getOriginLevel()));
		}
	}

}
