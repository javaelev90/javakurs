package java1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DataStore {
	
	public boolean deleteBookingOnDateTime(int employeeId, Booking booking);
	public List<Booking> getBookingsOnDate(int employeeId, LocalDate date);
	public Schedule getSchedule(int employeeId);
	public boolean saveBooking(int employeeId, Booking booking);
	public boolean storeNewEmployee(Employee employee);
	public boolean updateEmployee(int employeeId, Employee updatedUser);
	public Employee getEmployee(int employeeId);
	public List<Employee> getAllEmployees();
}
