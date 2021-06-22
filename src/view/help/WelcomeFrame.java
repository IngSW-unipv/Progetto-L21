package view.help;


import java.io.IOException;


import javax.swing.JScrollPane;

public class WelcomeFrame extends AbstractPopupFrame {

	public WelcomeFrame() {
	super(LANGUAGE_MODULE.get("help_menu_welcome"), "./images/welcome.JPG");

	
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
