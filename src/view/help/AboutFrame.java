package view.help;

import java.io.IOException;


import javax.swing.JScrollPane;

public class AboutFrame extends AbstractPopupFrame {

	public AboutFrame() {
		super(LANGUAGE_MODULE.get("help_menu_about"), "./images/help.JPG");

		try {
			display.setPage(LANGUAGE_MODULE.get("help_doc"));
		} catch (IOException e) {
			System.out.println("errore url");
		}

		add(new JScrollPane(display));
		repaint();
		revalidate();
	}



}
