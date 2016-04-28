
public class ConverterFactory {

	public Converter getConverter(String converterType) {
		if (converterType == null) {
			return null;
		}
		switch(converterType.toLowerCase()) {
		case "currency": return new CurrencyConverter();
			
		case "temperature": return new TemperatureConverter();
		
		case "mass": return new MassConverter();
			
		default: return null;
		
		}
	}
	
}
