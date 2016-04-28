import java.math.BigDecimal;
import java.math.RoundingMode;


public class MassConverter implements Converter {

	private enum MassUnit {
		KG(1.0),
		LB(2.20462),
		SL(0.0685218);
		
		private BigDecimal mass;
	
		public BigDecimal getValue() {
			return this.mass;
		}
		
		MassUnit(double mass) {
			this.mass = new BigDecimal(mass);
		}
	}
	
	private String firstMass, secondMass;
	private MassUnit firstUnit, secondUnit;
	private double amount;
	
	public MassConverter() {}
	
	public void setVariables(String mass1, String mass2, double amt) {
		for (MassUnit val : MassUnit.values()) {
			if (val.name().equals(mass1.toUpperCase())) {
				firstUnit = val;
			}
			if (val.name().equals(mass2.toUpperCase())) {
				secondUnit = val;
			}

		}
		this.amount = amt;
		firstMass = Double.toString(amt);
	}
	
	
	@Override
	public void convert() {
		System.out.printf("Converted %s %s to %s %s%n", 
				this.firstMass, this.firstUnit.toString(), 
					this.secondUnit.getValue()
					.divide(this.firstUnit.getValue(),3, RoundingMode.CEILING)
					.multiply(new BigDecimal(amount))
					.toString(),
					 this.secondUnit.toString());

	}

	@Override
	public void listUnits() {
		for (MassUnit m : MassUnit.values()) {
			System.out.printf("%s%n", m.name());
		}
		
	}

}
