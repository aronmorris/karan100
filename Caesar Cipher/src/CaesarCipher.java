
//from the security portion of Karan's list
public class CaesarCipher {

	public CaesarCipher() {
	
	}

	//converts the string to a char array, then rotates each char by *key* spaces, adding them to the final "encrypted" result
	//before returning the resultant string
	
	public String encrypt(String msg, int key){
	    String s = "";
	    int len = msg.length();
	    for(int x = 0; x < len; x++){
	        char c = (char)(msg.charAt(x) + key);
	        if (c > 'z')
	            s += (char)(msg.charAt(x) - (26-key));
	        else
	            s += (char)(msg.charAt(x) + key);
	    }
	    return s;
	}
	
	//simply inverts the key, runs the encryption again, reversing prior rotation, then re-inverts the key to its original value
	//doesn't work if the string's been encrypted multiple times
	public String decrypt(String encrypted, int key) {
		return encrypt(encrypted, -key);
		
	}
	
	public static void main(String[] args) {
		
		CaesarCipher cipher = new CaesarCipher();
		
		System.out.println(cipher.encrypt("ABC abc", 13));
		
		System.out.println(cipher.decrypt(cipher.encrypt("ABC abc", 13), 13));
		
	}
	
}
