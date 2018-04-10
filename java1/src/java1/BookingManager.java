package java1;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingManager {
	
	private enum BookingStages {AddDate, AddStartTime, AddEndTime, MakeBooking, Exit};
	private EmployeeHandler employeeHandler;
	
	public BookingManager(EmployeeHandler employeeHandler) {
		this.employeeHandler = employeeHandler;
	}
	
	public void makeBookingTimeSlot(InputHandler input, MenuPrinter menu) throws IOException {
		boolean wantToExit = false;
		BookingStages stage = BookingStages.AddDate;
		BookingTimeSlot booking = new BookingTimeSlot();
		while(!wantToExit) {
			
			switch(stage) {
			
			case AddDate:
				menu.printMainMenuBookClientsOptionDate();
				String dateString = input.getTextInput();
				if(dateString.equals("")) { 
					stage = BookingStages.Exit; 
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
					stage = BookingStages.Exit; 
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
					stage = BookingStages.Exit; 
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
				break;
			case Exit:
				//TODO fix this structure
				wantToExit = true;
			
			default:
				menu.printInvalidMenuOption();
				break;
			}
		}
	}

}
