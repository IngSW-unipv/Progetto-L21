package persistance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
	
	/**
	 * Get the contents of a file as plain text.
	 * @param path
	 * @return
	 */
	public static String readFile(String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
			String buf = null;
			String result = "";
			while((buf=reader.readLine())!= null) {
				result+=buf;
			}
			reader.close();
			return result;
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Write text to a file.
	 * @param path
	 * @param text
	 */
	public static void writeFile(String path, String text) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)));
			writer.write(text);
			writer.flush();
			writer.close();
		}catch(IOException e){
			
		}	
	}
	
	
	
	
	
	
	
	

}
