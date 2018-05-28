package java1.main;

import java.util.List;

public interface DataStore {
	public boolean storeBooking(int employeeId, Booking booking);
	public boolean storeNewEmployee(Employee employee);
	public boolean updateEmployee(int employeeId, Employee updatedUser);
	public Employee getEmployee(int employeeId);
	public List<Employee> getAllEmployees();
}
