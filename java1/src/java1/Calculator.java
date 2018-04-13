package java1;


public class Calculator {
	
	private final int DIVISION_LIMIT = 2;
	private final int MULTIPLICATION_LIMIT = 2;
	
	public double calculate(String input) throws NumberFormatException, ExceededNumberOfDivisionsLimitException, ExceededNumberOfMultiplicationsLimitException, CanNotDivideByZeroException{
		
		if(InputValidator.isAddition(input)) {	
			return addition(input);	
		}
		if(InputValidator.isDivision(input)) {
			return division(input);
		}

		if(InputValidator.isMultiplication(input)) {
			return multiplication(input);
		}
		if(InputValidator.isSubtraction(input)) {
			return subtraction(input);
		}
		return 0;
	}
	private double addition(String input) throws NumberFormatException{
		String[] addition = input.split("\\+");
		int multiplier = 1;
		int startValue = 0;
		if(input.charAt(0) == '-') {
			multiplier = -1;
		}
		if(addition[0].equals("")) {
			startValue = 1;
		}
		double sum = Double.parseDouble(addition[startValue]) * multiplier; 
		for(int i = startValue+1; i < addition.length; i++) {
			
			sum += Double.parseDouble(addition[i]);
			
		}
		return sum;
	}
	
	private double subtraction(String input) throws NumberFormatException{
		String[] subtraction = input.split("\\-");
		int multiplier = 1;
		int startValue = 0;
		if(input.charAt(0) == '-') {
			multiplier = -1;
		}
		if(subtraction[0].equals("")) {
			startValue = 1;
		}
		double sum = Double.parseDouble(subtraction[startValue]) * multiplier; 
		for(int i = startValue+1; i < subtraction.length; i++) {
			sum -= Double.parseDouble(subtraction[i]);
		}
		return sum;
	}
	
	private double division(String input) throws ExceededNumberOfDivisionsLimitException, CanNotDivideByZeroException, NumberFormatException{
		String[] division = input.split("/");
		if(division.length > DIVISION_LIMIT) {
			throw new ExceededNumberOfDivisionsLimitException();	
		}

		double quotient = Double.parseDouble(division[0]);
		if(quotient == 0) throw new CanNotDivideByZeroException();
		for(int i = 1; i < division.length; i++) {
			double number = Double.parseDouble(division[i]);
			if(number == 0) throw new CanNotDivideByZeroException();
			quotient /= Double.parseDouble(division[i]);	
		}
		return quotient;
	}

	private double multiplication(String input) throws ExceededNumberOfMultiplicationsLimitException, NumberFormatException {
		String[] multiplication = input.split("\\*");
		if(multiplication.length > MULTIPLICATION_LIMIT) {
			throw new ExceededNumberOfMultiplicationsLimitException();	
		}
	
		double product = Double.parseDouble(multiplication[0]);
		for(int i = 1; i < multiplication.length; i++) {
			product *= Double.parseDouble(multiplication[i]);
			
		}
		return product;
	}

}
