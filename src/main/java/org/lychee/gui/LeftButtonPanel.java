package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class LeftButtonPanel extends LycheeButtonPanel {
	public LeftButtonPanel() {
		super(CodePanel.WIDTH);
		this.setLayout(new BorderLayout());

		JPanel leftButtons = new JPanel(new FlexGridLayout());
		leftButtons.setOpaque(false);
		leftButtons.add(new LycheeButton("import code"));
		leftButtons.add(new Spacer(20, 0));
		leftButtons.add(new LycheeButton("export code"));
		this.add(leftButtons, BorderLayout.WEST);

		JPanel rightButtons = new JPanel(new FlexGridLayout());
		rightButtons.setOpaque(false);
		rightButtons.add(new LycheeButton("export image"));
		this.add(rightButtons, BorderLayout.EAST);
	}
}
