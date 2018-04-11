package java1;

import java.time.LocalDateTime;
import java.time.LocalTime;
//TODO class not in use yet
public class ScheduleLimits {
	
	private LocalTime startTime;
	private LocalTime stopTime;
	
	public ScheduleLimits(LocalTime startTime, LocalTime stopTime) {
		this.startTime = startTime;
		this.stopTime = stopTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getStopTime() {
		return stopTime;
	}

	public void setStopTime(LocalTime stopTime) {
		this.stopTime = stopTime;
	}
	//TODO need to make this work
//	public boolean (TimeSlot timeSlot) {
//		LocalTime startTime = timeSlot.getTimeSlotStart().toLocalTime();
//		LocalTime stopTime = timeSlot.getTimeSlotStop().toLocalTime() ;
//		
//		return (timeSlotStart.isBefore(stopTime) && startTime.isBefore(timeSlotStop));
//	}
//	
	
}
