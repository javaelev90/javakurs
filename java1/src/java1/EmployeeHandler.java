package java1;

import java.util.List;

public interface EmployeeHandler {
	public List<Employee> getBookedEmployees();
	public boolean bookEmployee(Employee employee, Booking booking);
	public Employee getEmployeeWithFewestBookingsOnDate(BookingTimeSlot date);
}
