
public class PalindromeDetector {

	public static void main(String[] args) {
		System.out.println(isPalindrome("Hello"));
		System.out.println(isPalindrome("HellolleH"));
	}
	
	public static boolean isPalindrome(String str) {
		String[] split = str.split("");
		
		//i iterates upwards, j iterates downwards
		for (int i = 0, j = split.length - 1; i < split.length && j > 0; i++, j--) { 
			if (!split[i].equals(split[j])) {
				return false;
			}
		}
		return true;
		
	}
	
}
