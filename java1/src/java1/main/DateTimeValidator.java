package java1.main;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeValidator {
	public static LocalDateTime getValidDateTime(String dateString) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime date = LocalDateTime.parse(dateString, formatter);
			return date;
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static LocalTime getValidTime(String dateString) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime date = LocalTime.parse(dateString, formatter);
			return date;
		} catch (DateTimeParseException e) {
			return null;
		}
	}
}
