package java1;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

public class MenuPrinter extends PrintWriter {

	public MenuPrinter(PrintStream out) {
		super(out);
	}

	public void printMainMenu() {
		println("----Main menu----");
		println("1. New Booking");
		println("2. Show bookings");
		println("3. Administrate employees");
		println("4. Exit");
		flush();
	}

	public void printAdministrateEmployeesMenu() {
		println("----Employee administration menu----");
		println("1. Show employees");
		println("2. Create employee");
		println("3. Delete employee");
		println("4. Main menu");
		flush();
	}

	public void printDeleteEmployeeOption() {
		println("Enter the integer id of the employee you want to remove(leave blank to exit): ");
		flush();
	}

	public void printCreateEmployeeFirstName() {
		println("Enter first name of employee(leave blank to exit): ");
		flush();
	}

	public void printCreateEmployeeLastName() {
		println("Enter last name of employee(leave blank to exit): ");
		flush();
	}

	public void printMainMenuNewBookingOptionStartTime() {
		println("Enter a start datetime for appointment(YYYY-MM-DD HH:MM), leave empty to exit: ");
		flush();
	}

	public void printMainMenuNewBookingOptionEndTime() {
		println("Enter a stop datetime for appointment(YYYY-MM-DD HH:MM), leave empty to exit: ");
		flush();
	}

	public void printInvalidMenuOption() {
		println("Invalid menu option.");
		flush();
	}

	public void printInvalidDateTime(String datetime) {
		format("Invalid datetime: %s should have format YYYY-MM-DD HH:MM \n", datetime);
		flush();
	}

	public void printNoEmployeesAvailable() {
		println("No employees were available at that date and time.");
		flush();
	}

	public void printStartTimeMustBeBeforeStopTime() {
		println("Stop time has to be after start time.");
		flush();
	}

	public void printOperationNotSuccessful() {
		println("Could not perform operation! :D");
		flush();
	}

	public void printOperationSucceeded() {
		println("Operation succeeded!");
		flush();
	}

	public void printInputHasToBeAnInteger() {
		println("Input has to be an integer.");
		flush();
	}

	public void printAllEmployees(List<Employee> employees) {
		println("----All employees----");
		if (employees.isEmpty()) {
			println("There are no employees!");
		} else {
			for (Employee employee : employees) {
				println(employee.toString());
				println("");
			}
		}
		flush();
	}

	public void printBookings(List<Employee> employees) {

		println("----Bookings per employee----");
		if (employees.isEmpty()) {
			println("There are no bookings!");
		} else {
			for (Employee employee : employees) {
				println(employee.toString() + ":");
				println(employee.getSchedule().toString());
			}
		}
		flush();
	}
}
