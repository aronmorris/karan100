import java.util.Scanner;


public class ReverseString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String tor = sc.nextLine().toString();
		
		System.out.println(new StringBuilder(tor).reverse());
		
		sc.close();
		
	}
	
}
