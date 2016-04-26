
public class CurrencyConverter implements UnitConverter {

	private CurrencyUnit fromVal, toVal;
	int amount;
	
	public CurrencyConverter(CurrencyUnit A, CurrencyUnit B, int amountOfA) {
		this.fromVal = A;
		this.toVal = B;
		this.amount = amountOfA;
	}
	
	public void reassignValues(CurrencyUnit from, CurrencyUnit to, int amountOfFrom) {
		this.fromVal = from;
		this.toVal = to;
		this.amount = amountOfFrom;
	}
		
	@Override
	public void convertUnit() {
		System.out.printf("Your amount is: %.2f", (fromVal.convert(toVal) * this.amount));
		
	}

}
