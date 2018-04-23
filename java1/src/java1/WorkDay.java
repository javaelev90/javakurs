package java1;

import java.time.LocalDate;
import java.util.List;

public class WorkDay {
	
	private LocalDate date;
	private WorkDayLimits workingLimits;
	private List<Booking> bookings;

	public WorkDay(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public WorkDayLimits getWorkingLimits() {
		return workingLimits;
	}

	public void setWorkingLimits(WorkDayLimits workingLimits) {
		this.workingLimits = workingLimits;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public boolean equals(LocalDate date) {
		return date.isEqual(date);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("-").append(date.toString()).append(" working: ").append("(" + workingLimits.toString() + ")")
				.append("\n");
		bookings.forEach(booking -> builder.append("--").append(booking.toString()).append("\n"));
		return builder.toString();
	}
}
