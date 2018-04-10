package java1;

import java.time.LocalDate;
import java.util.List;

public interface DataStore {
	public boolean storeDate(Employee employee, Client client);
	public boolean storeNewEmployee(Employee employee);
	public boolean updateEmployee(int employeeId, Employee updatedUser);
	public Employee getEmployee(int employeeId);
	public List<Client> getEmployeeClientsForDate(int employeeId, LocalDate dateToCheck);
}
