package java1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
	
	private LocalDate appointmentDate;
	private LocalTime eventStartTime;
	private LocalTime eventStopTime;
	
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public LocalTime getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(LocalTime eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public LocalTime getEventStopTime() {
		return eventStopTime;
	}
	public void setEventStopTime(LocalTime eventStopTime) {
		this.eventStopTime = eventStopTime;
	}
	
}
