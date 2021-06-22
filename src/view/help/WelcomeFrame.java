package view.help;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class WelcomeFrame extends AbstractPopupFrame {

	public WelcomeFrame() {
	super("Welcome", "./images/welcome.JPG");

	
	try {
		display.setPage(LANGUAGE_MODULE.get("welcome_doc"));
	} catch (IOException e) {
		System.out.println("errore url");
	}

	add(new JScrollPane(display));
	repaint();
	revalidate();
}

	
	
}
