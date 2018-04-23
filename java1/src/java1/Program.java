package java1;

import java.util.ArrayList;
import java.util.List;

public class Program {

	public static void main(String[] args) throws InterruptedException {

		Elevator elevator = new Elevator(0);
		
		Thread thread = new Thread(elevator);
		thread.start();
		Thread.sleep(1000);
		Person person = new Person("Pelle",2, 4);
		Person person2 = new Person("Ingvar",1, 3);
		
		Thread thread2 = new Thread(person);
		Thread thread3 = new Thread(person2);
		
		thread2.start();
		thread3.start();
		
		List<Person>  people = new ArrayList<Person>();
		people.add(person);
		people.add(person2);
		
		for(Person p : people) {
			Thread.sleep(1);
			
			elevator.clickCallButton(new ElevatorCall(p, p.getOriginLevel()));
		}
		//elevator.clickCallButton(new ElevatorCall(person, 1));
	}

}
