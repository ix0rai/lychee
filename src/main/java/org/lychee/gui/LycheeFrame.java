package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;
import org.lychee.gui.flex_grid.constraints.FlexGridConstraints;

import javax.swing.JFrame;
import java.awt.Container;

public class LycheeFrame extends JFrame {
	public static int HEIGHT = 600;
	public static int WIDTH = 1400;

	public void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Lychee GUI");

		Container contentPane = this.getContentPane();

		this.setLayout(new FlexGridLayout());
		this.setSize(WIDTH, HEIGHT);

		FlexGridConstraints.Absolute mainPanelConstraints = FlexGridConstraints.createAbsolute().extent(1, 9);

		this.add(new CodePanel(), mainPanelConstraints.pos(0, 1));
		this.add(new DrawingPanel(), mainPanelConstraints.pos(1, 0));

		FlexGridConstraints.Absolute buttonPanelConstraints = FlexGridConstraints.createAbsolute().extent(1, 1);

		this.add(new LycheeButtonPanel(CodePanel.WIDTH), buttonPanelConstraints.pos(0, 0));
		this.add(new LycheeButtonPanel(DrawingPanel.WIDTH), buttonPanelConstraints.pos(1, 9));


		contentPane.setBackground(LycheeColors.PINK);

		this.setVisible(true);
		//this.setIconImage(null) // todo pretty icon
	}
}
