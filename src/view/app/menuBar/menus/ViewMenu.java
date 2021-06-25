package view.app.menuBar.menus;

import java.awt.event.ActionEvent;
import persistence.Module;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import persistence.ModuleManager;
import view.graph.GraphPanel;

public class ViewMenu extends AbstractMenu {

	GraphPanel graphPanel;

	public ViewMenu(GraphPanel graphPanel) {

		//set the title
		super(LANGUAGE_MODULE.get("view_menu_title"));
		this.graphPanel = graphPanel;

		
		//make a menu to select the language
		JMenu selectLanguageMenu = new JMenu(LANGUAGE_MODULE.get("select_language"));

		//make a menu item for each available language
		for(String language : ModuleManager.getInstance().getModule("available_langs").getKeyValMap().keySet()) {
						
			JMenuItem langItem = new JMenuItem(language);
			langItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Module localSettings = ModuleManager.getInstance().getModule("local_settings");
					localSettings.put("language", language);
					JOptionPane.showMessageDialog(null, ModuleManager.getInstance().getModule(language).get("please_restart_the_app"));
				}		
				
			});
			
			//add the language item to the langs menu
			selectLanguageMenu.add(langItem);
		}
		
		//add the language selction menu
		this.add(selectLanguageMenu);




		//make the "view zeros" menu item
		JMenuItem viewZeros = new JMenuItem(LANGUAGE_MODULE.get("view_menu_zeros"));
		viewZeros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.toggleHighlightZeros();
			}				
		});
		this.add(viewZeros);	


		//make the view critical points item
		JMenuItem viewCriticalPoints = new JMenuItem(LANGUAGE_MODULE.get("view_menu_critial_points"));
		viewCriticalPoints.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.toggleHighlightCriticalPoints();
			}

		});
		this.add(viewCriticalPoints);	

		//make the view coordinates by hovering cursor item
		JMenuItem viewHoveringCoordinates = new JMenuItem(LANGUAGE_MODULE.get("view_menu_hovering_coords"));
		viewHoveringCoordinates.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.toggleHoverCoordinates();
			}

		});
		this.add(viewHoveringCoordinates);


		//make a new sub-menu for color-related stuff
		JMenu chromaticsMenu = new JMenu(LANGUAGE_MODULE.get("view_menu_color_options"));
		this.add(chromaticsMenu);


		//make the bg-color selection item
		JMenuItem setBgColor = new JMenuItem(LANGUAGE_MODULE.get("view_menu_bg_color"));
		setBgColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.setBackgroundColorProcedure();

			}

		});

		chromaticsMenu.add(setBgColor);

		//make the axes-color selection item
		JMenuItem setAxesColor = new JMenuItem(LANGUAGE_MODULE.get("view_menu_axes_color"));
		setAxesColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.setAxesColorProcedure();

			}

		});

		chromaticsMenu.add(setAxesColor);

		

		//make a new sub-menu for the step
		JMenu stepMenu = new JMenu(LANGUAGE_MODULE.get("step"));


		JMenuItem stepOne = new StepMenuItem(1);
		JMenuItem stepZeroPointOne = new StepMenuItem(0.1);
		JMenuItem stepZeroPointFive = new StepMenuItem(0.5);
		JMenuItem stepTen = new StepMenuItem(10);


		stepMenu.add(stepTen);
		stepMenu.add(stepOne);
		stepMenu.add(stepZeroPointOne);
		stepMenu.add(stepZeroPointFive);


		add(stepMenu);


		
		//make an item to select the number of insertable functions
		JMenuItem insertableFunctions = new JMenuItem(LANGUAGE_MODULE.get("view_menu_max_functions"));
		insertableFunctions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String functionsNumber = JOptionPane.showInputDialog(LANGUAGE_MODULE.get("view_menu_max_insert_new_max_functions"));
				ModuleManager.getInstance().getModule("graph").put("MAX_INSERTABLE_FUNCTIONS", functionsNumber);	
			}
			
		});
		add(insertableFunctions);
		

	}



	class StepMenuItem extends JMenuItem{

		StepMenuItem(double step){

			super(step+"");

			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graphPanel.setStep(step);
				}
			});
		}
	}






}
