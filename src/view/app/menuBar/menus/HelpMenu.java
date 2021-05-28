package view.app.menuBar.menus;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import view.graph.GraphPanel;

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
				JFrame dialog = new JFrame("Welcome");
				dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("./images/welcome.JPG"));
		        dialog.getContentPane().add(Box.createRigidArea(new Dimension(300, 300)));
		        dialog.pack();
			    dialog.setLocationByPlatform(true);
			    dialog.setVisible(true);
			    dialog.setLocationRelativeTo(null);
			    JEditorPane display = new JEditorPane();
			    try {
					display.setPage("file:./HtmlFiles/WelcomeHTML.html");
				} catch (IOException e) {
					System.out.println("errore url");
				}
			    dialog.add(new JScrollPane(display));
			}
			
		});
		
		JMenuItem helpItem = new JMenuItem("about");
		helpItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {					
				JFrame dialog = new JFrame("Help");
				dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("./images/help.JPG"));
		        dialog.getContentPane().add(Box.createRigidArea(new Dimension(350, 300)));
		        dialog.pack();
			    dialog.setLocationByPlatform(true);
			    dialog.setVisible(true);
			    dialog.setLocationRelativeTo(null);
			    JEditorPane display = new JEditorPane();
			    try {
					display.setPage("file:./HtmlFiles/HelpHTML.html");
				} catch (IOException e) {
					System.out.println("errore url");
				}
			    dialog.add(new JScrollPane(display));
			}
			
		});
		
		
		this.add(infoItem);
		this.add(helpItem);
	}


}
