package java1;

import java.time.LocalDateTime;

public class Client {
	
	private LocalDateTime eventStartTime;
	private LocalDateTime eventStopTime;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	public LocalDateTime getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(LocalDateTime eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public LocalDateTime getEventStopTime() {
		return eventStopTime;
	}
	public void setEventStopTime(LocalDateTime eventStopTime) {
		this.eventStopTime = eventStopTime;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}	
	
}
