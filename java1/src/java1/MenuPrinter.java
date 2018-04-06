package java1;

public class MenuPrinter {	
	
	public void printDefaultMenu() {
		print("--MENU--");
		print("Input a menu selection(1, 2, 3...)");
		print("1: Count words");
		print("2: Count characters");
		print("3: Count both words and characters");
		print("4: Exit");
		print("--------");
	}
	
	public void printCountWordOption() {
		print("--Count Words--");
		print("Write a text, then press enter to count words");
		print("---------------");
	}
	
	public void printCountCharactersOption() {
		print("--Count Words--");
		print("Write a text, then press enter to count words");
		print("---------------");
	}
	
	public void printCountWordsAndCharactersOption() {
		print("--Count Words--");
		print("Write a text, then press enter to count words");
		print("---------------");
	}
	
	public void thatIsNotAnOption() {
		print("That is not an option.");
	}
	
	public void print(String text) {
		System.out.println(text);
	}
	
}
