package view.help;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class AboutFrame extends JFrame {

	public AboutFrame() {
		super("Help");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./images/help.JPG"));
        getContentPane().add(Box.createRigidArea(new Dimension(400, 300)));
        pack();
	    setLocationByPlatform(true);
	    setVisible(true);
	    setLocationRelativeTo(null);
	    JEditorPane display = new JEditorPane();
	    try {
			display.setPage("file:./HtmlFiles/HelpHTML.html");
		} catch (IOException e) {
			System.out.println("errore url");
		}
	    add(new JScrollPane(display));
	    repaint();
	    revalidate();
	}



}
