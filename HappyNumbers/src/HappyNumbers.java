import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class HappyNumbers {

	public static void main(String[] args) throws InterruptedException {
	
		ArrayList<Integer> a = makeDigits(13);
		
		final List<Integer> eLoop = 
				new ArrayList<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));
	
		System.out.println(a);
		
		int i = 0;
		
		do {
			i = happify(a);
			a = makeDigits(i);
			//if (a.toString().equals("[1, 4, 5]")) break;
			System.out.println(a.toString());
			Thread.sleep(1);
		} while (i != 1);
		System.out.println("Unending!");
	}
	
	public static ArrayList<Integer> makeDigits(int i) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int temp = i;
		do {
			arr.add(temp % 10);
			temp /= 10;
		} while(temp > 0);
		Collections.reverse(arr); //arraylist is filled in backwards order
		return arr;
	}
	
	public static int happify(ArrayList<Integer> a) {
		int ret = 0;
		for (int i = 0; i < a.size(); i++) {
			ret += Math.pow(a.get(i), 2);
		}
		
		return ret;
	}
	
	public static void catchLoop(String s) {
		
	}
	
	
}
