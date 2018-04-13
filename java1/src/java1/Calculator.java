package java1;


public class Calculator {
	
	private final int DIVISION_LIMIT = 2;
	private final int MULTIPLICATION_LIMIT = 2;
	
	public double calculate(String input) throws ExceededNumberOfDivisionsLimitException, ExceededNumberOfMultiplicationsLimitException, CanNotDivideByZeroException{
		
		if(InputValidator.isAddition(input)) {	
			return addition(input);	
		}
		
		if(InputValidator.isSubtraction(input)) {
			return subtraction(input);
		}

		if(InputValidator.isDivision(input)) {
			return division(input);
		}

		if(InputValidator.isMultiplication(input)) {
			return multiplication(input);
		}
		
		return 0;
	}
	private double addition(String input) throws NumberFormatException{
		String[] addition = input.split("\\+");
		double sum = 0;
		for(String number : addition) {
			
			sum += Double.parseDouble(number);
			
		}
		return sum;
	}
	
	private double subtraction(String input) throws NumberFormatException{
		String[] subtraction = input.split("\\-");
		double sum = 0;
		sum = Double.parseDouble(subtraction[0]);
		for(int i = 1; i < subtraction.length; i++) {
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
		double product = 1;
		for(String number : multiplication) {
			try {
				product *= Double.parseDouble(number);
			} catch(NumberFormatException exception) {
				System.out.println("NumberFormatException vid parsning.");
			}
			
		}
		return product;
	}
//	private double addition(String input) {
//		String[] addition = input.split("\\+");
//		double sum = 0;
//		for(String number : addition) {
//			try {
//				sum += Double.parseDouble(number);
//			} catch(NumberFormatException exception) {
//				System.out.println("NumberFormatException vid parsning.");
//			}
//		}
//		return sum;
//	}
//	
//	private double subtraction(String input){
//		String[] subtraction = input.split("\\-");
//		double sum = 0;
//		try {
//			sum = Double.parseDouble(subtraction[0]);
//		
//			for(int i = 1; i < subtraction.length; i++) {
//				
//					sum -= Double.parseDouble(subtraction[i]);
//				
//			}
//		} catch(NumberFormatException exception) {
//			System.out.println("NumberFormatException vid parsning.");
//		}
//		return sum;
//	}
//	
//	private Optional<Double> division(String input) throws ExceededNumberOfDivisionsLimitException, CanNotDivideByZeroException{
//		String[] division = input.split("/");
//		if(division.length > DIVISION_LIMIT) {
//			throw new ExceededNumberOfDivisionsLimitException();	
//		}
//		
//		try {
//			double quotient = Double.parseDouble(division[0]);
//			if(quotient == 0) throw new CanNotDivideByZeroException();
//			for(int i = 1; i < division.length; i++) {
//				
//				double number = Double.parseDouble(division[i]);
//				if(number == 0) throw new CanNotDivideByZeroException();
//				quotient /= Double.parseDouble(division[i]);
//				
//			}
//			return Optional.of(quotient);
//		} catch(NumberFormatException exception) {
//			System.out.println("NumberFormatException vid parsning.");
//		}
//		return Optional.empty();
//	}
//
//	private double multiplication(String input) throws ExceededNumberOfMultiplicationsLimitException {
//		String[] multiplication = input.split("\\*");
//		if(multiplication.length > MULTIPLICATION_LIMIT) {
//			throw new ExceededNumberOfMultiplicationsLimitException();	
//		}
//		double product = 1;
//		for(String number : multiplication) {
//			try {
//				product *= Double.parseDouble(number);
//			} catch(NumberFormatException exception) {
//				System.out.println("NumberFormatException vid parsning.");
//			}
//			
//		}
//		return product;
//	}
}
