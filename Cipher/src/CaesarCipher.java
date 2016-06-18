
public class CaesarCipher {

	protected static char[] message;
	
	public static String encrypt(String msg, char shift) {
		
		message = msg.toCharArray();
		
		StringBuilder sb = new StringBuilder();
		
		for (char c : message) {
			if (c + shift > 'z') {
				c -= shift;
			}
			else {
				c += shift;
			}
			sb.append(c);
		}
		
		return sb.toString();
	}
	
}
