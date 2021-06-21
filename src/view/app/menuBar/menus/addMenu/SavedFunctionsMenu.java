package view.app.menuBar.menus.addMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Calculator;
import persistence.Module;
import persistence.ModuleListener;
import persistence.ModuleManager;

/**
 * This menu lets the user pick a saved function from the customFunctions module.
 * 
 * (You can save a plotted function by clicking on its expression in the 
 * displayed functions panel)
 *
 */
public class SavedFunctionsMenu extends JMenu implements ModuleListener{
	
	
	Calculator controller;
	Module customFunctions; 
	
	public SavedFunctionsMenu(Calculator controller, String moduleName, String menuName ) {
		super(menuName);
		this.controller = controller;
		customFunctions = ModuleManager.getInstance().getModule(moduleName);
		customFunctions.addListener(this);
		reloadMenu();
	}
	
	
	/**
	 * Loads all of the saved functions from the customFunctions
	 * module to the menu.
	 */
	private void reloadMenu() {
		
		//remove all previous items 
		this.removeAll();
		
		
		//add the "save new function item"
		JMenuItem saveNewFunction = new JMenuItem("aggiungi");
		saveNewFunction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("scegli un nome per la funzione da salvare:");
				String expression = JOptionPane.showInputDialog("inserisci un'espressione valida:");
				if(name==null||expression==null) {	
					return;
				}
				customFunctions.put(name.trim(), expression.trim());
			}

		});
		this.add(saveNewFunction);
		
		
		//add all of the previously saved functions
		for(String savedFunctionName : customFunctions.getKeyValMap().keySet()) {
			JMenu savedFunction = new JMenu(savedFunctionName);
			
			JMenuItem displayItem = new JMenuItem("mostra");
			displayItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.addFunction(savedFunctionName+"(x)");
				}
				
			});
			
			
			JMenuItem deleteItem = new JMenuItem("elimina");
			deleteItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					ModuleManager.getInstance().getModule("customFunctions").remove(savedFunctionName);						
				}
				
			});
			
			savedFunction.add(displayItem);
			savedFunction.add(deleteItem);
			this.add(savedFunction);
		}
	}


	/**
	 * Reload saved functions menu if functions were added/removed from the module
	 */
	@Override
	public void dealWithModuleUpdate(Module module) {
		reloadMenu();
	}
	@Override
	public void dealWithSingularUpdate(String key, String value) {			
	}
	
}
