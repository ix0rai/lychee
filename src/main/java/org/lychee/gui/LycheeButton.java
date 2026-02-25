package org.lychee.gui;

import javax.swing.JButton;
import javax.swing.JLabel;

public class LycheeButton extends JButton {
	public LycheeButton(String text) {
		super();
		JLabel label = new JLabel(text);
		label.setForeground(LycheeColors.WHITE);
		this.add(label);

		this.setBackground(LycheeColors.BROWN);
	}
}
