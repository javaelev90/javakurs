package java1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForgetfulDataStore implements DataStore {


	private HashMap<Integer, Employee> employees;
	
	public ForgetfulDataStore() {
		employees = new HashMap<Integer, Employee>();
	}
	
	@Override
	public boolean storeBooking(int employeeId, Booking booking) {
		Employee employee = employees.get(employeeId);
		if(employee.getSchedule().tryAddClientToSchedule(booking)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean storeNewEmployee(Employee employee) {
		if(!employees.containsKey(employee.getId())) {
			employees.put(employee.getId(), employee);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateEmployee(int employeeId, Employee updatedEmployee) {
		if(employeeId == updatedEmployee.getId() && employees.containsKey(updatedEmployee.getId())) {
			employees.put(employeeId, updatedEmployee);	
			return true;
		}
		return false;
	}

	@Override
	public Employee getEmployee(int employeeId) {
		return employees.get(employeeId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> allEmployees = new ArrayList<Employee>(employees.values());
		
		return allEmployees;
	}

}
