package java1;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeValidator {
	public static LocalDateTime getValidDateTime(String dateString) throws DateTimeParseException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = LocalDateTime.parse(dateString, formatter);
		return date;
		
	}

	public static LocalTime getValidTime(String dateString) throws DateTimeParseException{
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime date = LocalTime.parse(dateString, formatter);
		return date;
		
	}
}
