package view.app.menuBar.menus;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.graph.GraphPanel;
import view.help.AboutFrame;
import view.help.WelcomeFrame;

public class HelpMenu extends AbstractMenu {
	
	GraphPanel graphPanel;

	public HelpMenu(GraphPanel graphPanel) {
		//set title
		super(LANGUAGE_MODULE.get("help_menu_title"));
		this.graphPanel = graphPanel;
		JMenuItem infoItem = new JMenuItem(LANGUAGE_MODULE.get("help_menu_welcome"));
		infoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {					
				WelcomeFrame wFrame = new WelcomeFrame();
			}
			
		});
		
		JMenuItem helpItem = new JMenuItem(LANGUAGE_MODULE.get("help_menu_about"));
		helpItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {					
				AboutFrame aFrame = new AboutFrame();
			}
			
		});
		
		
		this.add(infoItem);
		this.add(helpItem);
	}


}
