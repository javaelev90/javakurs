package java1.handlers;

import java.util.List;

import java1.model.Booking;
import java1.model.Employee;
import java1.model.TimeSlot;

public interface EmployeeHandler {
	public List<Employee> getBookedEmployees();

	public boolean bookEmployee(Employee employee, Booking booking);

	public List<Employee> getAvailableEmployees(TimeSlot date);
	
	public Employee getEmployeeWithFewestBookingsOnDate(TimeSlot date, List<Employee> employees);
}
