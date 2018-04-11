package java1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
	
	//Date string key representing a day, which holds the clients for the day
	private HashMap<String, List<Booking>> schedule;
	
	
	public Schedule() {
		schedule = new HashMap<String, List<Booking>>();
	}
	
	public int getNumberOfAppointmentsOnDate(TimeSlot timeSlot) {
		String day = timeSlot.getTimeSlotStart().toLocalDate().toString();
		if(schedule.containsKey(day)) {
			return schedule.get(day).size();
		}
		return 0;
	}
	
	public boolean tryAddClientToSchedule(Booking booking) {
		TimeSlot timeSlot = booking.getBooking();
		if(isTimeSlotAvailable(timeSlot)) {
			addToSchedule(booking);
			return true;
		}
		return false;
	}
	
	public boolean isTimeSlotAvailable(TimeSlot timeSlot) {
		String dateDay = timeSlot.getTimeSlotStart().toLocalDate().toString();
		if(schedule.containsKey(dateDay)) {
			List<Booking> bookings = schedule.get(dateDay);
			for(Booking booking : bookings) {
				TimeSlot bTimeSlot = booking.getBooking();
				if(bTimeSlot.doesTimeSlotsCollide(timeSlot)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void addToSchedule(Booking booking) {
		String date = booking.getBooking().getTimeSlotStart().toLocalDate().toString();
		List<Booking> bookings;
		if(!schedule.containsKey(date)) {
			bookings = new ArrayList<Booking>();
		} else {
			bookings = schedule.get(date);
		}
		bookings.add(booking);
		schedule.put(date, bookings);
	}
	
	@Override
	public String toString() {
		if(schedule.isEmpty()) {
			return "";
		}
		String strVersion = "";
		for(Map.Entry<String, List<Booking>> entry : schedule.entrySet()) {
			strVersion += entry + ":\n";
			for(Booking booking : entry.getValue()) {
				strVersion += booking.getBooking().toString() + "\n";
			}
		}
		return strVersion;	
	}
	
	public boolean isEmpty() {
		return schedule.isEmpty();
	}
	
}
