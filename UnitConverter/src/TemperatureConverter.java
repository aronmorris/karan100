
public class TemperatureConverter implements Converter {

	private String unit1, unit2;
	private int degrees;
	public TemperatureConverter() {
	}
	
	public void setVariables(String unit1, String unit2, int degreesUnit1) {
		this.unit1 = unit1;
		this.unit2 = unit2;
		this.degrees = degreesUnit1;
	}
	
	@Override
	public void convert() {

		if (this.unit1.equalsIgnoreCase("celcius")) {
			if (this.unit2.equalsIgnoreCase("kelvin")) {
				System.out.printf("Celcius to Kelvin: %.2f%n", CelciusToKelvin(this.degrees));
			}
			if (this.unit1.equalsIgnoreCase("fahrenheit")) {
				System.out.printf("Celcius to Fahrenheit: %.2f%n", CelciusToFahrenheit(this.degrees));
			}
		}
		
		if (this.unit1.equalsIgnoreCase("fahrenheit")) {
			if (this.unit2.equalsIgnoreCase("celcius")) {
				System.out.printf("Fahrenheit to Celcius: %.2f%n", FahrenheitToCelcius(this.degrees));
			}
			if (this.unit1.equalsIgnoreCase("kelvin")) {
				System.out.printf("Fahrenheit to Kelvin: %.2f%n", 
						CelciusToKelvin(FahrenheitToCelcius(this.degrees)));
			}
		}
		
		if (this.unit1.equalsIgnoreCase("kelvin")) {
			if (this.unit2.equalsIgnoreCase("celcius")) {
				System.out.printf("KelvinToCelcius: %.2f%n", KelvinToCelcius(this.degrees));
			}
			if (this.unit1.equalsIgnoreCase("fahrenheit")) {
				System.out.printf("Kelvin to Fahrenheit: %.2f%n", 
						CelciusToFahrenheit(KelvinToCelcius(this.degrees)));
			}
		}
		
	}
	
	//left out fahrenheit to kelvin as transitively solved
	
	private static double CelciusToKelvin(double degrees) {
		return degrees + 273.15;
	}
	private static double KelvinToCelcius(double degrees)	{
		return degrees - 273.15;
	}
	private static double CelciusToFahrenheit(double degrees) {
		return (degrees * (9 / 5) + 32);
	}
	private static double FahrenheitToCelcius(double degrees) {
		return ((degrees - 32) * (5/9));
	}
}
