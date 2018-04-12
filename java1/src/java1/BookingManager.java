package java1;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class BookingManager {

	private enum BookingStages {
		STARTDATETIME, STOPDATETIME, MAKEBOOKING, EXIT
	};

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
		BookingStages stage = BookingStages.STARTDATETIME;
		TimeSlot timeSlot = new TimeSlot();
		while (!wantToExit) {

			switch (stage) {

			case STARTDATETIME:
				menu.printMainMenuNewBookingOptionStartTime();
				String startTimeString = input.getTextInput();
				if (startTimeString.equals("")) {
					stage = BookingStages.EXIT;
					break;
				}
				LocalDateTime startTime = DateTimeValidator.getValidDateTime(startTimeString);
				if (startTime == null) {
					menu.printInvalidDateTime(startTimeString);
					break;
				}
				timeSlot.setTimeSlotStart(startTime);
				// If validation worked goto next step
				stage = BookingStages.STOPDATETIME;
				break;
			case STOPDATETIME:
				menu.printMainMenuNewBookingOptionEndTime();
				String stopTimeString = input.getTextInput();
				if (stopTimeString.equals("")) {
					stage = BookingStages.EXIT;
					break;
				}
				LocalDateTime stopTime = DateTimeValidator.getValidDateTime(stopTimeString);
				if (stopTime == null) {
					menu.printInvalidDateTime(stopTimeString);
					break;
				}
				if (timeSlot.getTimeSlotStart().isAfter(stopTime)) {
					menu.printStartTimeMustBeBeforeStopTime();
					break;
				}
				timeSlot.setTimeSlotTop(stopTime);
				// If validation worked goto next step
				stage = BookingStages.MAKEBOOKING;
				break;
			case MAKEBOOKING:
				List<Employee> employees = employeeHandler.getAvailableEmployees(timeSlot);
				Employee employee = employeeHandler.getEmployeeWithFewestBookingsOnDate(timeSlot, employees);
				if (employee == null) {
					stage = BookingStages.EXIT;
					menu.printNoEmployeesAvailable();
					break;
				}
				Booking booking = new Booking();
				booking.setBooking(timeSlot);
				employeeHandler.bookEmployee(employee, booking);
				stage = BookingStages.EXIT;
				break;
			case EXIT:
				wantToExit = true;
				break;
			default:
				menu.printInvalidMenuOption();
				break;
			}
		}
	}

	
}
