package java1;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Program {

	public static void main(String[] args) {


		boolean wantToExit = false;
		CollectionMaker maker = new CollectionMaker();
		try(InputHandler reader = new InputHandler(new InputStreamReader(System.in))){
			while(!wantToExit) {
				int[] inIntegerArray;
				int[] outArray;
				Optional<Integer> outValue;
				printMainMenu();
				char choice = reader.getChoiceInput();
				switch(choice) {
				
				case '1':
					System.out.println("Enter an integer array and then remove doublets using array");
					inIntegerArray = reader.getIntegerArray();
					outArray = maker.removeDoubletsUsingArray(inIntegerArray);
					System.out.print("Input: ");
					for(Integer integer : inIntegerArray) {
						System.out.print(integer+" ");
					}
					System.out.print("Output: ");
					for(Integer integer : outArray) {
						System.out.print(integer+" ");
					}
					System.out.println("");
					break;
				case '2':
					System.out.println("Enter an integer array and then remove doublets using set");
					inIntegerArray = reader.getIntegerArray();
					Set<Integer> outSet = maker.removeDoubletsUsingSet(inIntegerArray);
					System.out.print("Input: ");
					for(Integer integer : inIntegerArray) {
						System.out.print(integer+" ");
					}
					System.out.print("Output: ");
					System.out.print(outSet.toString());
					System.out.println("");
					break;
				case '3':
					System.out.println("Enter an integer array and then add an integer at the end");
					inIntegerArray = reader.getIntegerArray();
					System.out.print("Integer to add at the end: ");
					outValue = reader.getIntegerValue();
					if(!outValue.isPresent()) {
						break;
					}
					List<Integer> outList = maker.addValueToEndOfArrayUsingList(inIntegerArray, outValue.get());
					System.out.print("Input: ");
					for(Integer integer : inIntegerArray) {
						System.out.print(integer+" ");
					}
					System.out.print("Output: ");
					System.out.print(outList.toString());
					System.out.println("");
					break;
				case '4':
					System.out.println("Enter an integer array and then give an integer to remove");
					inIntegerArray = reader.getIntegerArray();
					System.out.println("Remove fist instance of integer: ");
					outValue = reader.getIntegerValue();
					if(!outValue.isPresent()) {
						break;
					}
					outArray = maker.removeFirstElementWithValue(inIntegerArray, outValue.get());
					System.out.print("Input: ");
					for(Integer integer : inIntegerArray) {
						System.out.print(integer+" ");
					}
					System.out.print("Output: ");
					for(Integer integer : outArray) {
						System.out.print(integer+" ");
					}
					System.out.println("");
					break;
				case '5':
					System.out.println("Enter an integer array and then an string array to create a Map<Integer, String> ");
					System.out.println("Enter integer keys:");
					inIntegerArray = reader.getIntegerArray();
					if(inIntegerArray.length==0) {
						break;
					}
					System.out.println("Enter string values:");
					String[] values = reader.getStringArray();
					if(values.length==0) {
						break;
					}
					Map<Integer, String> map = maker.makeMapFromIntegerAndStringArray(inIntegerArray, values);
					if(map == null) {
						System.out.println("Number of keys and values do not match!");
						break;
					}
					System.out.println("Input: ");
					System.out.print("Keys: ");
					for(Integer integer : inIntegerArray) {
						System.out.print(integer+" ");
					}
					System.out.print("Values: ");
					for(String strings : values) {
						System.out.print(strings+" ");
					}
					System.out.print("Output: ");
					System.out.print(map.toString());
					System.out.println("");
					break;
				case '6':
					wantToExit = true;
					break;
				default:
					break;
				}			
			}
		} catch (IOException e) {
			System.out.println("An IOException was thrown!");
		}
	}
	
	public static void printMainMenu() {
		System.out.println("");
		System.out.println("**--.....-|Main Menu|-.....--**");
		System.out.println("1. Remove doublets using Array");
		System.out.println("2. Remove doublets using Set");
		System.out.println("3. Add value to end using List");
		System.out.println("4. Remove first instance of value using Array");
		System.out.println("5. Make map using integer and string Array");
		System.out.println("6. Exit");
	}
	

}
