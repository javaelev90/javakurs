package java1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class InputReader extends BufferedReader {

	public InputReader(Reader in) {
		super(in);
	}

	public char getChoiceInput() throws IOException {
		String c = this.readLine();
		if(c.length() != 0) return c.charAt(0);
		return 0;
	}

	public String getTextInput() throws IOException {

		return this.readLine();
	}

	
	public boolean gotBreakOption(String text, String breakOption) {
		if (text.length() == breakOption.length() && text.equals(breakOption)) {
			return true;
		}
		return false;
	}

}
