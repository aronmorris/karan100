import java.util.Arrays;


public class MergeAndBubbleSort {

	public static void main(String[] args) {
		int[] toSort = {8, 7, 6, 5, 4, 3, 2, 1};
		System.out.println(Arrays.toString(bubbleSort(toSort)));
		System.out.println(Arrays.toString(mergeSort(toSort)));
	}
	
	
	// <bubbleSort>
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
	// </bubbleSort>
	
	// <mergeSort>
	public static int[] mergeSort(int[] arr) {
		int[] sArr = arr;
		int[] working = new int[sArr.length]; //working array, holds values only
		
		topDownMerge(sArr, 0, sArr.length, working); 
			
		return sArr;
	}

	//merges from the top down recursively
	private static void topDownMerge(int[] A, int begin, int end,
			int[] B) {
		if (end - begin < 2) { //These two are ordered
			return; //base case
		}
		
		int middle = (end + begin) / 2; //split the array in half
		
		topDownMerge(A, begin, middle, B); //merge from start to middle
		topDownMerge(A, middle, end, B); //end to middle
		//merge the two arrays
		//This is reached only when every sub-array has a length of 1
		merge(A, begin, middle, end, B);
		copyArray(B, begin, end, A); //copies the working and final arrays back together, overriding A
		
	}

	//Copies the arrays together, overriding A with the sorted B as many times as there are smaller arrays to combine up to the final A, the sorted version of the input
	private static void copyArray(int[] B, int begin, int end, int[] A) {
		for (int k = begin; k < end; k++) {
			A[k] = B[k];
		}
	}
	
	//Called when every array sublist has been divided down into 1 element each
	private static void merge(int[] A, int begin, int middle, int end, int[] B) {
		int i = begin, j = middle;
		
		for (int k = begin; k < end; k++) {
			if (i < middle && (j >= end || A[i] <= A[j])) { //if A[start] less than A[middle], then we're good
				B[k] = A[i];
				i += 1;
			}
			else {
				B[k] = A[j]; //otherwise the middle value of A is the correct one and gets added to the sorted B
				j += 1;
			}
		}
		
	}
	// </mergeSort>
	
}
