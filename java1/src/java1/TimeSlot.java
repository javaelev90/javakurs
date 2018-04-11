package java1;

import java.time.LocalDateTime;

public class TimeSlot {
	
	private LocalDateTime timeSlotStart;
	private LocalDateTime timeSlotStop;
	
	public LocalDateTime getTimeSlotStart() {
		return timeSlotStart;
	}

	public void setTimeSlotStart(LocalDateTime timeSlotStart) {
		this.timeSlotStart = timeSlotStart;
	}

	public LocalDateTime getTimeSlotStop() {
		return timeSlotStop;
	}

	public void setTimeSlotTop(LocalDateTime timeSlotStop) {
		this.timeSlotStop = timeSlotStop;
	}

	public boolean doTimeSlotsCollide(TimeSlot timeSlot) {
		LocalDateTime startTime = timeSlot.getTimeSlotStart();
		LocalDateTime stopTime = timeSlot.getTimeSlotStop() ;
		
		return (timeSlotStart.isBefore(stopTime) && startTime.isBefore(timeSlotStop));
	}
	
	@Override
	public String toString() {
		return "From: " + timeSlotStart.toString() + " To: " + timeSlotStop.toString();
	}
	
}
