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
			
			System.out.println("Enter cost of your tile as a number, please.");
			double price = sc.nextDouble();
			
			//Init 1 variable instead of multiple for each case
			double[] params = new double[3];
			switch(n) {
			case 1: System.out.printf("Enter %s separated by enter.%n", "width and height");
					params[0] = sc.nextDouble(); params[1] = sc.nextDouble();
					output(square(params[0], params[1]) * price);
					break;
					
			case 2: System.out.printf("Enter %s separated by enter.%n", "width and height");
					params[0] = sc.nextDouble(); params[1] = sc.nextDouble();
					output(triangle(params[0], params[1]) * price);
					break;
					
			case 3: System.out.printf("Enter %s separated by enter.%n", "radius");
					params[0] = sc.nextDouble();
					output(circle(params[0]) * price);
					break;
					
			case 4: System.out.printf("Enter %s separated by enter.%n", 
					"semimajor axis and semiminor axis");
					params[0] = sc.nextDouble(); params[1] = sc.nextDouble();
					output(oval(params[0], params[1]) * price);
					break;
					
			case 5: System.out.printf("Enter %s separated by enter.%n", "width and height");
					params[0] = sc.nextDouble(); params[1] = sc.nextDouble();
					output(parallelogram(params[0], params[1]) * price);
					break;
					
			case 6: System.out.printf("Enter %s separated by enter.%n", 
					"upper width, lower width, and height");
					params[0] = sc.nextDouble(); params[1] = sc.nextDouble(); params[2] = sc.nextDouble();
					output(trapezoid(params[0], params[1], params[2]) * price);
					break;
					
			case 7: System.out.printf("Enter %s separated by enter.%n", "radius and angle in degrees");
					params[0] = sc.nextDouble(); params[1] = sc.nextDouble();
					output(sector(params[0], params[1]) * price);
					break;
			default: cont = false;
					break;
			}
			
			System.out.printf("Calculate another shape? Y/N%n");
			String next = sc.next();
			
			if (next.toLowerCase().equals("y") || next.toLowerCase().equals("yes")) {
				cont = true;
			}
			else { 
				cont = false; 
			}
			
		}
		
		sc.close();
	}
	
	public static void descOptions() {
		System.out.printf("Your options:%n"
						+ "1: Square%n"
						+ "2: Triangle%n"
						+ "3: Circle%n"
						+ "4: Oval%n"
						+ "5: Parallelogram%n"
						+ "6: Trapezoid%n"
						+ "7: Arc sector%n"
						+ "Any other value to exit this menu.%n");
	}
	
	//Consumes a double in the form of the output of a returned area
	//Prints the cost
	public static void output(double func) {
		System.out.printf("Your cost: $%.2f%n%n", func);
	}
	
	//All area calculations below for their shape
	
	public static double square(double l, double w) {
		return l * w;
	}
	
	public static double triangle(double h, double w) {
		return (h * w) / 2;
	}
	
	public static double circle(double r) {
		return Math.PI * r * r;
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
		return ((angle / 360) * Math.PI * (r * r));
	}
}
