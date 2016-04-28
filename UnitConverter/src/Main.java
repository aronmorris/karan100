import java.util.Scanner;


public class Main {
	
	final static String[] units = {"Currency", "Temperature", "Volume", "Mass", "Exit"};
	
	public static void main(String[] args) {
		ConverterFactory convFact = new ConverterFactory();
		Converter conv = null;
		Scanner sc = new Scanner(System.in);
		String option;
		String opt1 = null, opt2 = null;
		double amt;
		
		do {
			do {
				System.out.printf("What kind of unit do you want to convert?%n");
				listUnits();
				option = sc.next();
				for (String s : units) {
					if (s.equalsIgnoreCase(option)) {
						conv = convFact.getConverter(s);
					}
				}
				//break option loop
				if (option.equalsIgnoreCase("exit")) {
					break;
				}
				if (conv == null) {
					System.out.printf("Not a valid option!%n");
				}
			} while (conv == null);
			//and exit the program
			if (option.equalsIgnoreCase("Exit")) {
				break;
			}
			
			System.out.printf("Please enter the two units you'll be using from the following options.%n");
			System.out.printf("Also, please enter the amount of the first unit.%n");
			conv.listUnits();
			
			do {
				opt1 = sc.next();
				opt2 = sc.next();
				amt = Double.parseDouble(sc.next());
				System.out.println(amt);
			} while(opt1 == null || opt2 == null);
			
			
			
			//kind of hacky
			if (conv instanceof CurrencyConverter) {
				((CurrencyConverter)conv)
				.setVariables(((CurrencyConverter) conv).parseStrToCurr(opt1),
						((CurrencyConverter) conv).parseStrToCurr(opt2), amt, opt1, opt2);
			} else if (conv instanceof TemperatureConverter) {
				((TemperatureConverter) conv).setVariables(opt1, opt2, amt);
			} else if (conv instanceof MassConverter) {
				((MassConverter) conv).setVariables(opt1, opt2, amt);
			}
			
			conv.convert();
			
		} while (true);
		
		sc.close();
		
	}
	
	public static void listUnits() {
		for (String s : units) {
			System.out.printf("%s%n", s);
		}
	}
	
}
