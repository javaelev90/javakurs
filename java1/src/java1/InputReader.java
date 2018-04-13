package java1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InputReader extends BufferedReader{
	
	public InputReader(InputStreamReader in) {
		super(in);
	}
	
	public char getInputChoice()  {
		String choice;
		try {
			choice = readLine();
			if(choice.length() != 0) {
				return choice.charAt(0);
			}
		} catch (IOException e) {
			OutputWriter.printChoiceException(e);
		}
		return 0;
	}
	
	public String getInputText() throws IOException {
		try {
			return readLine();			
		} catch (IOException e) {
			OutputWriter.printTextInputException(e);
		
		}
		return null;
	}
	
	
}
