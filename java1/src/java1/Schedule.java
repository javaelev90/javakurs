package java1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * Each employee has a schedule
 */
public class Schedule {

	// String key representing the date of a day, which holds the bookings for the
	// day
	private HashMap<String, WorkDay> schedule;

	public Schedule() {
		schedule = new HashMap<String, WorkDay>();
	}

	public int getNumberOfAppointmentsOnDate(TimeSlot timeSlot) {
		String day = timeSlot.getTimeSlotStart().toLocalDate().toString();
		if (schedule.containsKey(day)) {
			return schedule.get(day).getBookings().size();
		}
		return 0;
	}

	public Optional<List<Booking>> getBookingsOnDate(LocalDate date) {
		String day = date.toString();
		if (schedule.containsKey(day)) {
			return Optional.of(schedule.get(day).getBookings());
		}
		return Optional.empty();
	}

	public boolean isTimeSlotAvailable(TimeSlot timeSlot) {
		WorkDayLimits limits = getWorkDayLimitsOnDate(timeSlot);
		if (!limits.isWithinLimits(timeSlot))
			return false;

		String dateDay = timeSlot.getTimeSlotStart().toLocalDate().toString();
		if (schedule.containsKey(dateDay)) {
			List<Booking> bookings = schedule.get(dateDay).getBookings();
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
		List<Booking> bookings = schedule.get(date).getBookings();
		Optional<Booking> foundBooking = bookings.stream().filter(timeslot -> timeslot.getBookingTime()
				.getTimeSlotStart().isEqual(booking.getBookingTime().getTimeSlotStart())).findFirst();
		if (!foundBooking.isPresent()) {
			return false;
		}
		bookings.remove(foundBooking.get());
		return true;

	}

	// TODO might re-implement this so that it is more flexible
	private WorkDayLimits getDefaultWorkDayLimits() {
		Optional<Properties> props = AppProperties.getProperties();
		if(!props.isPresent()) {
			return null;
		}
		String startString = props.get().getProperty("defaultDayLimitsStart");
		String stopString = props.get().getProperty("defaultDayLimitsStop");
		LocalTime start = DateTimeValidator.getValidTime(startString);
		LocalTime stop = DateTimeValidator.getValidTime(stopString);
		return new WorkDayLimits(start, stop);
	}

	private WorkDayLimits getWorkDayLimitsOnDate(TimeSlot timeSlot) {
		String date = timeSlot.getTimeSlotStart().toLocalDate().toString();
		if (!schedule.containsKey(date)) {
			return getDefaultWorkDayLimits();
		}
		return schedule.get(timeSlot.getTimeSlotStart().toLocalDate().toString()).getWorkingLimits();
	}

	private void addToSchedule(Booking booking) {
		String date = booking.getBookingTime().getTimeSlotStart().toLocalDate().toString();
		List<Booking> bookings;
		if (!schedule.containsKey(date)) {
			LocalDate localDate = booking.getBookingTime().getTimeSlotStart().toLocalDate();
			WorkDay workDay = new WorkDay(localDate);
			bookings = new ArrayList<Booking>();
			workDay.setBookings(bookings);
			workDay.setWorkingLimits(getDefaultWorkDayLimits());
			schedule.put(date, workDay);
		} else {
			bookings = schedule.get(date).getBookings();
		}
		bookings.add(booking);
	}

	public WorkDayLimits getScheduleLimits(LocalDate date) {
		String dateString = date.toString();
		if (!schedule.containsKey(dateString)) {
			return null;
		}
		return schedule.get(dateString).getWorkingLimits();
	}

	public boolean isEmpty() {
		return schedule.isEmpty();
	}

	@Override
	public String toString() {
		if (schedule.isEmpty()) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, WorkDay> entry : schedule.entrySet()) {
			builder.append(entry.getValue().toString());
			builder.append("\n");
		}
		return builder.toString();
	}

}
