package persistance;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Module extends TextFile {

	public static String pathToModulesDir = "modules";
	
	public Module(String name) {
		super(pathToModulesDir+File.separator+name);
	}
	
	public void put(String key, String value) {
		if(get(key)!=null) {
			remove(key);
		}
		
		String newText = read()+key+":"+value+"\n";
		write(newText);
	}
	
	public void remove(String key) {
		if(get(key)==null) {
			return; //no key to remove
		}
		
	    String newText = read().replace(key+":"+get(key)+"\n", "");
		write(newText);
	}
	
	public String get(String key) {
		String text = read();
		Pattern pattern = Pattern.compile(key+":"+"(.*?)");
		Matcher matcher = pattern.matcher(text);
		try {
			matcher.find();
			String value = matcher.group(1).trim();
			System.out.println(value);
			return value;
			
		}catch(Exception e) {
			
		}
		
		//key not present
		return null;
	}
	
	
	

}
