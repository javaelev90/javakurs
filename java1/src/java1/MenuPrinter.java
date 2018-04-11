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
		println("3. Exit");
		flush();
	}
	
	public void printMainMenuBookClientsOptionStartTime() {
		println("\nEnter a start datetime for appointment(YYYY-MM-DD HH:MM): ");
		flush();
	}
	
	public void printMainMenuBookClientsOptionEndTime() {
		println("\nEnter a stop datetime for appointment(YYYY-MM-DD HH:MM): ");
		flush();
	}
	
	public void printInvalidMenuOption() {
		println("Invalid menu option.");
		flush();
	}
	
	public void printInvalidDateTime(String datetime) {
		format("Invalid datetime: %s, should have format YYYY-MM-DD HH:MM \n", datetime);
		flush();
	}
	
	public void printNoEmployeesAvailable() {
		println("No employees were available at that date and time.");
		flush();
	}
	
	public void printBookings(List<Employee> employees) {
		println("----Bookings per employee----");
		for(Employee e : employees) {
			println(e.getFullName()+":");
			println(e.getSchedule().toString());
		}
		flush();
	}
}
