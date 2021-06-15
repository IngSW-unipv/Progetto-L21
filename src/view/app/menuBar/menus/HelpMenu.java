package view.app.menuBar.menus;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import view.graph.GraphPanel;
import view.help.AboutFrame;
import view.help.WelcomeFrame;

public class HelpMenu extends JMenu {
	
	GraphPanel graphPanel;

	public HelpMenu(GraphPanel graphPanel) {
		//set title
		super("Help");
		this.graphPanel = graphPanel;
		JMenuItem infoItem = new JMenuItem("welcome");
		infoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {					
				WelcomeFrame wFrame = new WelcomeFrame();
			}
			
		});
		
		JMenuItem helpItem = new JMenuItem("about");
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
