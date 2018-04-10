package java1;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ClientManager {
	
	private enum BookingStages {AddDate, AddStartTime, AddEndTime, MakeBooking, EXIT};
	
	
	public Booking makeBooking(InputHandler input, MenuPrinter menu) throws IOException {
		boolean wantToExit = false;
		BookingStages stage = BookingStages.AddDate;
		Booking booking = new Booking();
		while(!wantToExit) {
			
			switch(stage) {
			
			case AddDate:
				menu.printMainMenuBookClientsOptionDate();
				String dateString = input.getTextInput();
				if(dateString.equals("")) { 
					stage = BookingStages.EXIT; 
					break;
				}
				LocalDate date = DateTimeValidator.isValidDate(dateString);
				if(date == null) {
					menu.printInvalidDate(dateString);
					break;
				} 
				booking.setAppointmentDate(date);
				//If validation worked goto next step
				stage = BookingStages.AddStartTime;
				break;
			case AddStartTime:
				menu.printMainMenuBookClientsOptionStartTime();
				String startTimeString = input.getTextInput();
				if(startTimeString.equals("")) { 
					stage = BookingStages.EXIT; 
					break;
				}
				LocalTime startTime = DateTimeValidator.isValidTime(startTimeString);
				if(startTime == null) {
					menu.printInvalidTime(startTimeString);
					break;
				}
				booking.setEventStartTime(startTime);
				//If validation worked goto next step
				stage = BookingStages.AddEndTime;
				break;
			case AddEndTime:
				menu.printMainMenuBookClientsOptionEndTime();
				String stopTimeString = input.getTextInput();
				if(stopTimeString.equals("")) { 
					stage = BookingStages.EXIT; 
					break;
				}
				LocalTime stopTime = DateTimeValidator.isValidTime(stopTimeString);
				if(stopTime == null) {
					menu.printInvalidTime(stopTimeString);
					break;
				}
				booking.setEventStopTime(stopTime);
				//If validation worked goto next step
				stage = BookingStages.MakeBooking;
				break;
			case MakeBooking:
				//TODO fix this structure
				return booking;
			case EXIT:
				//TODO fix this structure
				return null;
			
			default:
				menu.printInvalidMenuOption();
				break;
			
			}
		}
		return null;
	}
}
