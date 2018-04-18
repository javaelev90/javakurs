package java1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

public class InputHandler extends BufferedReader{

	public InputHandler(Reader arg0) {
		super(arg0);
	}
	
	public char getChoiceInput() throws IOException {
		String text = readLine();
		if(!text.isEmpty()) return text.charAt(0);
		return 0;
	}
	
	public String getTextInput() throws IOException {
		return readLine();
	}

	public Optional<Integer> getArtNumberInput() throws IOException {
		while(true) {
			String integer = readLine();
			if(integer.isEmpty()) {
				return Optional.empty();
			}
			try {
				return Optional.of(Integer.parseInt(integer));
			} catch(NumberFormatException exception) {
				System.out.println("The article number is an integer, please input an integer.");
			}
		}
	}
	
}
