package view.app.menuBar.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.app.GraphController;
import view.help.AboutFrame;
import view.help.WelcomeFrame;
/**
 * 
 * Show help info 
 * 
 * @author Team - L21
 *
 */
@SuppressWarnings("serial")
public class HelpMenu extends AbstractMenu {
	
	public HelpMenu(GraphController interactivePopups) {
		//set title
		super(LANGUAGE_MODULE.get("help_menu_title"), interactivePopups);
		JMenuItem infoItem = new JMenuItem(LANGUAGE_MODULE.get("help_menu_welcome"));
		infoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {					
				new WelcomeFrame();
			}
			
		});
		
		JMenuItem helpItem = new JMenuItem(LANGUAGE_MODULE.get("help_menu_about"));
		helpItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {					
				new AboutFrame();
			}
			
		});
		
		
		this.add(infoItem);
		this.add(helpItem);
	}

}
