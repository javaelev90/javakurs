package java1.model;

import java.time.LocalTime;

public class ScheduleLimits {

	private LocalTime startTime;
	private LocalTime stopTime;

	public ScheduleLimits(LocalTime startTime, LocalTime stopTime) {
		this.startTime = startTime;
		this.stopTime = stopTime;
	}

	/**
	 * Checks if a given timeslot is within the decided limits of the schedule, e.g.
	 * working hours of an employee from 08:00 to 18:00
	 * 
	 * @param timeSlot
	 * @return
	 */
	public boolean isWithinLimits(TimeSlot timeSlot) {
		LocalTime startTime = timeSlot.getTimeSlotStart().toLocalTime();
		LocalTime stopTime = timeSlot.getTimeSlotStop().toLocalTime();
		if (!isSameDay(timeSlot))
			return false;
		return (!startTime.isBefore(this.startTime) && !startTime.isAfter(this.stopTime)
				&& !stopTime.isBefore(startTime) && !stopTime.isAfter(this.stopTime));
	}

	private boolean isSameDay(TimeSlot timeSlot) {
		return timeSlot.getTimeSlotStart().toLocalDate().isEqual(timeSlot.getTimeSlotStop().toLocalDate());
	}

	@Override
	public String toString() {
		return startTime + "-" + stopTime;
	}

}
