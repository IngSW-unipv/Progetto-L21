package persistence;

import java.io.File;
import java.io.IOException;

public class TextFile extends File {

	public TextFile(String path) {
		super(path);
	}

	/**
	 * Get the contents of this TextFile.
	 * @return
	 */
	public String read() {
		return FileIO.readFile(this.getPath());
	}
	
	
	/**
	 * OVERWRITE this TextFile.
	 * @param text
	 */
	public synchronized void write(String text) {
		FileIO.writeFile(this.getPath(), text);
	}
	
	/**
	 * Create this file on disk.
	 */
	public synchronized void create() {
		try {
			this.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
