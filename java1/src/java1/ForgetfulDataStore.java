package java1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForgetfulDataStore implements DataStore {


	private HashMap<Integer, Employee> employees;
	
	public ForgetfulDataStore() {
		employees = new HashMap<Integer, Employee>();
	}
	
	@Override
	public boolean saveBooking(int employeeId, Booking booking) {
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

	@Override
	public boolean deleteBookingOnDateTime(int employeeId, Booking booking) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Booking> getBookingsOnDate(int employeeId, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getAllBooking(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
