import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
 * Takes one or more files as a constructor argument, which it then processes
 * through Java's zip API as a single zipped file/folder regardless of arguments
 * 
 * Optional goal is to implement the Huffman algorithm to compress data
 */

public class FileZipper {

	private File[] files;
	
	public FileZipper(File... files) {
		
		for (File f : files) {
			
		}
	
	}
	
	private void zip(File[] f, String destinationFileName) {
		
		byte[] buffer = new byte[2048];
		
		ZipEntry[] entries = new ZipEntry[f.length];
		
		for (int i = 0; i < f.length; i++) {
			entries[i] = new ZipEntry(f[i].getAbsolutePath()); //populate the ZipEntry array
		}
		
		try (FileOutputStream fileOut = new FileOutputStream(destinationFileName + (destinationFileName.contains(".zip") ? "" : ".zip"))) { //create output stream for the new zipped file, add .zip extension if it isn't there already
			
			ZipOutputStream zipOut = new ZipOutputStream(fileOut); //zip stream that feeds into the output
			
			FileInputStream input;
			
			for (File fileToCompress : f) {
				
				System.out.println("Now compressing " + fileToCompress.getName());
				
				input = new FileInputStream(fileToCompress.getAbsolutePath());
				
				int length;
				
				while ((length = input.read(buffer)) > 0) {
					zipOut.write(buffer, 0, length); //write length bytes into the file
				}
							
				zipOut.closeEntry(); //close the current entry, prepare stream to write the next entry
				
				input.close();
				
			}
			
			zipOut.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch(IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
}
