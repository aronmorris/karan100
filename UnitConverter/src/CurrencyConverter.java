import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CurrencyConverter implements Converter {
	
	private enum Currencies {
		USD(new BigDecimal(1.00)),
		EUR(new BigDecimal(0.88)),
		JPY(new BigDecimal(111.18)),
		GBP(new BigDecimal(0.69)),
		CHF(new BigDecimal(0.97)),
		CAD(new BigDecimal(1.26)),
		AUD(new BigDecimal(1.31)),
		ZAR(new BigDecimal(14.41)),
		BTC(new BigDecimal(0.0021));
		
		private final BigDecimal value;
		
		private Currencies(BigDecimal valueInUSD) {
			this.value = valueInUSD;
		}
		
		public BigDecimal getValue() {
			return this.value;
		}
		
	}
	
	private BigDecimal converterOutput;
	String firstCurrency, secondCurrency;
	
	public CurrencyConverter() {}
	
	/**
	 * 
	 * @param fCurr first currency unit
	 * @param sCurr second currency unit
	 * @param amt amount of money in first unit
	 * @param fStr first currency name
	 * @param sStr second currency name
	 */

	public void setVariables(Currencies fCurr, Currencies sCurr, double amt, String fStr, String sStr) {
		this.converterOutput = convert(fCurr, sCurr, amt);
		this.firstCurrency = fStr;
		this.secondCurrency = sStr;
	}
	
	public Currencies parseStrToCurr(String input) {
		for (Currencies c : Currencies.values()) {
			if (c.name().equals(input.toUpperCase())) {
				return c;
			}
		}
		return null;
	}
	
	public static BigDecimal convert(Currencies first, Currencies second, double amount) {
		return second.getValue().divide(first.getValue(), 4, RoundingMode.CEILING).multiply(new BigDecimal(amount));
	}
	
	@Override
	public void convert() {
		System.out.printf("Converting %s to %s:%n" +
						"Result: %s %s%n", this.firstCurrency, this.secondCurrency,
						this.converterOutput.toString(), this.secondCurrency);
	}

	@Override
	public void listUnits() {
		for (Currencies c : Currencies.values()) {
			System.out.printf("%s%n", c.name());
		}
		
	}
	
}
