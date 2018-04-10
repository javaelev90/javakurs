package java1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;

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
		ClientManager clientManager = new ClientManager();
		try(InputHandler input = new InputHandler(new InputStreamReader(System.in));
				MenuPrinter menu = new MenuPrinter(System.out)) {
			
			while(!wantToExit) {
				menu.printMainMenu();
				// Return 0 if empty choice
				char choice = input.getChoiceInput();
				
				switch(choice) {
				
				case '1':
					clientManager.makeBooking(input, menu);
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

