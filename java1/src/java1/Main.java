package java1;

import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		boolean wantToExit = false;

		try (InputReader reader = new InputReader(new InputStreamReader(System.in))){
			while(!wantToExit) {
				OutputWriter.printMainMenu();
				char choice = reader.getInputChoice();
				try {
					

					switch(choice) {
					
					case '1':
						String text = reader.getInputText();
						if(text.equals("")) {
							break;
						}
						if(!InputValidator.isValidInput(text)) {
							System.out.println("Invalid input: "+text);
							break;
						}
						if(text.length() < 3) {
							throw new TooFewArgumentsException();
							
						}
					
	//					Pattern pattern = Pattern.compile("\\+");
	//					Matcher matcher = pattern.matcher(text);
	//					System.out.println(text.matches("\\+"));
	//					System.out.println(matcher.find());
						Calculator c = new Calculator();
						System.out.println(c.calculate(text));
						System.out.println(InputValidator.isValidInput(text));
						break;
						
					case '2':
						wantToExit = true;
						break;
						
					default:
						OutputWriter.inValidOption();
						break;
					}
				} catch (TooFewArgumentsException e) {
					System.out.println("Too few arguments in input, need a second number.");
				} catch (MixingDifferentCalculationOperatorsException e) {
					
					System.out.println("You can't mix different operators.");
				} catch (HasIllegalInput e) {
					
					System.out.println("You inputted illegal characters, legal chars: 0123456789+-/*.");
				} catch (UsingOperatorAfterAnotherOperator e) {
				
					System.out.println("You can't use operators without a number in between, unless its a minus.");
				} catch (ExceededNumberOfDivisionsLimitException e) {
					System.out.println("Can only divide two numbers. E.g. 5/2");
				} catch (ExceededNumberOfMultiplicationsLimitException e) {
					System.out.println("Can only multiply two numbers. E.g. 5*2");
				} catch (CanNotDivideByZeroException e) {
					System.out.println("Can not divide by zero.");
				} catch (NumberFormatException e) {
					System.out.println("NumberFormatException was thrown when parsing.");
				} catch (EndsWithAnOperatorException e) {
					System.out.println("Can't end input string with an operator + - / *");
				}
				
				
			
			}
		} catch (IOException exception) {
			OutputWriter.printInputReaderException(exception);
			OutputWriter.printExit();
		}
		
	}

}
