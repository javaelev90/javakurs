package java1;

import java.time.LocalDate;
import java.util.List;

public class ForgetfulDataStore implements DataStore {

	@Override
	public boolean storeDate(Employee employee, Client client) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean storeNewEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployee(int employeeId, Employee updatedEmployee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee getEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getEmployeeClientsForDate(int employeeId, LocalDate dateToCheck) {
		// TODO Auto-generated method stub
		return null;
	}

}
