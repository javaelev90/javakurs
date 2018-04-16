package java1;

public class OutputWriter {

	public static void printMainMenu() {
		System.out.println("----Main Menu----");
		System.out.println("1. Addition	");
		System.out.println("2. Subtraction");
		System.out.println("3. Multiplication");
		System.out.println("4. Division");
		System.out.println("5. Exit");
	}

	public static void inValidOption() {
		System.out.println("Invalid menu option.");
	}

	public static void printExit() {
		System.out.println("The program will now exit.");
	}

	public static void printChoiceException(Exception exception) {
		String formatStringmessage = "\"An: %s was thrown when reading menu choice with the message %s \n";
		printExceptionHelper(exception, formatStringmessage);
	}

	public static void printTextInputException(Exception exception) {
		String formatStringmessage = "An: %s was thrown when reading calculation input with the message %s \n";
		printExceptionHelper(exception, formatStringmessage);
	}

	public static void printInputReaderException(Exception exception) {
		String formatStringmessage = "An: %s was thrown with the message %s \n";
		printExceptionHelper(exception, formatStringmessage);
	}

	private static void printExceptionHelper(Exception exception, String formatString) {
		System.err.format(formatString, exception.toString(), exception.getMessage());
	}

}
