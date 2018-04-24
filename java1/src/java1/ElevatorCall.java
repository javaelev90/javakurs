package java1;

public class ElevatorCall {

	private Person person;
	private int personLevel;
	
	public ElevatorCall(Person person, int personLevel) {
		this.person = person;
		this.personLevel = personLevel;
	}

	public int getPersonLevel() {
		return personLevel;
	}

	public void setPersonLevel(int personLevel) {
		this.personLevel = personLevel;
	}

	public Person getPerson() {
		return person;
	}
	
	public String toString() {
		return ""+personLevel;
	}
	
}
