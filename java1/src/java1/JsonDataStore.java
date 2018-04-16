package java1;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

public class JsonDataStore implements DataStore {

	private Gson gson;

	public JsonDataStore() {
		gson = new Gson();
	}

	@Override
	public boolean saveBooking(int employeeId, Booking booking) {

		Path path = FileHandler.getFilePathForEmployee(employeeId);
		Optional<String> jsonSchedule = FileHandler.readFromFile(path);
		if (!jsonSchedule.isPresent()) {
			return false;
		}
		Schedule schedule = gson.fromJson(jsonSchedule.get(), Schedule.class);
		if (schedule.tryAddClientToSchedule(booking)) {
			FileHandler.writeToFile(gson.toJson(schedule), path);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBookingOnDateTime(int employeeId, Booking booking) {

		Path path = FileHandler.getFilePathForEmployee(employeeId);
		Optional<String> jsonSchedule = FileHandler.readFromFile(path);
		if (!jsonSchedule.isPresent()) {
			return false;
		}
		Schedule schedule = gson.fromJson(jsonSchedule.get(), Schedule.class);
		if (schedule.tryDeleteBookingFromSchedule(booking)) {
			FileHandler.writeToFile(gson.toJson(schedule), path);
			return true;
		}
		return false;
	}

	@Override
	public List<Booking> getBookingsOnDate(int employeeId, LocalDate date) {
		Path path = FileHandler.getFilePathForEmployee(employeeId);
		Optional<String> jsonSchedule = FileHandler.readFromFile(path);
		if (!jsonSchedule.isPresent()) {
			return null;
		}
		Schedule schedule = gson.fromJson(jsonSchedule.get(), Schedule.class);
		return schedule.getBookingsOnDate(date).get();
	}

	@Override
	public List<Booking> getAllBooking(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean storeNewEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployee(int employeeId, Employee updatedUser) {
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
