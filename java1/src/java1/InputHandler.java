package java1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputHandler {

	private BufferedReader input;
	
	public char getChoiceInput() throws IOException {
		char c = input.readLine().charAt(0);
		return c;
	}
	
	public String getTextInput() throws IOException {
		String text = input.readLine();
		return text;
	}
	
	
	public BufferedReader getBufferedReader(InputStream in) {
		input = new BufferedReader(new InputStreamReader(in));
		return input;
	}
}
