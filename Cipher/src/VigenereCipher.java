
public class VigenereCipher {

	private static char[] message;
	
	private static char[] key;
	
	public static String encrypt(String msg, String keyphrase) {
		
		message = msg.toCharArray();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0, j = 0; i < message.length; i++) {
            char c = message[i];
            if (c < 'A' || c > 'Z') continue;
            sb.append((char)((c + keyphrase.charAt(j) - 2 * 'A') % 26 + 'A'));
            j = ++j % keyphrase.length();
        }
		
		return sb.toString();
		
	}
	
}
