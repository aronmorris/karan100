import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class HappyNumbers {

	public static void main(String[] args) {
	
		int happyNums = 0, it = 1;
		
		while (happyNums < 8) {
			
			if (isHappy(it)) {
				happyNums++;
				System.out.println(it);
			}
			
			it++;
			
		}
		
	}
	
	public static int[] makeDigits(int i) {
		String temp = String.valueOf(i);
		int[] res = new int[temp.length()];
		int j = 0;
		
		while(i > 0) {
			int k = i % 10;
			res[j++] = k;
			i /= 10;
		}
		
		return res;
	}
	
	public static int happify(int[] arr) {
		int ret = 0;
		
		for (int i : arr) {
			ret +=  i * i;
		}
		
		return ret;
	}
	
	public static boolean isHappy(int n) {
		HashSet<Integer> s = new HashSet<Integer>();
		
		while (!s.contains(n)) {
			s.add(n);
			n = happify(makeDigits(n));
			if (n == 1) {
				return true;
			}	
		}
		
		return false;
	}
	
}	