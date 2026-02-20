package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;

import javax.swing.JFrame;

public class LycheeFrame extends JFrame {
	public void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Lychee GUI");

		this.setLayout(new FlexGridLayout());
		this.setResizable(false);
		this.setSize(800, 600);
		this.setVisible(true);
		//this.setIconImage(null) // todo pretty icon
	}
}
