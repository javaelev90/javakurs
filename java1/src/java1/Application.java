package java1;

import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

	private boolean setupDone = false;
	private DataStore dataStore;
	
	public boolean setup() {
		
		dataStore = new ForgetfulDataStore();
		
		Employee emp1 = new Employee();
		emp1.setFirstName("Lars");
		emp1.setLastName("Larsson");
		emp1.setId(1);
		emp1.setSchedule(new Schedule());;
		
		Employee emp2 = new Employee();
		emp2.setFirstName("Inga");
		emp2.setLastName("Lund");
		emp2.setId(2);
		emp2.setSchedule(new Schedule());;
		
		Employee emp3 = new Employee();
		emp3.setFirstName("Yin");
		emp3.setLastName("Kwan");
		emp3.setId(3);
		emp3.setSchedule(new Schedule());;
		
		dataStore.storeNewEmployee(emp1);
		dataStore.storeNewEmployee(emp2);
		dataStore.storeNewEmployee(emp3);
		
		setupDone = true;
		return setupDone;
	}
	
	
	public void run() {
		if(!setupDone) return;
		boolean wantToExit = false;
		EmployeeHandler employeeHandler = new ForgetfulEmployeeHandler(dataStore);
		BookingManager manager = new BookingManager(employeeHandler);
		

		try(InputHandler input = new InputHandler(new InputStreamReader(System.in));
				MenuPrinter menu = new MenuPrinter(System.out)) {
			
			while(!wantToExit) {
				menu.printMainMenu();
				// Return 0 if empty choice
				char choice = input.getChoiceInput();
				
				switch(choice) {
				
				case '1':
					manager.tryToBookTime(input, menu);					
					break;
					
				case '2':
					manager.showAllBookings(menu);		
					break;
					
				case '3':
					wantToExit = true;
					break;
				
				default:
					menu.printInvalidMenuOption();
					break;
				
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	
	
	
		
}

