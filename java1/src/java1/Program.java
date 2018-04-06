package java1;

import java.io.BufferedReader;
import java.io.IOException;

public class Program {
	
	public static void main(String[] args){
		
		
		InputHandler iHandler = new InputHandler();
		MenuPrinter printer = new MenuPrinter();
		WordCounter words = new WordCounter();
		CharacterCounter chars = new CharacterCounter();
		boolean isFinished = false;
		
		
		try (BufferedReader reader = iHandler.getBufferedReader(System.in)){
			while(!isFinished) {	
				printer.printDefaultMenu();
				char choice = iHandler.getChoiceInput();
				switch(choice) {
				case '1':
					printer.printCountWordOption();
					printer.print(""+words.count(iHandler.getTextInput()));
					break;
				case '2':
					printer.printCountCharactersOption();
					printer.print(""+chars.count(iHandler.getTextInput()));
					break;
				case '3':
					printer.printCountWordsAndCharactersOption();
					printer.print("There are: "+chars.count(iHandler.getTextInput())+ " characters.");
					printer.print("There are: "+words.count(iHandler.getTextInput())+ " words.");
					break;
				case '4':
					isFinished = true;
					break;
				default:
					printer.thatIsNotAnOption();
					break;
				
				
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}

}
