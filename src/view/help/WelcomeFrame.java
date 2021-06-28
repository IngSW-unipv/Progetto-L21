package view.help;
/**
 * Displays welcome page
 * 
 * @author Team - L21
 *
 */
@SuppressWarnings("serial")
public class WelcomeFrame extends AbstractPopupFrame {

	public WelcomeFrame() {
		super(LANGUAGE_MODULE.get("help_menu_welcome"), "./images/welcome.JPG");
		super.setDoc("welcome_doc");
	}

}
