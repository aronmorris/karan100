
public class sortMain {

	public static void main(String[] args) {
		
		Record record = new Record("-!Insert Path!-"); //removed path as it's a public repo
		
		record.print();
		
		System.out.println("Newline");
		
		Record record2 = record.sort(0);
		
		record2.print();
		
	}
	
}
