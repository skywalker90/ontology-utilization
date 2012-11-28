package com.ontology.utilization.gui.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ontology.utilization.domain.BooleanPatient;
import com.ontology.utilization.domain.genetic.algorithm.crossover.Crossover;
import com.ontology.utilization.domain.genetic.algorithm.mutation.Mutation;
import com.ontology.utilization.factory.BooleanPatientFactory;
import com.ontology.utilization.gui.controller.DefaultController;
import com.ontology.utilization.parser.dataset.FileDatasetParser;
import com.ontology.utilization.parser.dataset.model.Dataset;

public class BooleanEnvironmentViewPanel extends AbstractViewPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4981338238615668404L;
	private final JButton patiensButton = new JButton("Wczytaj pacjentów");
	private final JLabel patiensCountLabel = new JLabel("Iloœæ pacjentów = 0");
	private final JCheckBox isMalignatRuleCheckBox = new JCheckBox("Z³oœliwa zasada?");
	private final JLabel sizeOfInitialPopulationLabel = new JLabel("Rozmiar pierwszego pokolenia");
	private final JSlider sizeOfInitialPopulationSlider = new JSlider();
	private final JTextField sizeOfInitialPopulationTextField = new JTextField(5);
	private final JLabel iterationLabel = new JLabel("Iloœæ iteracji");
	private final JSlider iterationSlider = new JSlider();
	private final JTextField iterationTextField = new JTextField(5);
	private final JLabel sizeOfStandardPopulationLabel = new JLabel("Rozmiar populacji");
	private final JSlider sizeOfStandardPopulationSlider = new JSlider();
	private final JTextField sizeOfStandardPopulationTextField = new JTextField(5);
	private final JLabel probabilityOfCrossoverLabel = new JLabel("Prawdopodobieñstwo krzy¿owania");
	private final JSlider probabilityOfCrossoverSlider = new JSlider();
	private final JTextField probabilityOfCrossoverTextField = new JTextField(5);
	private final JLabel probabilityOfMutationLabel = new JLabel("Prawdopodobieñstwo mutacji");
	private final JSlider probabilityOfMutationSlider = new JSlider();
	private final JTextField probabilityOfMutationTextField = new JTextField(5);
	private final JLabel thresholdLabel = new JLabel("Próg");
	private final JSlider thresholdSlider = new JSlider();
	private final JTextField thresholdTextField = new JTextField(5);
	private final JComboBox mutationMethodComboBox = new JComboBox(Mutation.BOOLEAN_MUTATIONS);
	private final JComboBox crossoverMethodComboBox = new JComboBox(Crossover.BOOLEAN_CROSSOVERS);
	private final JButton resetDefaultButton = new JButton("Domyœlne wartoœci");

	private DefaultController controller;

	public BooleanEnvironmentViewPanel(DefaultController controller) {
		super();
		this.controller = controller;

		init();
	}

	private void init() {
		setLayout(new GridBagLayout());

		int gridy = 0;
		addCenterComponent(patiensButton, gridy);

		addCenterComponent(patiensCountLabel, ++gridy);
		addCenterComponent(isMalignatRuleCheckBox, ++gridy);

		addLabel(sizeOfInitialPopulationLabel, ++gridy);
		addTextField(sizeOfInitialPopulationTextField, gridy);
		addSlider(sizeOfInitialPopulationSlider, ++gridy);

		addLabel(sizeOfStandardPopulationLabel, ++gridy);
		addTextField(sizeOfStandardPopulationTextField, gridy);
		addSlider(sizeOfStandardPopulationSlider, ++gridy);

		addCenterComponent(crossoverMethodComboBox, ++gridy);

		addLabel(probabilityOfCrossoverLabel, ++gridy);
		addTextField(probabilityOfCrossoverTextField, gridy);
		addSlider(probabilityOfCrossoverSlider, ++gridy);

		addCenterComponent(mutationMethodComboBox, ++gridy);

		addLabel(probabilityOfMutationLabel, ++gridy);
		addTextField(probabilityOfMutationTextField, gridy);
		addSlider(probabilityOfMutationSlider, ++gridy);

		addLabel(thresholdLabel, ++gridy);
		addTextField(thresholdTextField, gridy);
		addSlider(thresholdSlider, ++gridy);

		addLabel(iterationLabel, ++gridy);
		addTextField(iterationTextField, gridy);
		addSlider(iterationSlider, ++gridy);
		addCenterComponent(resetDefaultButton, ++gridy);
		addListeners();
	}

	private void addListeners() {
		isMalignatRuleCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.changeElementIsMalignatRule(isMalignatRuleCheckBox.isSelected());
			}
		});

		probabilityOfCrossoverSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				controller.changeElementProbabilityOfCrossover(probabilityOfCrossoverSlider.getValue());
			}
		});
		probabilityOfCrossoverTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (probabilityOfCrossoverTextField.getText().trim().equals("")) {
					probabilityOfCrossoverTextField.setText("0");
				}
				controller.changeElementProbabilityOfCrossover(Integer.parseInt(probabilityOfCrossoverTextField.getText()));
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});

		probabilityOfMutationSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				controller.changeElementProbabilityOfMutation(probabilityOfMutationSlider.getValue());
			}
		});
		probabilityOfMutationTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (probabilityOfMutationTextField.getText().trim().equals("")) {
					probabilityOfMutationTextField.setText("0");
				}
				controller.changeElementProbabilityOfMutation(Integer.parseInt(probabilityOfMutationTextField.getText()));
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

		thresholdSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				controller.changeElementThreshold(thresholdSlider.getValue());
			}
		});
		thresholdTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (thresholdTextField.getText().trim().equals("")) {
					thresholdTextField.setText("0");
				}
				controller.changeElementThreshold(Integer.parseInt(thresholdTextField.getText()));
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

		sizeOfInitialPopulationSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				controller.changeElementSizeOfInitialPopulation(sizeOfInitialPopulationSlider.getValue());
			}
		});
		sizeOfInitialPopulationTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (sizeOfInitialPopulationTextField.getText().trim().equals("")) {
					sizeOfInitialPopulationTextField.setText("0");
				}
				controller.changeElementSizeOfInitialPopulation(Integer.parseInt(sizeOfInitialPopulationTextField.getText()));
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

		sizeOfStandardPopulationSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				controller.changeElementSizeOfStandardPopulation(sizeOfStandardPopulationSlider.getValue());
			}
		});
		sizeOfStandardPopulationTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (sizeOfStandardPopulationTextField.getText().trim().equals("")) {
					sizeOfStandardPopulationTextField.setText("0");
				}
				controller.changeElementSizeOfStandardPopulation(Integer.parseInt(sizeOfStandardPopulationTextField.getText()));
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

		iterationSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				controller.changeElementIteration(iterationSlider.getValue());
			}
		});
		iterationTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (iterationTextField.getText().trim().equals("")) {
					iterationTextField.setText("0");
				}
				controller.changeElementIteration(Integer.parseInt(iterationTextField.getText()));
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

		patiensButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(".");
				int returnVal = fileChooser.showDialog(BooleanEnvironmentViewPanel.this, "Wczytaj dane pacjentów");

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					FileDatasetParser fDP = new FileDatasetParser(file);
					Dataset dataset = null;
					try {
						dataset = fDP.getParsedDataset();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					List<BooleanPatient> patients = BooleanPatientFactory.getInstace().producePatient(dataset.getDataset());
					controller.changeElementPatients(patients);
				}
			}
		});
		
		resetDefaultButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.initDefault();
			}
		});
	}

	private void addTextField(JTextField textField, int gridy) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = gridy;
		c.anchor = GridBagConstraints.WEST;
		this.add(textField, c);
	}

	private void addSlider(JSlider slider, int gridy) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = gridy;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(slider, c);
	}

	private void addCenterComponent(JComponent component, int gridy) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = gridy;
		this.add(component, c);
	}

	private void addLabel(JLabel label, int gridy) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = gridy;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		this.add(label, c);
	}

	public void modelPropertyChange(final PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(DefaultController.ELEMENT_isMalignatRule_PROPERTY)) {
			isMalignatRuleCheckBox.setSelected((Boolean) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_probabilityOfCrossover_PROPERTY)) {
			probabilityOfCrossoverSlider.setValue((Integer) evt.getNewValue());
			probabilityOfCrossoverTextField.setText(evt.getNewValue().toString());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_probabilityOfCrossoverMax_PROPERTY)) {
			probabilityOfCrossoverSlider.setMaximum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_probabilityOfCrossoverMin_PROPERTY)) {
			probabilityOfCrossoverSlider.setMinimum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_probabilityOfMutation_PROPERTY)) {
			probabilityOfMutationSlider.setValue((Integer) evt.getNewValue());
			probabilityOfMutationTextField.setText(evt.getNewValue().toString());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_probabilityOfMutationMax_PROPERTY)) {
			probabilityOfMutationSlider.setMaximum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_probabilityOfMutationMin_PROPERTY)) {
			probabilityOfMutationSlider.setMinimum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_sizeOfInitialPopulation_PROPERTY)) {
			sizeOfInitialPopulationSlider.setValue((Integer) evt.getNewValue());
			sizeOfInitialPopulationTextField.setText(evt.getNewValue().toString());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_sizeOfInitialPopulationMax_PROPERTY)) {
			sizeOfInitialPopulationSlider.setMaximum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_sizeOfInitialPopulationMin_PROPERTY)) {
			sizeOfInitialPopulationSlider.setMinimum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_sizeOfStandardPopulation_PROPERTY)) {
			sizeOfStandardPopulationSlider.setValue((Integer) evt.getNewValue());
			sizeOfStandardPopulationTextField.setText(evt.getNewValue().toString());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_sizeOfStandardPopulationMax_PROPERTY)) {
			sizeOfStandardPopulationSlider.setMaximum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_sizeOfStandardPopulationMin_PROPERTY)) {
			sizeOfStandardPopulationSlider.setMinimum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_threshold_PROPERTY)) {
			thresholdSlider.setValue((Integer) evt.getNewValue());
			thresholdTextField.setText(evt.getNewValue().toString());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_thresholdMax_PROPERTY)) {
			thresholdSlider.setMaximum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_thresholdMin_PROPERTY)) {
			thresholdSlider.setMinimum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_iteration_PROPERTY)) {
			iterationSlider.setValue((Integer) evt.getNewValue());
			iterationTextField.setText(evt.getNewValue().toString());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_iterationMax_PROPERTY)) {
			iterationSlider.setMaximum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_iterationMin_PROPERTY)) {
			iterationSlider.setMinimum((Integer) evt.getNewValue());
		} else if (evt.getPropertyName().equals(DefaultController.ELEMENT_patients_PROPERTY)) {
			patiensCountLabel.setText("Iloœæ pacjentów = " + ((Collection) evt.getNewValue()).size());
		}
	}
}
