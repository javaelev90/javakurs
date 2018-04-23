package java1;

import java.time.temporal.ChronoUnit;

/**
 * 
 * Holder class which could easily be extended to hold customer information
 *
 */
public class Booking {

	private static final double MINUTEPRICE = 1;
	
	private TimeSlot bookingTime;
	private double price;

	public TimeSlot getBookingTime() {
		return bookingTime;
	}

	public void setBooking(TimeSlot bookingTime) {
		this.bookingTime = bookingTime;
	}

	public double calculatePrice() {
		long time = bookingTime.getTimeSlotStart().until(bookingTime.getTimeSlotStop(),ChronoUnit.MINUTES);
		price = time * MINUTEPRICE;
		return price;
	}
	
	public String toString() {
		return bookingTime.toString() + " Price: "+calculatePrice();
	}
	
}
