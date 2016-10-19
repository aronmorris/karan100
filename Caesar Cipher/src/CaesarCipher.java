
//from the security portion of Karan's list
public class CaesarCipher {

	public CaesarCipher() {
	
	}

	//converts the string to a char array, then rotates each char by *key* spaces, adding them to the final "encrypted" result
	//before returning the resultant string
	
	public String encrypt(String msg, int shift){
	    String s = "";
	    int len = msg.length();
	    for(int x = 0; x < len; x++){
	        char c = (char)(msg.charAt(x) + shift);
	        if (c > 'z')
	            s += (char)(msg.charAt(x) - (26-shift));
	        else
	            s += (char)(msg.charAt(x) + shift);
	    }
	    return s;
	}
	
	//simply inverts the key, runs the encryption again, reversing prior rotation, then re-inverts the key to its original value
	//doesn't work if the string's been encrypted multiple times
	public String unencrypt() {
		return null;
		
	}
	
	public static void main(String[] args) {
		
		CaesarCipher cipher = new CaesarCipher();
		
		System.out.println(cipher.encrypt("ABC abc", 1));
		
	}
	
}
