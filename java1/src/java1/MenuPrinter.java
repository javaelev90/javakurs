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
	
	public void printMainMenuBookClientsOptionDate() {
		print("\nEnter date you would like to book(YYYY-MM-DD): ");
		flush();
	}
	
	public void printMainMenuBookClientsOptionStartTime() {
		print("\nEnter a start time for appointment(HH:MM): ");
		flush();
	}
	
	public void printMainMenuBookClientsOptionEndTime() {
		print("\nEnter a stop time for appointment(HH:MM): ");
		flush();
	}
	
	public void printInvalidMenuOption() {
		println("Invalid menu option.");
		flush();
	}
	
	public void printInvalidDate(String date) {
		format("\nInvalid date: %s, should have format YYYY-MM-DD", date);
		flush();
	}
	
	public void printInvalidTime(String time) {
		format("\nInvalid time: %s, should have format HH:MM", time);
		flush();
	}
	
	public void printNoEmployeesAvailable() {
		println("No employees were available at that date and time.");
		flush();
	}
	
	public void printBookings(List<Employee> employees) {
		//TODO wont show name now
		println("----Bookings per employee----");
		for(Employee e : employees) {
			println(e.getFullName()+":");
			println(e.getSchedule().toString());
		}
		flush();
	}
}
