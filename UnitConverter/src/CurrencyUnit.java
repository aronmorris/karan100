
public enum CurrencyUnit {
	USD(1.00),
	CAD(1.27),
	GBP(0.69),
	EUR(0.89),
	JPY(111.17),
	AUD(1.30);
	
	private double valueInUSD;
	
	CurrencyUnit(double valueInUSD) {
		this.valueInUSD = valueInUSD;
	}

	/**
	 * Converts from currency A to B
	 * @param A The unit being converted from
	 * @param B The unit being converted to
	 * @return
	 */
	public double convert(CurrencyUnit B) {
		double output;
		output = Math.round((B.valueInUSD / this.valueInUSD) * 100) / 100;
		return output;
	}
}
