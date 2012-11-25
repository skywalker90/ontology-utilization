package com.ontology.utilization.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ontology.utilization.domain.genetic.algorithm.BooleanGeneticAlgorithm;
import com.ontology.utilization.gui.controller.DefaultController;
import com.ontology.utilization.gui.model.BooleanEnvironmentModel;
import com.ontology.utilization.gui.view.BooleanEnvironmentViewPanel;

public class MainPanel extends JPanel {
	private BooleanEnvironmentModel model;
	private ChartsAlgorithmHandler chartsAlgorithmHandler;

	public MainPanel() {
		init();
	}

	private void init() {
		setLayout(new GridBagLayout());
		createHandlerPanel();
		createOptionPanel();
		createControllPanel();
	}

	private void createHandlerPanel() {
		if (chartsAlgorithmHandler != null) {
			this.remove(chartsAlgorithmHandler.getChartPanel());
		}
		chartsAlgorithmHandler = new ChartsAlgorithmHandler();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 3. / 4.;
		c.weighty = 1;
		this.add(chartsAlgorithmHandler.getChartPanel(), c);
		revalidate();
	}

	private void createOptionPanel() {
		DefaultController controller = new DefaultController();
		model = new BooleanEnvironmentModel();
		BooleanEnvironmentViewPanel view = new BooleanEnvironmentViewPanel(controller);
		controller.addModel(model);
		controller.addView(view);
		model.initDefaults();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1. / 4.;
		c.weighty = 1;
		this.add(view, c);
	}

	private void createControllPanel() {
		JPanel controllPanel = new JPanel();
		final JButton startButton = new JButton("Start");
		final JButton stopButton = new JButton("Stop");
		controllPanel.add(startButton);
		controllPanel.add(stopButton);
		stopButton.setEnabled(false);
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (model.getPatients() == null || model.getPatients().size() == 0) {
					JOptionPane.showMessageDialog(MainPanel.this, "Wybierz plik z danymi pacjentów", "B³¹d", JOptionPane.ERROR_MESSAGE);
				} else {
					startButton.setEnabled(false);
					stopButton.setEnabled(true);
					createHandlerPanel();
					final BooleanGeneticAlgorithm algorithm = model.createBooleanGeneticAlgorithm(chartsAlgorithmHandler);
					Runnable runnable = new Runnable() {

						@Override
						public void run() {
							algorithm.run();
							startButton.setEnabled(true);
							stopButton.setEnabled(false);
						}
					};
					Executors.newSingleThreadExecutor().execute(runnable);
				}
			}
		});

		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				stopButton.setEnabled(false);
				chartsAlgorithmHandler.stopAlgorithm();
				startButton.setEnabled(true);
			}
		});
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.weightx = 1. / 4.;

		this.add(controllPanel, c);
	}
}
