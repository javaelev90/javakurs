package java1;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class BookingManager {
	
	private enum BookingStages {AddStartDateTime, AddEndDateTime, MakeBooking, Exit};
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
		BookingStages stage = BookingStages.AddStartDateTime;
		TimeSlot timeSlot = new TimeSlot();
		while(!wantToExit) {
			
			switch(stage) {
			
			case AddStartDateTime:
				menu.printMainMenuBookClientsOptionStartTime();
				String startTimeString = input.getTextInput();
				if(startTimeString.equals("")) { 
					stage = BookingStages.Exit; 
					break;
				}
				LocalDateTime startTime = DateTimeValidator.isValidDateTime(startTimeString);
				if(startTime == null) {
					menu.printInvalidDateTime(startTimeString);
					break;
				}
				timeSlot.setTimeSlotStart(startTime);
				//If validation worked goto next step
				stage = BookingStages.AddEndDateTime;
				break;
			case AddEndDateTime:
				menu.printMainMenuBookClientsOptionEndTime();
				String stopTimeString = input.getTextInput();
				if(stopTimeString.equals("")) { 
					stage = BookingStages.Exit; 
					break;
				}
				LocalDateTime stopTime = DateTimeValidator.isValidDateTime(stopTimeString);
				if(stopTime == null) {
					menu.printInvalidDateTime(stopTimeString);
					break;
				}
				if(!timeSlot.getTimeSlotStart().isAfter(stopTime)) {
					
					stage = BookingStages.AddEndDateTime;
				}
				timeSlot.setTimeSlotTop(stopTime);
				//If validation worked goto next step
				stage = BookingStages.MakeBooking;
				break;
			case MakeBooking:
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
				wantToExit = true;
				break;
			default:
				menu.printInvalidMenuOption();
				break;
			}
		}
	}
}
