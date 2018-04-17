package java1;

import java.util.ArrayList;
import java.util.List;

public class JsonEmployeeHandler implements EmployeeHandler {

	private DataStore dataStore;
	
	public JsonEmployeeHandler(DataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	@Override
	public List<Employee> getBookedEmployees() {
		List<Employee> employees = dataStore.getAllEmployees();
		List<Employee> bookedEmployees = new ArrayList<Employee>();
		if(employees == null){
			return null;
		}
		for(Employee employee : employees) {
			Schedule schedule = dataStore.getSchedule(employee.getId());
			if(schedule == null) {
				schedule = new Schedule();
			} 
			if(!schedule.isEmpty()) {
				employee.setSchedule(schedule);
				bookedEmployees.add(employee);
			}
		}
		return bookedEmployees;
	}

	@Override
	public boolean bookEmployee(Employee employee, Booking booking) {
		// TODO Auto-generated method stub
		return dataStore.saveBooking(employee.getId(), booking);
	}

	@Override
	public List<Employee> getAvailableEmployees(TimeSlot date) {
		// TODO Auto-generated method stub
		List<Employee> employees = dataStore.getAllEmployees();
		List<Employee> availableEmployees = new ArrayList<Employee>();
		if(employees == null) {
			return null;
		}
		for(Employee employee : employees) {
			Schedule schedule = dataStore.getSchedule(employee.getId());
			if(schedule == null) {
				schedule = new Schedule();
			} 
			if(schedule.isTimeSlotAvailable(date)) {
				employee.setSchedule(schedule);
				availableEmployees.add(employee);
			}
		}
		return availableEmployees;
	}

	@Override
	public Employee getEmployeeWithFewestBookingsOnDate(TimeSlot date, List<Employee> employees) {
		// TODO Auto-generated method stub
		int leastNumberOfBookings = Integer.MAX_VALUE;
		if (!employees.isEmpty()) {
			Employee employeeWithFewestBookings = employees.get(0);
			for (int i = 0; i < employees.size(); i++) {
				int numberOfBookings = employees.get(i).getSchedule().getNumberOfAppointmentsOnDate(date);
				if (numberOfBookings < leastNumberOfBookings) {
					leastNumberOfBookings = numberOfBookings;
					employeeWithFewestBookings = employees.get(i);
				}
			}
			return employeeWithFewestBookings;
		}
		return null;
	}

}
