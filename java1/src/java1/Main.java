package java1;

import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		boolean wantToExit = false;

		try (InputReader reader = new InputReader(new InputStreamReader(System.in))) {
			while (!wantToExit) {
				OutputWriter.printMainMenu();
				char choice = reader.getInputChoice();
				String text;
				try {

					switch (choice) {

					case '1':
						text = reader.getInputText();
						if (text.equals(""))
							break;

						if (text.length() < 3) {
							throw new TooFewArgumentsException();
						}
						break;
					case '2':
						text = reader.getInputText();
						if (text.equals("")) {
							break;
						}

						if (text.length() < 3) {
							throw new TooFewArgumentsException();

						}

						break;
					case '3':
						text = reader.getInputText();
						if (text.equals("")) {
							break;
						}

						if (text.length() < 3) {
							throw new TooFewArgumentsException();

						}

						break;
					case '4':
						text = reader.getInputText();
						if (text.equals("")) {
							break;
						}

						if (text.length() < 3) {
							throw new TooFewArgumentsException();

						}
						break;
					case '5':
						wantToExit = true;
						break;

					default:
						OutputWriter.inValidOption();
						break;
					}
				} catch (TooFewArgumentsException e) {
					System.out.println("Too few arguments in input, need a second number.");
				} catch (NumberFormatException e) {
					System.out.println("NumberFormatException was thrown when parsing.");
				}
			}
		} catch (IOException exception) {
			OutputWriter.printInputReaderException(exception);
			OutputWriter.printExit();
		}

	}

}
