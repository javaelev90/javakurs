package java1;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeHandler {
	public List<Client> checkAvailableEmployees(LocalDateTime date);
	public boolean bookAvailableEmployee(Employee employee, Client client, LocalDateTime date);
}
