package java1;

import java.util.ArrayList;
import java.util.List;

public class Program {

	public static void main(String[] args) throws InterruptedException {

		Elevator elevator = new Elevator(2);
		
		Thread thread = new Thread(elevator);
		thread.start();
		Thread.sleep(1000);
		Person person = new Person(2);
		Person person2 = new Person(1);
		
		Thread thread2 = new Thread(person);
		Thread thread3 = new Thread(person2);
		
		thread2.start();
		thread3.start();
		
		List<Person>  people = new ArrayList<Person>();
		people.add(person);
		people.add(person2);
		
		for(Person p : people) {
			Thread.sleep(1000);
			
			elevator.clickCallButton(new ElevatorCall(p, p.getOriginLevel()));
		}
		//elevator.clickCallButton(new ElevatorCall(person, 1));
	}

}
