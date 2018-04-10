package java1;

import java.util.List;

public interface DataStore {
	public boolean storeBooking(Employee employee, Client client);
	public boolean storeNewEmployee(Employee employee);
	public boolean updateEmployee(int employeeId, Employee updatedUser);
	public Employee getEmployee(int employeeId);
	public List<Employee> getAllEmployees();
}
