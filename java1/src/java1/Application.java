package java1;

import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

	private DataStore dataStore;

	public boolean setup() {

		// Properties are added for ease of access
//		System.setProperty("defaultDayLimitsStart", "08:00");
//		System.setProperty("defaultDayLimitsStop", "18:00");
//		System.setProperty("employeeSchedulePath", "./resources/schedule");
//		System.setProperty("employeeScheduleType", ".json");
//		System.setProperty("employeeFilePath", "./resources/employees.json");
		if(!AppProperties.loadProperties()) {
			return false;
		}
		
		// Creates employees.json if it does not exist
		FileHandler.createFile(FileHandler.getFilePathToEmployeeFile());
	

		dataStore = new JsonDataStore();

		// Default employee which is added for convenience
		Employee emp = new Employee();
		emp.setFirstName("Ika");
		emp.setLastName("Johansson");
		emp.setId(1);
		dataStore.storeNewEmployee(emp);

		return true;
	}

	public void run() {
		if (!setup()) {
			System.out.println("Setup did not finish, application will shut down.");
			return;
		}
		boolean wantToExit = false;
		BookingManager manager = new BookingManager(dataStore);

		try (InputHandler input = new InputHandler(new InputStreamReader(System.in));
				MenuPrinter menu = new MenuPrinter(System.out)) {

			while (!wantToExit) {
				menu.printMainMenu();
				// Return 0 if empty choice
				char choice = input.getChoiceInput();

				switch (choice) {

				case '1':
					manager.tryToBookTime(input, menu);
					break;

				case '2':
					manager.showAllBookings(menu);
					break;
				case '3':
					manageEmployeesMenu(input, menu);
					break;
				case '4':
					wantToExit = true;
					break;
				default:
					menu.printInvalidMenuOption();
					break;

				}
			}
		} catch (IOException exception) {
			System.out.println("An IOException was thrown with message: " + exception.getMessage());
		}
	}

	public void manageEmployeesMenu(InputHandler input, MenuPrinter menu) throws IOException {
		EmployeeManager manager = new EmployeeManager(dataStore);
		boolean wantToExit = false;
		boolean success = false;
		while (!wantToExit) {
			menu.printAdministrateEmployeesMenu();
			// Return 0 if empty choice
			char choice = input.getChoiceInput();

			switch (choice) {

			case '1':
				manager.showEmployees(menu);
				break;
			case '2':
				success = manager.createEmployee(input, menu);
				break;
			case '3':
				success = manager.deleteEmployee(input, menu);
				break;
			case '4':
				wantToExit = true;
				break;
			default:
				menu.printInvalidMenuOption();
				break;

			}
		}
	}

}
