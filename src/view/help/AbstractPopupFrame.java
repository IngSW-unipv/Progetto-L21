package view.help;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JEditorPane;
import javax.swing.JFrame;

import persistence.Module;
import persistence.ModuleManager;

public abstract class AbstractPopupFrame extends JFrame {

	static String currentLanguage = ModuleManager.getInstance().getModule("local_settings").get("language");
	static protected Module LANGUAGE_MODULE =ModuleManager.getInstance().getModule(currentLanguage !=null ? currentLanguage : "english");	

	JEditorPane display;
	
	public AbstractPopupFrame(String title, String iconPath){
		super(title);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(iconPath));
        getContentPane().add(Box.createRigidArea(new Dimension(400, 300)));
        pack();
	    setLocationByPlatform(true);
	    setVisible(true);
	    setLocationRelativeTo(null);
	    display = new JEditorPane();
        
	}
	


}
