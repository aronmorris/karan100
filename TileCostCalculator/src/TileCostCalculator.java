import java.util.Scanner;


public class TileCostCalculator {
	/**
	 * Calculates the cost of tiling an area of floor, given a shape S,
	 * dimensions L, W, r (when applicable)
	 */
	
	//Project desc. only calls for a square to be calculated - that's boring
	//Here's some fun shapes
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Choose shape to calculate its cost per area.");
		
		boolean cont = true;
		
		while(cont) {
			descOptions();
			int n = sc.nextInt();
			
			System.out.println("Enter cost of your tile, please.");
			int price = sc.nextInt();
			
			
			switch(n) {
			case 1: System.out.println("Enter length and width separated by enter.");
					int l = sc.nextInt(), w = sc.nextInt();
					System.out.printf("Your cost: $%.2f", (square(l, w) * price));
			default: cont = false;
			}
		}
		
		sc.close();
	}
	
	public static void descOptions() {
		System.out.printf("1: Square%n"
						+ "2: Triangle%n"
						+ "3: Circle%n"
						+ "4: Oval%n"
						+ "5: Parallelogram%n"
						+ "6: Trapezoid%n"
						+ "7: Arc sector%n"
						+ "Any other value to exit.%n");
	}
	
	//All area calculations below for their shape
	
	public static double square(double l, double w) {
		return l * w;
	}
	
	public static double triangle(double l, double w) {
		return (l * w) / 2;
	}
	
	public static double circle(double r) {
		return 2 * Math.PI * r;
	}
	
	public static double oval(double semimajor, double semiminor) {
		return Math.PI * semimajor * semiminor;
	}
	
	public static double parallelogram(double h, double b) {
		return h * b;
	}
	
	public static double trapezoid(double upper_w, double lower_w, double h) {
		return (0.5 * (upper_w + lower_w) * h);
	}
	
	public static double sector(double r, double angle) {
		return (0.5 * Math.pow(r, 2) * angle);
	}
}
