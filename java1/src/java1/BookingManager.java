package java1;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BookingManager {
	
	private enum BookingStages {AddDate, AddStartTime, AddEndTime, MakeBooking, Exit};
	private EmployeeHandler employeeHandler;
	
	public BookingManager(EmployeeHandler employeeHandler) {
		this.employeeHandler = employeeHandler;
	}
	
	public void showAllBookings(MenuPrinter menu) {
		List<Employee> employees = employeeHandler.getBookedEmployees();
		menu.printBookings(employees);
	}
	
	public void tryToBookTime(InputHandler input, MenuPrinter menu) throws IOException {
		boolean wantToExit = false;
		BookingStages stage = BookingStages.AddDate;
		BookingTimeSlot timeSlot = new BookingTimeSlot();
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
				timeSlot.setAppointmentDate(date);
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
				timeSlot.setEventStartTime(startTime);
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
				timeSlot.setEventStopTime(stopTime);
				//If validation worked goto next step
				stage = BookingStages.MakeBooking;
				break;
			case MakeBooking:
				//TODO fix this structure
				if(timeSlot.getEventStartTime().isAfter(timeSlot.getEventStopTime())) {
					//TODO add print
					stage = BookingStages.AddStartTime;
				}
				Employee employee = employeeHandler.getEmployeeWithFewestBookingsOnDate(timeSlot);
				if(employee == null) {
					stage = BookingStages.Exit; 
					menu.printNoEmployeesAvailable();
					break;
				}
				Booking booking = new Booking();
				booking.setBooking(timeSlot);
				employeeHandler.bookEmployee(employee, booking);
				stage = BookingStages.Exit; 
				break;
			case Exit:
				//TODO fix this structure
				wantToExit = true;
				break;
			default:
				menu.printInvalidMenuOption();
				break;
			}
		}
	}


}
