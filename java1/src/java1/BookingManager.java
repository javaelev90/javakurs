package java1;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {

	private enum BookingStages {
		STARTDATETIME, STOPDATETIME, MAKEBOOKING, EXIT
	};

	private DataStore dataStore;

	public BookingManager(DataStore dataStore) {
		this.dataStore = dataStore;
	}

	public void showAllBookings(MenuPrinter menu) {
		List<Employee> employees = getBookedEmployees();
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
				try {
					LocalDateTime startTime = DateTimeValidator.getValidDateTime(startTimeString);
					timeSlot.setTimeSlotStart(startTime);
					// If validation worked goto next step
					stage = BookingStages.STOPDATETIME;
					break;
				} catch (DateTimeParseException exception) {
					menu.printInvalidDateTime(startTimeString);
					break;
				}
			case STOPDATETIME:
				menu.printMainMenuNewBookingOptionEndTime();
				String stopTimeString = input.getTextInput();
				if (stopTimeString.equals("")) {
					stage = BookingStages.EXIT;
					break;
				}
				try {
					LocalDateTime stopTime = DateTimeValidator.getValidDateTime(stopTimeString);
					if (timeSlot.getTimeSlotStart().isAfter(stopTime)) {
						menu.printStartTimeMustBeBeforeStopTime();
						break;
					}
					timeSlot.setTimeSlotTop(stopTime);
					// If validation worked goto next step
					stage = BookingStages.MAKEBOOKING;
					break;
				} catch (DateTimeParseException exception) {
					menu.printInvalidDateTime(stopTimeString);
					break;
				}
			case MAKEBOOKING:
				List<Employee> employees = getAvailableEmployees(timeSlot);
				Employee employee = getEmployeeWithFewestBookingsOnDate(timeSlot, employees);

				if (employee == null) {
					stage = BookingStages.EXIT;
					menu.printNoEmployeesAvailable();
					break;
				}
				Booking booking = new Booking();
				booking.setBooking(timeSlot);
				bookEmployee(employee, booking);
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

	private List<Employee> getBookedEmployees() {
		List<Employee> employees = dataStore.getAllEmployees();
		List<Employee> bookedEmployees = new ArrayList<Employee>();
		if (employees == null) {
			return null;
		}
		for (Employee employee : employees) {
			Schedule schedule = dataStore.getSchedule(employee.getId());
			if (schedule == null) {
				schedule = new Schedule();
			}
			if (!schedule.isEmpty()) {
				employee.setSchedule(schedule);
				bookedEmployees.add(employee);
			}
		}
		return bookedEmployees;
	}

	private boolean bookEmployee(Employee employee, Booking booking) {
		return dataStore.saveBooking(employee.getId(), booking);
	}

	private List<Employee> getAvailableEmployees(TimeSlot date) {
		List<Employee> employees = dataStore.getAllEmployees();
		List<Employee> availableEmployees = new ArrayList<Employee>();
		if (employees == null) {
			return null;
		}
		for (Employee employee : employees) {
			Schedule schedule = dataStore.getSchedule(employee.getId());
			if (schedule == null) {
				schedule = new Schedule();
			}
			if (schedule.isTimeSlotAvailable(date)) {
				employee.setSchedule(schedule);
				availableEmployees.add(employee);
			}
		}
		return availableEmployees;
	}

	private Employee getEmployeeWithFewestBookingsOnDate(TimeSlot date, List<Employee> employees) {
		// Should not use MAX INTEGER value
		int leastNumberOfBookings = Integer.MAX_VALUE;
		if (!employees.isEmpty()) {
			Employee employeeWithFewestBookings = employees.get(0);
			for (int i = 0; i < employees.size(); i++) {
				int numberOfBookings = employees.get(i).getSchedule().getNumberOfAppointmentsOnDate(date);
				if (numberOfBookings < leastNumberOfBookings) {
					leastNumberOfBookings = numberOfBookings;
					employeeWithFewestBookings = employees.get(i);
				}
			}
			return employeeWithFewestBookings;
		}
		return null;
	}

}
