import java.util.function.Function;


public class Limit {
	
	private Function<Double, Double> func;
	
	public Limit(Function<Double, Double> function) {
		this.func = function;
	}
	
	public final double limit(double approach, String direction) {
		double resultBelow = approachBelow(this.func, approach);
		double resultAbove = approachAbove(this.func, approach);
		return direction == "above" ? resultAbove : resultBelow;
		
	}
	//Approach the limit from below the entered approach value
	private final double approachBelow(Function<Double, Double> func, double approach) {
		for (double d = approach - 10; d <= approach; d = approach - ((approach - d) / 10)) { //Approach the limit
			if ((double)func.apply(d) == Double.POSITIVE_INFINITY) { //If the limit is positive infinity
				return Double.POSITIVE_INFINITY;
			}
			else if ((double)func.apply(d) == Double.NEGATIVE_INFINITY) { //negative infinity
				return Double.NEGATIVE_INFINITY;
			}
			else if (Double.isNaN((double)func.apply(d))) { //doesn't exist
				return (double)func.apply(approach + ((approach - d) * 10)); //Try at a higher power
			}
			else {
				if (d == approach) {
					return (double)func.apply(d);
				}
				else if (approach - d < 0.000000000001) {
					d = approach;
				}
			}
			
		}
		return  Double.NaN; //no case matches, the limit then isn't a number
	}
	
	private final double approachAbove(Function<Double, Double> func, double approach) {
		for (double d = approach + 10; d >= approach; d = approach - ((approach - d) / 10)) { //Approach the limit
			if ((double)func.apply(d) == Double.POSITIVE_INFINITY) { //If the limit is positive infinity
				return Double.POSITIVE_INFINITY;
			}
			else if ((double)func.apply(d) == Double.NEGATIVE_INFINITY) { //negative infinity
				return Double.NEGATIVE_INFINITY;
			}
			else if (Double.isNaN((double)func.apply(d))) { //doesn't exist
				return (double)func.apply(approach + ((approach - d) * 10)); //Try at a higher power
			}
			else {
				if (d == approach) {
					return (double)func.apply(d);
				}
				else if (approach - d < 0.000000000001) {
					d = approach;
				}
			}
			
		}
		return  Double.NaN; //no case matches, the limit then isn't a number
	}
}
