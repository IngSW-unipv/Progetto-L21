package view.help;
/**
 * Displays some help info
 * 
 * @author Team - L21
 *
 */
@SuppressWarnings("serial")
public class AboutFrame extends AbstractPopupFrame {

	public AboutFrame() {
		super(LANGUAGE_MODULE.get("help_menu_about"), "./images/help.JPG");
		super.setDoc("help_doc");
	}

}
