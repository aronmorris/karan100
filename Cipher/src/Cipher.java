
public class Cipher {

	public static void main(String[] args) {
		System.out.println(CaesarCipher.encrypt("ABCDE", (char) 1));
		System.out.println(VigenereCipher.encrypt("ABCDE", "Hello"));
		System.out.println(VernamCipher.encrypt("ABCDE", "Hello"));
	}
	
}
