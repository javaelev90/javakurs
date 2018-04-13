package java1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

	public static boolean isValidInput(String input) throws MixingDifferentCalculationOperatorsException, 
		HasIllegalInput, UsingOperatorAfterAnotherOperator, EndsWithOperatorException, StartsWithOperatorException {
		input = input.replaceAll(" ", "");
		if(hasIllegalInput(input)){
			throw new HasIllegalInput();
		}
		if(mixingDifferentCalucaltionOperators(input)) {
			throw new MixingDifferentCalculationOperatorsException();
		}
		if(usingOperatorAfterAnotherOperator(input)) {
			throw new UsingOperatorAfterAnotherOperator();
		}
		if(endsWithOperator(input)) {
			throw new EndsWithOperatorException();
		}
		if(startsWithOperator(input)) {
			throw new StartsWithOperatorException();
		}
		return true;
	}
	
	
	public static boolean isAddition(String input) {
		return usingOperator(input, "\\+");
	}
	
	public static boolean isSubtraction(String input) {
		return usingOperator(input, "\\-");
	}
	
	public static boolean isDivision(String input) {
		return usingOperator(input, "/");
	}
	
	public static boolean isMultiplication(String input) {
		return usingOperator(input, "\\*");
	}
	
	private static boolean usingOperatorAfterAnotherOperator(String input) {
		int calculationMethods = 0;
		if(usingOperator(input,"[\\+][\\+]")) { //operator: +
			calculationMethods++;
		}
		if(usingOperator(input,"[\\-][\\-][\\-]")) { //operator: -
			calculationMethods++;
		}
		if(usingOperator(input,"[\\*][\\*]")) { //operator: *
			calculationMethods++;
		}
		if(usingOperator(input,"[/][/]")) { //operator: /
			calculationMethods++;
		}
		if(usingOperator(input,"[.][.]")) { //operator: /
			calculationMethods++;
		}
		return (calculationMethods > 0);
	}
	
	private static boolean endsWithOperator(String input) {
		char c = input.charAt(input.length()-1);
		if(c == '+' || c == '/' || c == '*' || c == '-') return true;
		return false;
	}
	
	private static boolean startsWithOperator(String input) {
		char c = input.charAt(0);
		if(c == '+' || c == '/' || c == '*') return true;
		return false;
	}
	
	private static boolean mixingDifferentCalucaltionOperators(String input) {
		int calculationMethods = 0;
		if(isAddition(input)) { 
			calculationMethods++;
		}
		if(isMultiplication(input)) {
			calculationMethods++;
		}
		if(isDivision(input)) { 
			calculationMethods++;
		}
		return (calculationMethods > 1);
	}
	
	private static boolean hasIllegalInput(String input) {
		return !input.matches("[0-9/+-\\\\*\\\\.]+");
	}
	
	private static boolean usingOperator(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}
	
}
