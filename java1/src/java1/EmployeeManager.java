package java1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeManager {

	private DataStore dataStore;

	public EmployeeManager(DataStore dataStore) {
		this.dataStore = dataStore;
	}

	public boolean deleteEmployee(InputHandler input, MenuPrinter menu) throws IOException, NumberFormatException {
		menu.printDeleteEmployeeOption();
		String text = input.getTextInput();
		if (text.equals("")) {
			return false;
		}
		try {
			int id = Integer.parseInt(text);
			if (dataStore.deleteEmployee(id)) {
				menu.printOperationSucceeded();
				return true;
			} else {
				menu.printOperationNotSuccessful();
				return false;
			}
		} catch (NumberFormatException exception) {
			menu.printInputHasToBeAnInteger();
			return false;
		}
	}

	public boolean createEmployee(InputHandler input, MenuPrinter menu) throws IOException {

		Employee newEmployee = new Employee();

		menu.printCreateEmployeeFirstName();
		String text = input.getTextInput();
		if (text.equals("")) {
			return false;
		}
		newEmployee.setFirstName(text);
		menu.printCreateEmployeeLastName();
		text = input.getTextInput();
		if (text.equals("")) {
			return false;
		}
		newEmployee.setLastName(text);
		newEmployee.setId(getNewEmployeeId());
		if (dataStore.storeNewEmployee(newEmployee)) {
			menu.printOperationSucceeded();
			return true;
		} else {
			menu.printOperationNotSuccessful();
			return false;
		}
	}

	public boolean updateEmployee(InputHandler input, MenuPrinter menu) {
		return false;
	}

	public void showEmployees(MenuPrinter menu) {
		List<Employee> employees = dataStore.getAllEmployees();
		menu.printAllEmployees(employees);
	}

	private int getNewEmployeeId() {
		List<Employee> employees = dataStore.getAllEmployees();
		if(employees.isEmpty()) {
			return 0;
		}
		
		Set<Integer> ids = new HashSet<Integer>();
		for (Employee employee : employees) {
			ids.add(employee.getId());
		}
		int newId = 0;
		int counter = 0;
		for (Integer i : ids) {
			if (counter != i) {
				break;
			}
			counter++;
		}
		newId = counter;
		return newId;
	}
}
