package com.ontology.utilization.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

public class MainFrame extends JFrame {
	final static String LOOK_AND_FEEL = "System";

	public MainFrame() {
		setVisible(true);
		setSize(new Dimension(1000, 500));
		initLookAndFeel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.getContentPane().add(new MainPanel());
		this.validate();
		this.repaint();
	}

	public static void initLookAndFeel() {
		String lookAndFeel = null;

		if (LOOK_AND_FEEL != null) {
			if (LOOK_AND_FEEL.equals("Metal")) {
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			} else if (LOOK_AND_FEEL.equals("System")) {
				lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			} else if (LOOK_AND_FEEL.equals("Motif")) {
				lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			} else if (LOOK_AND_FEEL.equals("GTK")) {
				lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
			} else {
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			}
			try {
				UIManager.setLookAndFeel(lookAndFeel);
				UIManager.getLookAndFeelDefaults().put("TitledBorder.font", ((FontUIResource) UIManager.getLookAndFeelDefaults().get("TitledBorder.font")).deriveFont(14.f));
				UIManager.getLookAndFeelDefaults().put("Button.font", ((FontUIResource) UIManager.getLookAndFeelDefaults().get("Button.font")).deriveFont(14.f));
				UIManager.getLookAndFeelDefaults().put("Label.font", ((FontUIResource) UIManager.getLookAndFeelDefaults().get("Label.font")).deriveFont(14.f));
				UIManager.getLookAndFeelDefaults().put("ComboBox.font", ((FontUIResource) UIManager.getLookAndFeelDefaults().get("ComboBox.font")).deriveFont(14.f));
				UIManager.getLookAndFeelDefaults().put("CheckBox.font", ((FontUIResource) UIManager.getLookAndFeelDefaults().get("CheckBox.font")).deriveFont(14.f));
			} catch (ClassNotFoundException e) {
			} catch (UnsupportedLookAndFeelException e) {
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {

		MainFrame mainFrame = new MainFrame();

	}
}
