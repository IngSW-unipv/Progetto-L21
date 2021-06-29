package view.app.menuBar.menus;

import javax.swing.JMenu;

import persistence.Module;
import persistence.ModuleManager;
import view.app.GraphController;
/**
 * 
 * An abstract extended by all the menus
 * 
 * @author Team - L21
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractMenu extends JMenu {
	
	static String currentLanguage = ModuleManager.getInstance().getModule("local_settings").get("language");
	static protected Module LANGUAGE_MODULE =ModuleManager.getInstance().getModule(currentLanguage !=null ? currentLanguage : "english");
	protected GraphController interactivePopups;
	
	public AbstractMenu(String title, GraphController interactivePopups) {
		super(title);
		this.interactivePopups = interactivePopups;
	}
	
}
