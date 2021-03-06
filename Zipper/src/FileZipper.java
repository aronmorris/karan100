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
	
	private String destination;
	
	private String archivedFile;
	
	public FileZipper(String destinationFolder, File... files) {
		
		this.files = files;
		
		destination = destinationFolder;
	
	}
	
	public File zip() {
		return zip(files, destination);
	}
	
	private File zip(File[] f, String destinationFileName) {
		
		byte[] buffer = new byte[2048];
		
		ZipEntry[] entries = new ZipEntry[f.length];
		
		for (int i = 0; i < f.length; i++) {
			entries[i] = new ZipEntry(f[i].getName()); //populate the ZipEntry array
		}
		
		archivedFile = destinationFileName + (f.length > 1 ? "archive.zip" : f[0].getName().substring(0, f[0].getName().indexOf('.')) + ".zip");
		
		try (FileOutputStream fileOut = new FileOutputStream(archivedFile)) { //create output stream for the new zipped file, add .zip extension if it isn't there already
			
			ZipOutputStream zipOut = new ZipOutputStream(fileOut); //zip stream that feeds into the output
			
			FileInputStream input;
			
			for (int i = 0; i < entries.length; i++) {
				
				System.out.println("Now compressing " + f[i].getName());
				
				input = new FileInputStream(f[i].getAbsolutePath());
				
				int length;
				
				zipOut.putNextEntry(entries[i]);
				
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
		return new File(archivedFile);
		
	}
	
}
