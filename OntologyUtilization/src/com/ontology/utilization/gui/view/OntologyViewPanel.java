package com.ontology.utilization.gui.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.ui.FilesystemFilter;

import com.hp.hpl.jena.rdf.model.Model;
import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.generator.OntologyGenerator;
import com.ontology.utilization.gui.MainFrame;

public class OntologyViewPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3595614493842900311L;
	private boolean isMalignatRule;
	private BooleanChromosome bestBooleanChromosome;
	private MainFrame mainFrame;
	private JTextArea ontologyTextArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(ontologyTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	final JButton startButton = new JButton("Start");
	final JButton previousButton = new JButton("Wstecz");
	final JButton saveButton = new JButton("Zapisz");
	final JTextField minTextField = new JTextField("1", 3);
	final JTextField maxTextField = new JTextField("10", 3);

	public OntologyViewPanel(MainFrame mainFrame, boolean isMalignatRule, BooleanChromosome bestBooleanChromosome) {
		super();
		this.mainFrame = mainFrame;
		this.isMalignatRule = isMalignatRule;
		this.bestBooleanChromosome = bestBooleanChromosome;
		init();
	}

	private void init() {
		this.setLayout(new GridBagLayout());
		createOptionPanel();
		createOntologyPanel();
	}

	private void createOptionPanel() {
		JPanel optionPanel = new JPanel();
		optionPanel.setLayout(new GridBagLayout());
		minTextField.setMinimumSize(new Dimension(50,30));
		maxTextField.setMinimumSize(new Dimension(50,30));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		optionPanel.add(new JLabel("Minimalna wartoœæ atrybutów"), c);
		c.gridy = 1;
		optionPanel.add(new JLabel("Maksymalna wartoœæ atrybutów"), c);

		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		optionPanel.add(minTextField, c);
		c.gridy = 1;
		optionPanel.add(maxTextField, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		Box buttonsBox = new Box(BoxLayout.X_AXIS);
		buttonsBox.add(previousButton);
		buttonsBox.add(startButton);
		buttonsBox.add(saveButton);
		optionPanel.add(buttonsBox, c);

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				startButton.setEnabled(false);
				generateOntology();
				startButton.setEnabled(true);
			}
		});
		previousButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.changeToGeneticAlgorithmPanel();
			}
		});
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveToFile();
			}
		});

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		this.add(optionPanel, c);
	}

	private void createOntologyPanel() {

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 1;
		c.weightx = 3. / 4.;

		ontologyTextArea.setEditable(false);
		this.add(scrollPane, c);
	}

	private void generateOntology() {
		if (validateTextFields()) {
			Model model = new OntologyGenerator().generateOntology(bestBooleanChromosome, isMalignatRule, 1, 10);
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			model.write(arrayOutputStream, "RDF/XML-ABBREV");
			ontologyTextArea.setText(arrayOutputStream.toString());
			ontologyTextArea.setCaretPosition(0);
		}
	}

	private boolean validateTextFields() {
		try {
			int min = Integer.parseInt(minTextField.getText());
			int max = Integer.parseInt(maxTextField.getText());
			if (min >= max) {
				JOptionPane.showMessageDialog(this, "Minimalna wartoœæ atrybutu musi byæ mniejsza od maksymalnej!", "B³¹d", JOptionPane.ERROR_MESSAGE);
			} else {
				return true;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Nieporwany format atrybutów!", "B³¹d", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	private void saveToFile() {
		if (!ontologyTextArea.getText().trim().isEmpty()) {
			JFileChooser fileChooser = new JFileChooser(".");
			int returnVal = fileChooser.showDialog(this, "Zapisz");
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File outputFile = fileChooser.getSelectedFile();
				FileWriter fileWriter = null;
				try {
					fileWriter = new FileWriter(outputFile);
					fileWriter.write(ontologyTextArea.getText());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "B³¹d zapisu", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} finally {
					try {
						if (fileWriter != null) {
							fileWriter.close();
						}
					} catch (IOException ex) {

					}
				}
			}
		}
	}
}
