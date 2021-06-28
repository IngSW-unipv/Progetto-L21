package view.help;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import persistence.Module;
import persistence.ModuleManager;
/**
 * An abstract frame useful for displaying an HTML documents 
 * 
 * @author Team - L21
 *
 */
@SuppressWarnings("serial")
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

	public void setDoc(String nome) {

		try {
			display.setPage(LANGUAGE_MODULE.get(nome));
		} catch (IOException e) {
			System.out.println("errore url");
		}

		add(new JScrollPane(display));
		repaint();
		revalidate();
	}

}
