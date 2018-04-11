package java1;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingTimeSlot {
	
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
	
	public boolean doesTimeSlotsCollide(BookingTimeSlot timeSlot) {
		LocalDate date = timeSlot.getAppointmentDate();
		LocalTime startTime = timeSlot.getEventStartTime();
		LocalTime stopTime = timeSlot.getEventStopTime();
		
		if(!date.isEqual(appointmentDate)) return false;
		if(eventStartTime.isBefore(stopTime) && startTime.isBefore(eventStopTime) ) return true;
		return false;		
	}
	
	@Override
	public String toString() {
		return appointmentDate.toString() + " From: " + eventStartTime.toString() + " To: " + eventStopTime.toString();
	}
	
}
