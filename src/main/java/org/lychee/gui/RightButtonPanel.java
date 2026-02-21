package org.lychee.gui;

import org.lychee.gui.flex_grid.FlexGridLayout;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;

public class RightButtonPanel extends LycheeButtonPanel {
	public RightButtonPanel(LycheeFrame frame) {
		super(DrawingPanel.WIDTH, frame);
		this.setLayout(new BorderLayout());

		JPanel leftButtons = new JPanel(new FlexGridLayout());
		leftButtons.setOpaque(false);
		leftButtons.add(new LycheeButton("mouse: 0, 0"));
		leftButtons.add(new Spacer(20, 0));
		leftButtons.add(new LycheeButton("color: FFFFFF"));
		this.add(leftButtons, BorderLayout.WEST);

		JPanel rightButtons = new JPanel(new FlexGridLayout());
		rightButtons.setOpaque(false);
		rightButtons.add(new JLabel(LycheeFrame.ICON));
		rightButtons.add(new LycheeButton("lychee."));
		this.add(rightButtons, BorderLayout.EAST);
	}
}
