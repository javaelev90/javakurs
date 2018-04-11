package java1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeValidator {
	public static LocalDateTime getValidDateTime(String dateString) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime date = LocalDateTime.parse(dateString, formatter);
			return date;
		} catch(DateTimeParseException e) {
			return null;
		}	
	}
}
