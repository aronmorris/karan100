import java.io.File;
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
	
	private void zip(File[] f) {
		
		byte[] buffer = new byte[2048];
		
		ZipEntry[] entries = new ZipEntry[f.length];
		
		for (int i = 0; i < f.length; i++) {
			entries[i] = new ZipEntry(f[i].getAbsolutePath() + f[i].getName()); //populate the ZipEntry array
		}
		
		try (FileOutputStream fileOut = new FileOutputStream(entry.getName() + ".zip")) { //create output stream for the new zipped file
			
			ZipOutputStream zipOut = new ZipOutputStream(fileOut); //zip stream that feeds into the output
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch(IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
}
