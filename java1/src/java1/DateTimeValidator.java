package java1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeValidator {
	public static LocalDate isValidDate(String dateString) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(dateString, formatter);
			return date;
		} catch(DateTimeParseException e) {
			return null;
		}	
	}
	
	public static LocalTime isValidTime(String timeString) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime time = LocalTime.parse(timeString, formatter);	
			return time;
		} catch(DateTimeParseException e) {
			return null;
		}	
	}
}
