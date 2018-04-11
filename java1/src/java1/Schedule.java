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
	
	public int getNumberOfAppointmentsOnDate(BookingTimeSlot timeSlot) {
		String day = timeSlot.getAppointmentDate().toString();
		if(schedule.containsKey(day)) {
			return schedule.get(day).size();
		}
		return 0;
	}
	
	public boolean tryAddClientToSchedule(Booking booking) {
		BookingTimeSlot timeSlot = booking.getBooking();
		if(isTimeSlotAvailable(timeSlot)) {
			addToSchedule(booking);
			return true;
		}
		return false;
	}
	
	public boolean isTimeSlotAvailable(BookingTimeSlot timeSlot) {
		String dateDay = timeSlot.getAppointmentDate().toString();
		if(schedule.containsKey(dateDay)) {
			List<Booking> bookings = schedule.get(dateDay);
			for(Booking booking : bookings) {
				BookingTimeSlot bTimeSlot = booking.getBooking();
				if(bTimeSlot.doesTimeSlotsCollide(timeSlot)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void addToSchedule(Booking booking) {
		String date = booking.getBooking().getAppointmentDate().toString();
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
