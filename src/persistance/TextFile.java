package persistance;

import java.io.File;
import java.io.IOException;

public class TextFile extends File {

	public TextFile(String path) {
		super(path);
	}
	
	public String read() {
		return FileIO.readFile(this.getPath());
	}
	
	
	public synchronized void write(String text) {
		FileIO.writeFile(this.getPath(), text);
	}
	
	public synchronized void create() {
		try {
			this.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
