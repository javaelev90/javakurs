package java1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForgetfulDataStore implements DataStore {


	private List<Employee> employees = new ArrayList<Employee>();
	private HashMap<Employee, Schedule> schdule = new HashMap<Employee, Schedule>();
	
	@Override
	public boolean storeBooking(Employee employee, Client client) {
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
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

}
