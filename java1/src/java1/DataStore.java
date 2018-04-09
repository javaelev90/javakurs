package java1;

import java.time.LocalDateTime;
import java.util.List;

public interface DataStore {
	public boolean storeDate(Employee user, Client client, LocalDateTime localDateTime);
	public boolean storeNewEmployee(Employee user);
	public boolean updateEmployee(Employee user);
	public Employee getEmployee(int employeeId);
	public List<Client> getEmployeeClientsForDate(int employeeId, LocalDateTime dateToCheck);
}
