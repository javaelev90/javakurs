package java1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Each employee has a schedule
 */
public class Schedule {

	// String key representing the date of a day, which holds the bookings for the
	// day
	private HashMap<String, List<Booking>> schedule;
	private WorkingDayLimits limits;

	public Schedule(WorkingDayLimits limits) {
		schedule = new HashMap<String, List<Booking>>();
		this.limits = limits;
	}

	public int getNumberOfAppointmentsOnDate(TimeSlot timeSlot) {
		String day = timeSlot.getTimeSlotStart().toLocalDate().toString();
		if (schedule.containsKey(day)) {
			return schedule.get(day).size();
		}
		return 0;
	}

	public Optional<List<Booking>> getBookingsOnDate(LocalDate date) {
		String day = date.toString();
		if (schedule.containsKey(day)) {
			return Optional.of(schedule.get(day));
		}
		return Optional.empty();
	}

	public boolean isTimeSlotAvailable(TimeSlot timeSlot) {

		if (!limits.isWithinLimits(timeSlot))
			return false;

		String dateDay = timeSlot.getTimeSlotStart().toLocalDate().toString();
		if (schedule.containsKey(dateDay)) {
			List<Booking> bookings = schedule.get(dateDay);
			for (Booking booking : bookings) {
				if (booking.getBookingTime().doTimeSlotsCollide(timeSlot)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean tryAddClientToSchedule(Booking booking) {
		TimeSlot timeSlot = booking.getBookingTime();
		if (isTimeSlotAvailable(timeSlot)) {
			addToSchedule(booking);
			return true;
		}
		return false;
	}

	public boolean tryDeleteBookingFromSchedule(Booking booking) {
		String date = booking.getBookingTime().getTimeSlotStart().toLocalDate().toString();
		if (!schedule.containsKey(date)) {
			return false;
		}
		List<Booking> bookings = schedule.get(date);
		Optional<Booking> foundBooking = bookings.stream().filter(timeslot -> timeslot.getBookingTime()
				.getTimeSlotStart().isEqual(booking.getBookingTime().getTimeSlotStart())).findFirst();
		if (!foundBooking.isPresent()) {
			return false;
		}
		bookings.remove(foundBooking.get());
		return true;

	}

	private void addToSchedule(Booking booking) {
		String date = booking.getBookingTime().getTimeSlotStart().toLocalDate().toString();
		List<Booking> bookings;
		if (!schedule.containsKey(date)) {
			bookings = new ArrayList<Booking>();
		} else {
			bookings = schedule.get(date);
		}
		bookings.add(booking);
		schedule.put(date, bookings);
	}

	public WorkingDayLimits getScheduleLimits() {
		return limits;
	}

	public boolean isEmpty() {
		return schedule.isEmpty();
	}

	@Override
	public String toString() {
		if (schedule.isEmpty()) {
			return "";
		}
		String strVersion = "";
		for (Map.Entry<String, List<Booking>> entry : schedule.entrySet()) {
			strVersion += "-" + entry.getKey() + "(working:" + limits.toString() + ")\n";
			for (Booking booking : entry.getValue()) {
				strVersion += "--" + booking.getBookingTime().toString() + "\n";
			}
		}
		return strVersion;
	}

}
