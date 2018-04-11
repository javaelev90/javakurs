package java1;

import java.util.List;
import java.util.stream.Collectors;

public class ForgetfulEmployeeHandler implements EmployeeHandler {

	private DataStore dataStore;

	public ForgetfulEmployeeHandler(DataStore dataStore) {
		this.dataStore = dataStore;
	}

	@Override
	public List<Employee> getBookedEmployees() {
		List<Employee> employees = dataStore.getAllEmployees();
		employees = employees.stream().filter(employee -> !employee.getSchedule().isEmpty())
				.collect(Collectors.toList());
		return employees;
	}

	@Override
	public boolean bookEmployee(Employee employee, Booking booking) {
		if (dataStore.storeBooking(employee.getId(), booking)) {
			return true;
		}
		return false;
	}

	@Override
	public List<Employee> getAvailableEmployees(TimeSlot date) {
		List<Employee> employees = dataStore.getAllEmployees();
		employees = employees.stream().filter(employee -> employee.getSchedule().isTimeSlotAvailable(date))
				.collect(Collectors.toList());
		return employees;
	}
}
