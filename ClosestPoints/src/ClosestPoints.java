import java.util.Arrays;


public class ClosestPoints {

	public static void main(String[] args) {
		Tuple[] listOfPoints = {new Tuple(1,3),new Tuple(3,4),new Tuple(2,2),new Tuple(5,3)};
		Tuple[] ans = closestPoints(listOfPoints);
		System.out.printf(Arrays.toString(ans));
	}
	
	//returns the closest points from N points by divide-and-conquer
	//Could brute-force but divide and conquer is harder and therefore ~better~
	public static Tuple[] closestPoints(Tuple[] points) {
		
		Tuple[] arr = points;
		Tuple[] working = new Tuple[arr.length];
		int start = 0, mid = arr.length / 2, end = arr.length;
		
		arr = sortX(arr); //sorts into ascending order
		
		closest(arr, start, mid, working);
		closest(arr, mid, end, working);
		
		compare(arr);
		
		return arr;
	}
	
	private static void compare(Tuple[] arr) {
		// TODO Auto-generated method stub
		
	}

	private static void closest(Tuple[] arr, int start, int mid, Tuple[] working) {
		// TODO Auto-generated method stub
		
	}

	//sorts in ascending order of X
	//yeah it's O(n^2), it's a crude thing for an short project
	public static Tuple[] sortX(Tuple[] array) {
		
		Tuple temp;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i].X() < array[j].X()) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
	

	
}
