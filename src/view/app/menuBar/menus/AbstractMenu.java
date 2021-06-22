package view.app.menuBar.menus;

import javax.swing.JMenu;

import persistence.Module;
import persistence.ModuleManager;

public abstract class AbstractMenu extends JMenu {
	
	
	static String currentLanguage = ModuleManager.getInstance().getModule("local_settings").get("language");
	
	
	
	static protected Module LANGUAGE_MODULE =ModuleManager.getInstance().getModule(currentLanguage !=null ? currentLanguage : "english");	
	
	
	public AbstractMenu(String title) {
		super(title);
	}
	
	
	

}
