import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

//sorts the csv's chosen column
public class QuickSort {

	protected static ArrayList<CSVRecord> sort(ArrayList<CSVRecord> record, int columnToSort) {
		ArrayList<CSVRecord> sorted = new ArrayList<CSVRecord>(record); //create new record from old one
		
		quickSort(sorted, 0, sorted.size() - 1, columnToSort); //-1 to account for off-by-one error (size is 7, indexed from 0-6)
		
		return sorted;
		
	}
	
	private static void quickSort(ArrayList<CSVRecord> array, int start, int end, int column) { //adding column to specify which part of the record is being chosen to sort by
        // index for the "left-to-right scan"
        int i = start;
        // index for the "right-to-left scan"
        int j = end;

        // only examine arrays of 2 or more elements.
        
        if (j - i >= 1)
        { 
            // The pivot point of the sort method is arbitrarily set to the first element int the array.
            String pivot = array.get(i).get(column);
            // only scan between the two indexes, until they meet.
            while (j > i)
            {
            	// from the left, if the current element is lexicographically less than the (original)
                // first element in the String array, move on. Stop advancing the counter when we reach
                // the right or an element that is lexicographically greater than the pivot String.
                while (array.get(i).get(column).compareTo(pivot) <= 0 && i < end && j > i){
                    i++;
                }
                // from the right, if the current element is lexicographically greater than the (original)
                // first element in the String array, move on. Stop advancing the counter when we reach
                // the left or an element that is lexicographically less than the pivot String.
                while (array.get(j).get(column).compareTo(pivot) >= 0 && j > start && j >= i){
                    j--;
                }
                // check the two elements in the center, the last comparison before the scans cross.
                if (j > i)
                    swap(array, i, j);
            }
           
            swap(array, start, j);
            // sort left partition
            quickSort(array, start, j - 1, column);
            // sort right partition
            quickSort(array, j + 1, end, column);
        }
    }
   
    private static void swap(ArrayList<CSVRecord> array, int i, int j) {
	    CSVRecord temp = array.get(i);
	    array.set(i, array.get(j));
	    array.set(j, temp);
    }
}