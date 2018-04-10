package java1;

import java.util.List;

public class ForgetfulEmployeeHandler implements EmployeeHandler{
	
	private DataStore dataStore;
	
	public ForgetfulEmployeeHandler(DataStore dataStore) {
		// TODO Auto-generated constructor stub
		this.dataStore = dataStore;
		
	}
	
	@Override
	public List<Employee> checkAvailableEmployees(BookingTimeSlot date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean bookAvailableEmployee(Employee employee, Client client) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
