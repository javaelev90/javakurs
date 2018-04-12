package java1;

import java.util.List;

import java1.model.Booking;
import java1.model.Employee;

public interface DataStore {
	public boolean storeBooking(int employeeId, Booking booking);
	public boolean storeNewEmployee(Employee employee);
	public boolean updateEmployee(int employeeId, Employee updatedUser);
	public boolean deleteEmployee(int employeeId);
	public Employee getEmployee(int employeeId);
	public List<Employee> getAllEmployees();
}
