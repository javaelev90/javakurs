package java1.model;

import java.time.LocalDate;
import java.util.List;

public class Day {
	
	private LocalDate date;
	private List<Booking> bookings;
	
	public List<Booking> getBookings(){
		return bookings;
	}
	
	public LocalDate getLocalDate() {
		return date;
	}
	
}
