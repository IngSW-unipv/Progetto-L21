package view.app.insertedFunctionsPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import controller.Calculator;
import model.core.FunctionIF;
import persistence.ModuleManager;

/**
 * Each plotted function gets to have a FunctionFragment that
 * represents its expression on screen, allows the user 
 * to delete the plotted function at any time, and to plot
 * its derivative.
 * 
 * Eventually this may contain further options to 
 * be applied on single functions.
 *
 */
public class FunctionFragment extends JPanel implements MouseListener{

	//controller
	Calculator controller;
	//this fragment's function
	FunctionIF function;
	//button that deletes its function 
	JButton removeButton;
	//the expression of this fragment's function
	String expression;
	//label used to display the expression
	JLabel label;
	//button that allows you to differentiate its function
	JButton deriveButton;
	//this fragment's popup menu
	FragmentsPopupMenu popupMenu;
	
	public FunctionFragment(FunctionIF function, Calculator controller) {
		//set this fragment's function
		this.function = function;
		//make a new label 
		label = new JLabel(function.getExpression().toString());
		//set the color of the label to match the color of the function on the graph
		label.setForeground(function.getColor());
		//make a new remove button
		removeButton = new JButton("/");
		//color of remove button text = RED
		removeButton.setForeground(Color.red);
		//make a new derive button
		deriveButton = new JButton("d/dx");
		//make a new popup menu for this fragment
		popupMenu = new FragmentsPopupMenu(function,controller);

		
		//add buttons and label
		this.add(removeButton);
		this.add(deriveButton);
		this.add(label);

		//IMPORTANT: makes sure that keylistening doesn't get stuck on this panel for some swing-related reason.
		removeButton.setFocusable(false);
		deriveButton.setFocusable(false);

		//set the remove button's action to = delete the function
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//tell the controller to remove the function
				controller.removeFunction(function.getExpression().toString());
			}
		});
		
		//set the derive's button action
		deriveButton.addActionListener(new ActionListener() {

			//tell the controller to add this function's derivative
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.addFunction(function.getDerivative());
			}
			
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}

	//>------UNIMPLEMENTED METHODS----------------<
	@Override
	public void mouseEntered(MouseEvent e) {			
	}

	@Override
	public void mouseExited(MouseEvent e) {			
	}

	@Override
	public void mousePressed(MouseEvent e) {			
	}

	@Override
	public void mouseReleased(MouseEvent e) {			
	}
	//>-----------END UNIMPLEMENTED METHODS----------------<

}





//>----------CLASS: FRAGMENTSPOPUPMENU-------------------//<
/**
 * This popup menu makes up for the lack of space on the 
 * fragment. Providing more options to be applied on 
 * a single function, such as "save".
 */

class FragmentsPopupMenu extends JPopupMenu{
	
	FunctionIF function;
	Calculator controller;
	
	public FragmentsPopupMenu(FunctionIF function, Calculator controller) {
		//set the function to be manipulated 
		this.function = function;
		this.controller = controller;
		
		//set the "save" item
        JMenuItem saveItem = new JMenuItem("salva");
		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog("dai un nome alla funzione da salvare:");
				if(name!=null) {
					ModuleManager.getInstance().getModule("customFunctions").put(name, function.getExpression());
				}
			}
		});
		
		//set the remove button
        JMenuItem removeFromGraphItem = new JMenuItem("rimuovi");
        removeFromGraphItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.removeFunction(function);
			}
        	
        });
		
        
        //add all of the buttons to the popup menu
		this.add(saveItem);
		this.add(removeFromGraphItem);
		
		
	}
}
//>---------- END FRAGMENTSPOPUPMENU-------------------//<





