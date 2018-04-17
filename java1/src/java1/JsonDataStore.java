package java1;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonDataStore implements DataStore {

	private Gson gson;

	public JsonDataStore() {
		gson = new Gson();
	}

	@Override
	public boolean saveBooking(int employeeId, Booking booking) {
		
		Schedule schedule;
		Path path = FileHandler.getFilePathForEmployeeSchedule(employeeId);
		Optional<String> jsonSchedule = FileHandler.readFromFile(path);
		if (jsonSchedule.isPresent()) {
			schedule = gson.fromJson(jsonSchedule.get(), Schedule.class);
		} else {
			schedule = new Schedule();
		}
		if (schedule.tryAddClientToSchedule(booking)) {
			FileHandler.writeToFile(gson.toJson(schedule), path);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBookingOnDateTime(int employeeId, Booking booking) {

		Path path = FileHandler.getFilePathForEmployeeSchedule(employeeId);
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
		Path path = FileHandler.getFilePathForEmployeeSchedule(employeeId);
		Optional<String> jsonSchedule = FileHandler.readFromFile(path);
		if (!jsonSchedule.isPresent()) {
			return null;
		}
		Schedule schedule = gson.fromJson(jsonSchedule.get(), Schedule.class);
		return schedule.getBookingsOnDate(date).get();
	}

	@Override
	public Schedule getSchedule(int employeeId) {
		Path path = FileHandler.getFilePathForEmployeeSchedule(employeeId);
		Optional<String> jsonSchedule = FileHandler.readFromFile(path);
		if (!jsonSchedule.isPresent()) {
			return null;
		}
		Schedule schedule = gson.fromJson(jsonSchedule.get(), Schedule.class);
		return schedule;
	}

	@Override
	public boolean storeNewEmployee(Employee employee) {

		Optional<List<Employee>> employees = getAllEmployeesHelper();
		Type listType = new TypeToken<ArrayList<Employee>>(){}.getType();
		if(!employees.isPresent()) {
			return false;
		}
		if(containsEmployeeId(employees.get(), employee)) {
			return false;
		}
		employees.get().add(employee);
		String updatedJsonEmployees = gson.toJson(employees.get(), listType);
		FileHandler.writeToFile(updatedJsonEmployees, FileHandler.getFilePathToEmployeeFile());
		return true;
	}
	
	private boolean containsEmployeeId(List<Employee> employees, Employee employee) {
		for(Employee emp : employees) {
			if(emp.equals(employee)) return true;
		}
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
		Optional<List<Employee>> employees = getAllEmployeesHelper();
		if(!employees.isPresent()) {
			return null;
		}
		return employees.get();
	}
	
	private Optional<List<Employee>> getAllEmployeesHelper(){
		Path path = FileHandler.getFilePathToEmployeeFile();
		List<Employee> employees;
		Type listType = new TypeToken<ArrayList<Employee>>(){}.getType();
		Optional<String> jsonEmployees = FileHandler.readFromFile(path);
		
		if(!jsonEmployees.isPresent()) {
			return Optional.empty();
		}
		if(jsonEmployees.get().equals("") || jsonEmployees.get() == null) {
			employees = new ArrayList<Employee>();
		} else {
			employees = gson.fromJson(jsonEmployees.get(), listType);
		}
		return Optional.of(employees);
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		Path path = FileHandler.getFilePathToEmployeeFile();
		Optional<List<Employee>> employees = getAllEmployeesHelper();
		Type listType = new TypeToken<ArrayList<Employee>>(){}.getType();
		if(!employees.isPresent()) {
			return false;
		}
		for(Employee employee : employees.get()) {
			if(employee.getId() == employeeId) {
				employees.get().remove(employee);
				break;
			}
		}
		String updatedJsonEmployees = gson.toJson(employees.get(), listType);
		FileHandler.writeToFile(updatedJsonEmployees, path);
		return true;
	}
}
