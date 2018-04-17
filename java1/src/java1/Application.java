package java1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Properties;

public class Application {

	private boolean setupDone = false;
	private DataStore dataStore;

	public boolean setup() {
		
		
		System.setProperty("defaultDayLimitsStart", "08:00");
		System.setProperty("defaultDayLimitsStop", "18:00");
		System.setProperty("employeeSchedulePath", "./resources/schedule");
		System.setProperty("employeeScheduleType", ".json");
		System.setProperty("employeeFilePath", "./resources/employees.json");
		dataStore = new JsonDataStore();
		try {
			WorkDayLimits limits = new WorkDayLimits(DateTimeValidator.getValidTime("08:00"),
					DateTimeValidator.getValidTime("18:00"));
			Employee emp1 = new Employee();
			emp1.setFirstName("Ika");
			emp1.setLastName("Johansson");
			emp1.setId(1);
			//emp1.setSchedule(new Schedule());
			dataStore.storeNewEmployee(emp1);
		} catch(DateTimeParseException exception) {
			System.out.println("An DateTimeParseException was thrown, could not parse to localtime.");
			return setupDone;
		}

		// **** For more employees, uncomment these ****

		// Employee emp2 = new Employee();
		// emp2.setFirstName("Inga");
		// emp2.setLastName("Lund");
		// emp2.setId(2);
		// emp2.setSchedule(new Schedule(limits));
		// dataStore.storeNewEmployee(emp2);

		// Employee emp3 = new Employee();
		// emp3.setFirstName("Yen");
		// emp3.setLastName("Chan");
		// emp3.setId(3);
		// emp3.setSchedule(new Schedule(limits));
		// dataStore.storeNewEmployee(emp3);
		
		setupDone = true;
		return setupDone;
	}

	public void run() {
		if (!setupDone)
			return;
		boolean wantToExit = false;
		EmployeeHandler employeeHandler = new JsonEmployeeHandler(dataStore);
		BookingManager manager = new BookingManager(employeeHandler);
		
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
			System.out.println("An IOException was thrown with message: "+exception.getMessage());
		}
	}

}
