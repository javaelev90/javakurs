package java1;

import java.io.PrintStream;
import java.io.PrintWriter;

public class OutputWriter extends PrintWriter {

	public OutputWriter(PrintStream out) {
		super(out);
	}

	public void printDefaultMenu() {
		println("\n--MENU--");
		println("Input a menu selection(1, 2, 3...)");
		println("1: Count words");
		println("2: Count characters");
		println("3: Count both words and characters");
		println("4: Exit");
		println("--------");
		flush();
	}

	public void printCountWordOption() {
		println("--Count Words--");
		println("Write a text, then press enter to count");
		println("---------------");
		flush();
	}

	public void printCountCharactersOption() {
		println("--Count Literal Characters--");
		println("Write a text, then press enter to count");
		println("---------------");
		flush();
	}

	public void printCountWordsAndCharactersOption() {
		println("--Count Words And Literal Characters--");
		println("Write a text, then press enter to count");
		println("---------------");
		flush();
	}

	public void thatIsNotAnOption() {
		println("That is not an option.");
		flush();
	}

	public void printWordCount(int words) {
		format("There are %d word(s)%n", words);
		flush();
	}

	public void printCharCount(int chars) {
		format("There are %d literal charater(s)%n", chars);
		flush();
	}

	public void printCmdInterruptOption(String interruptCmd) {
		println("To go back to menu type only " + interruptCmd + " and press enter");
		flush();
	}
}
