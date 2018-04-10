package java1;

import java.util.List;

public interface EmployeeHandler {
	public List<Employee> checkAvailableEmployees(BookingTimeSlot date);
	public boolean bookAvailableEmployee(Employee employee, Client client);
	
}
