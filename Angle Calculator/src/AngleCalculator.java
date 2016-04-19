import java.util.Scanner;

public class AngleCalculator {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		double pitch, yaw;
		
		while(true) {
			System.out.println("Enter X angle.");
			yaw = (((scanner.nextDouble() + 7.5) + 90) * Math.PI) / 180;
			System.out.println("Enter Y angle.");
			pitch = ((scanner.nextDouble() + 90) * Math.PI) / 180;
			
			double y = Math.sin(pitch) * Math.sin(yaw);
			double x = Math.sin(pitch) * Math.cos(yaw);
			double z = Math.cos(pitch);
			
			
			System.out.println("Conversion to Cartesian: ");
			System.out.println("X: " + x * 2);
			System.out.println("Z: " + y * 2);
			//System.out.println("Y: " + z * 2);
			
			int in;
			
			System.out.println("1: Exit, 2: Continue");
			
			in = scanner.nextInt();
			
			if (in == 1) {
				break;
			}
			
		}
		
	}
	
}
