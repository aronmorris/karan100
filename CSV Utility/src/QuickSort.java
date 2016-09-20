import java.util.ArrayList;

//sorts the csv's chosen column
public class QuickSort {

	public static void sort(ArrayList<String> columns) {
		
	}
	
	private static void quickSort(ArrayList<String> array, int start, int end) {
        // index for the "left-to-right scan"
        int i = start;
        // index for the "right-to-left scan"
        int j = end;

        // only examine arrays of 2 or more elements.
        if (j - i >= 1)
        {
            // The pivot point of the sort method is arbitrarily set to the first element int the array.
            String pivot = array.get(i);
            // only scan between the two indexes, until they meet.
            while (j > i)
            {
            	// from the left, if the current element is lexicographically less than the (original)
                // first element in the String array, move on. Stop advancing the counter when we reach
                // the right or an element that is lexicographically greater than the pivot String.
                while (array.get(i).compareTo(pivot) <= 0 && i < end && j > i){
                    i++;
                }
                // from the right, if the current element is lexicographically greater than the (original)
                // first element in the String array, move on. Stop advancing the counter when we reach
                // the left or an element that is lexicographically less than the pivot String.
                while (array.get(j).compareTo(pivot) >= 0 && j > start && j >= i){
                    j--;
                }
                // check the two elements in the center, the last comparison before the scans cross.
                if (j > i)
                    swap(array, i, j);
            }
           
            swap(array, start, j);
            // sort left partition
            quickSort(array, start, j - 1);
            // sort right partition
            quickSort(array, j + 1, end);
        }
    }
   
    private static void swap(ArrayList<String> array, int i, int j) {
	    String temp = array.get(i);
	    array.set(i, array.get(j));
	    array.set(j, temp);
    }
}