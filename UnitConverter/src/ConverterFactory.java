
public class ConverterFactory {

	public Converter getConverter(String converterType) {
		if (converterType == null) {
			return null;
		}
		switch(converterType) {
		case "currency": return new CurrencyConverter();
			
		case "temperature": return new TemperatureConverter();
			
		case "volume": return new VolumeConverter();
		
		case "mass": return new MassConverter();
			
		default: return null;
		
		}
	}
	
}
