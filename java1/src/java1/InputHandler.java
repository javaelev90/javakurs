package java1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InputHandler extends BufferedReader{

	public InputHandler(Reader in) {
		super(in);
	}
	
	public char getChoiceInput() throws IOException {
		String text = readLine();
		if(text.length() != 0) return text.charAt(0);
		return 0;
	}
	
	public String getTextInput() throws IOException {
		return readLine();
	}
	
	public int[] getIntegerArray() throws IOException {
		
		System.out.println("(Leave blank and press enter to stop)");
		List<Integer> list = new ArrayList<Integer>();
		
		while(true) {
			System.out.print("Integer: ");
			String integer = readLine();
			if(integer.equals("")) {
				break;
			}
			try {
				int newInt = Integer.parseInt(integer);
				list.add(newInt);
			} catch(NumberFormatException exception) {
				System.out.println("You have to enter an integer!");
				continue;
			}	
		}
		return list.stream().mapToInt(i -> i).toArray();
	}
	
	public Optional<Integer> getIntegerValue() throws IOException {
		System.out.println("(Leave blank and press enter to stop)");
		while(true) {
			String integer = readLine();
			if(integer.equals("")) {
				break;
			}
			try {
				return Optional.of(Integer.parseInt(integer));
			} catch(NumberFormatException exception) {
				System.out.println("You have to enter an integer!");
				continue;
			}	
		}
		return Optional.empty();	
	}
	
	public String[] getStringArray() throws IOException{
		List<String> list = new ArrayList<String>();
		
		while(true) {
			System.out.print("String: ");
			String input = readLine();
			if(input.equals("")) {
				break;
			}
			list.add(input);
		}
		return list.toArray(new String[list.size()]);
		
	}
	
	

}
