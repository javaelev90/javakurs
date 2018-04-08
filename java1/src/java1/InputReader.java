package java1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class InputReader extends BufferedReader {

	public InputReader(Reader in) {
		super(in);
	}

	public char getChoiceInput() throws IOException {
		char c = this.readLine().charAt(0);
		return c;
	}

	public String getTextInput() throws IOException {

		return this.readLine();
	}

	public boolean gotBreakOption(String text, char breakOption) {
		if (text.length() == 1 && text.charAt(0) == breakOption) {
			return true;
		}
		return false;
	}

}
