import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FileUnzipper {

	private LinkedList<File> zipQueue;
	
	private String destination;
	
	public FileUnzipper(File[] zippedFile, String destinationFolder) {
		
		zipQueue = new LinkedList<File>();
		
		for (File f : zippedFile) {
			zipQueue.push(f);
		}
		
		destination = destinationFolder;
		
	}
	//Each time unzip is called, it unzips one more file from the linkedlist
	//If none are left, it terminates early
	public File unzip() {
		
		if (zipQueue.isEmpty()) { 
			return null;
		}
		
		return unzip(zipQueue.pop(), destination);
	}
	
	private File unzip(File zipFile, String outputLocation) {
		
		byte[] buffer = new byte[2048]; //set size of data buffer to be read at once
		
		File outputLoc = null;
		
		File unzippedFileLocation = null;
		
		try {
			
			outputLoc = new File(outputLocation); //create a new file at the desired location
			
			if (!outputLoc.exists()) {
				outputLoc.mkdir(); //make a directory for that file if the folder doesn't exist
			}
			
			ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile)); //inputstream reads from zipped file
			
			ZipEntry entry = zipIn.getNextEntry(); //each entry is a discrete file
			
			while (entry != null) { 
				
				String fileName = entry.getName(); //get the zipped file's name
				
				File unzipped = new File(outputLoc + File.separator + fileName); //and create a new file where it will go in the destination folder, by that name
				
				System.out.println("Unzipping: " + unzipped.getAbsoluteFile()); //print the file being unzipped
				
				new File(unzipped.getParent()).mkdirs(); //prevent FileNotFoundException for compressed folder
				
				FileOutputStream fileOut = new FileOutputStream(outputLocation + unzipped.getName()); //stream to write to the file that will contain the unzipped data
				
				int length; //# of bytes to read from the buffer
				
				while ((length = zipIn.read(buffer)) > 0) { //read buffer length of bytes from the stream into a byte array
					fileOut.write(buffer, 0, length); //and write the decompressed data to a new file
				}
				
				fileOut.close(); //close the output stream, as the file is written 
				
				entry = zipIn.getNextEntry();
				
			}
			
			//resource cleanup once the outer loop exits
			
			zipIn.closeEntry(); 
			
			zipIn.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return unzippedFileLocation;
		
	}
	
}
