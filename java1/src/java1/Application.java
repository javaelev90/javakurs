package java1;

import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

	private boolean setupDone = false;
	
	public boolean setup() {
		//TODO maybe need setup
		setupDone = true;
		return setupDone;
	}
	
	
	public void run() {
		if(!setupDone) return;
		boolean wantToExit = false;
		EmployeeHandler employeeHandler = new ForgetfulEmployeeHandler(new ForgetfulDataStore());
		BookingManager manager = new BookingManager(employeeHandler);
		

		try(InputHandler input = new InputHandler(new InputStreamReader(System.in));
				MenuPrinter menu = new MenuPrinter(System.out)) {
			
			while(!wantToExit) {
				menu.printBookingMenu();
				// Return 0 if empty choice
				char choice = input.getChoiceInput();
				
				switch(choice) {
				
				case '1':
					manager.makeBookingTimeSlot(input, menu);
				
					
					break;
					
				case '2':
					
					
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

