package java1;

import java.util.List;
import java.util.stream.Collectors;

public class ForgetfulEmployeeHandler implements EmployeeHandler{
	
	private DataStore dataStore;
	
	public ForgetfulEmployeeHandler(DataStore dataStore) {
		this.dataStore = dataStore;
		
	}
	
	@Override
	public List<Employee> getBookedEmployees() {
		List<Employee> employees = dataStore.getAllEmployees();
		employees = employees.stream().filter(employee -> !employee.getSchedule().isEmpty()).collect(Collectors.toList());
		return employees;
	}

	@Override
	public boolean bookEmployee(Employee employee, Booking booking) {
		if(dataStore.storeBooking(employee.getId(), booking)) {
			return true;
		}
		return false;
	}

	@Override
	public Employee getEmployeeWithFewestBookingsOnDate(TimeSlot date) {
		List<Employee> employees = getAvailableEmployees(date);
		int leastNumberOfBookings = Integer.MAX_VALUE;
		if(!employees.isEmpty()) {
			Employee employeeWithFewestBookings = employees.get(0);
			for(int i = 0; i < employees.size(); i++) {
				int numberOfBookings = employees.get(i).getSchedule().getNumberOfAppointmentsOnDate(date);
				if(numberOfBookings < leastNumberOfBookings) {
					leastNumberOfBookings = numberOfBookings;
					employeeWithFewestBookings = employees.get(i);
				}
			}
			return employeeWithFewestBookings;
		}
		return null;
	}
	
	private List<Employee> getAvailableEmployees(TimeSlot date) {
		// TODO Auto-generated method stub
		List<Employee> employees = dataStore.getAllEmployees();
		employees = employees.stream().filter(employee -> employee.getSchedule().isTimeSlotAvailable(date)).collect(Collectors.toList());
		return employees;
	}
}
