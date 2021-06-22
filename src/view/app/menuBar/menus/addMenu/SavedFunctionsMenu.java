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
import view.app.menuBar.menus.AbstractMenu;

/**
 * This menu lets the user pick a saved function from the customFunctions module.
 * 
 * (You can save a plotted function by clicking on its expression in the 
 * displayed functions panel)
 *
 */
public class SavedFunctionsMenu extends AbstractMenu implements ModuleListener{
	
	
	Calculator controller;
	Module customFunctions; 
	boolean abilityToAdd;
	
	public SavedFunctionsMenu(Calculator controller, String moduleName, String menuName, boolean abilityToAdd) {
		super(menuName);
		this.controller = controller;
		customFunctions = ModuleManager.getInstance().getModule(moduleName);
		customFunctions.addListener(this);
		this.abilityToAdd = abilityToAdd;
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
		JMenuItem saveNewFunction = new JMenuItem(LANGUAGE_MODULE.get("add_menu_add"));
		saveNewFunction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("scegli un nome per la funzione da salvare:");
				if(name==null) {
					return;
				}
				String expression = JOptionPane.showInputDialog("inserisci un'espressione valida:");
				if(expression==null) {	
					return;
				}
				
				customFunctions.put(name.trim(), expression.trim());
			}

		});
		
		if(abilityToAdd) {
			this.add(saveNewFunction);
		}
		
		
		
		
		
		
		//deletes all of the functions in the module.
		JMenuItem deleteAllItem = new JMenuItem(LANGUAGE_MODULE.get("add_menu_delete_everything"));
		deleteAllItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				customFunctions.removeAll();
			}
			
		});
		
		this.add(deleteAllItem);

		
		//add all of the previously saved functions
		for(String savedFunctionName : customFunctions.getKeyValMap().keySet()) {
			
			//ignore empty names
			if(savedFunctionName.trim().isEmpty()) {
				continue;
			}
			
			JMenu savedFunction = new JMenu(savedFunctionName);
			
			JMenuItem displayItem = new JMenuItem(LANGUAGE_MODULE.get("add_menu_show"));
			displayItem.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.addFunction(savedFunctionName+"(x)");
				}
				
			});
			
			
			JMenuItem deleteItem = new JMenuItem(LANGUAGE_MODULE.get("add_menu_delete"));
			deleteItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					customFunctions.remove(savedFunctionName);						
				}
				
			});
			
			//displays the function's definition
			JMenuItem definition = new JMenuItem(customFunctions.get(savedFunctionName));
			
			
			savedFunction.add(displayItem);
			savedFunction.add(deleteItem);
			savedFunction.add(definition);
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
