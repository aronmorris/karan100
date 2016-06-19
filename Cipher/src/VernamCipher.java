
public class VernamCipher {

	public static String encrypt(String msg, String key) {
			
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < msg.length(); i++) {
			sb.append(Character.toString((char) (msg.charAt(i) ^ key.charAt(i % key.length()))));
		}
		
		return sb.toString();
		
	}
	
}
