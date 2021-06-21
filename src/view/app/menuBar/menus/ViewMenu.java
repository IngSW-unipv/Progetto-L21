package view.app.menuBar.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.graph.GraphPanel;

public class ViewMenu extends JMenu {

	GraphPanel graphPanel;

	public ViewMenu(GraphPanel graphPanel) {

		//set the title
		super("Visualizza");
		this.graphPanel = graphPanel;
		//make the "view zeros" menu item
		JMenuItem viewZeros = new JMenuItem("zeri");
		viewZeros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.toggleHighlightZeros();
			}				
		});
		this.add(viewZeros);	


		//make the view critical points item
		JMenuItem viewCriticalPoints = new JMenuItem("estremanti");
		viewCriticalPoints.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.toggleHighlightCriticalPoints();
			}

		});
		this.add(viewCriticalPoints);	

		//make the view coordinates by hovering cursor item
		JMenuItem viewHoveringCoordinates = new JMenuItem("coordinata puntata");
		viewHoveringCoordinates.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.toggleHoverCoordinates();
			}

		});
		this.add(viewHoveringCoordinates);


		//make a new sub-menu for color-related stuff
		JMenu chromaticsMenu = new JMenu("opz. cromatiche");
		this.add(chromaticsMenu);


		//make the bg-color selection item
		JMenuItem setBgColor = new JMenuItem("colore sfondo");
		setBgColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.setBackgroundColorProcedure();

			}

		});

		chromaticsMenu.add(setBgColor);

		//make the axes-color selection item
		JMenuItem setAxesColor = new JMenuItem("colore assi");
		setAxesColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.setAxesColorProcedure();

			}

		});

		chromaticsMenu.add(setAxesColor);





		//make a new sub-menu for the step
		JMenu stepMenu = new JMenu("passo");


		JMenuItem stepOne = new StepMenuItem(1);
		JMenuItem stepZeroPointOne = new StepMenuItem(0.1);
		JMenuItem stepZeroPointFive = new StepMenuItem(0.5);
		JMenuItem stepTen = new StepMenuItem(10);

		
		stepMenu.add(stepTen);
		stepMenu.add(stepOne);
		stepMenu.add(stepZeroPointOne);
		stepMenu.add(stepZeroPointFive);
		

		add(stepMenu);





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
