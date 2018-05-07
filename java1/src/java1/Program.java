package java1;

import java.io.IOException;
import java.io.InputStreamReader;

public class Program {

	public static void main(String[] args) {

		WordCounter words = new WordCounter();
		CharacterCounter chars = new CharacterCounter();
		boolean isFinished = false;
		// To exit out of a chosen menu operation type only this
		String exitOptionCmd = ".";

		try (InputReader reader = new InputReader(new InputStreamReader(System.in));
				OutputWriter writer = new OutputWriter(System.out)) {
			while (!isFinished) {
				writer.printDefaultMenu();
				char choice = reader.getChoiceInput();
				String text;
				switch (choice) {
				case '1':
					writer.printCountWordOption();
					writer.printCmdInterruptOption(exitOptionCmd);
					text = reader.getTextInput();
					if (text.equals("") || reader.gotBreakOption(text, exitOptionCmd)) {
						break;
					}
					writer.printWordCount(words.count(text));
					break;
				case '2':
					writer.printCountCharactersOption();
					writer.printCmdInterruptOption(exitOptionCmd);
					text = reader.getTextInput();
					if (text.equals("") || reader.gotBreakOption(text, exitOptionCmd)) {
						break;
					}
					writer.printCharCount(chars.count(text));
					break;
				case '3':
					writer.printCountWordsAndCharactersOption();
					writer.printCmdInterruptOption(exitOptionCmd);
					text = reader.getTextInput();
					if (text.equals("") || reader.gotBreakOption(text, exitOptionCmd)) {
						break;
					}
					writer.printWordCount(words.count(text));
					writer.printCharCount(chars.count(text));
					break;
				case '4':
					isFinished = true;
					break;
				default:
					writer.thatIsNotAnOption();
					break;
				}

			}

		} catch (IOException e) {
			System.out.println(e.getClass() + " was thrown with message: " + e.getMessage());
		}
	}

}
