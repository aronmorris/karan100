import java.util.Arrays;


public class MergeAndBubbleSort {

	public static void main(String[] args) {
		int[] toSort = {8, 7, 6, 5, 4, 3, 2, 1};
		System.out.println(Arrays.toString(bubbleSort(toSort)));
	}
	
	public static int[] bubbleSort(int[] arr) {
		
		int n = arr.length;
		int temp;
		boolean swapped = true;
		
		while(swapped) {
			
			swapped = false;
			
			for (int i = 1; i < n; i++) {
				
				if (arr[i - i] > arr[i]) {
					
					temp = arr[i - 1];
					arr[i - 1] = arr[i];
					arr[i] = temp;
					System.out.printf("Swapped arr[i - 1] = %d and arr[i] = %d at loop %d%n", arr[i - 1], arr[i], i - 1);
					swapped = true;
					
				}
	
			}
			
			System.out.printf("Run complete.%n%n");
			
		}
		
		return arr;
	}
	
	
}
